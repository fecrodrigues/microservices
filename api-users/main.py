# pylint: disable=unused-variable
from typing import List
from fastapi import Depends, FastAPI, HTTPException, status, Header
from sqlalchemy.orm import Session
import crud, models, schemas
from database import SessionLocal, engine
from uuid import UUID
import uvicorn

from fastapi_login import LoginManager
from fastapi.security import OAuth2PasswordRequestForm
from fastapi_login.exceptions import InvalidCredentialsException
from fastapi.middleware.cors import CORSMiddleware

models.Base.metadata.create_all(bind=engine)

#FastAPI-Login (Secret Key 256-bit)
SECRET = "WhmWC5obY94hZrf8TMODfXzxCeDeVBSm"
manager = LoginManager(SECRET, tokenUrl='/auth/token')

app = FastAPI()

app.add_middleware(
    CORSMiddleware,
    allow_origins=["*"],
    allow_credentials=True,
    allow_methods=["*"],
    allow_headers=["*"],
)

# Dependency
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


@app.post("/users/", response_model=schemas.User)
def create_user(user_in: schemas.UserCreate, db: Session = Depends(get_db)):
    db_user = crud.get_user_by_username(db, username=user_in.username)
    if db_user:
        raise HTTPException(status_code=400, detail="Username already registered")
    user = crud.create_user(db=db, user_obj=user_in)
    return user

@app.get("/users/", response_model=List[schemas.User])
def read_users(skip: int = 0, limit: int = 100, advocate: bool = False, db: Session = Depends(get_db)):
    db_users = crud.get_users(db, skip=skip, limit=limit, advocate=advocate)
    return db_users

@app.get("/users/{user_uuid}", response_model=schemas.User)
def read_user(user_uuid: str, db: Session = Depends(get_db)):
    try:
        uuid_test = UUID(user_uuid, version=4)
    except ValueError:
        raise HTTPException(status_code=404, detail="User not found")
    db_user = crud.get_user(db, user_uuid=user_uuid)
    if db_user is None:
        raise HTTPException(status_code=404, detail="User not found")
    return db_user

@app.get("/logins/{user_username}", response_model=schemas.User)
def read_username(user_username: str, db: Session = Depends(get_db)):
    print(user_username)
    db_user = crud.get_user_by_username(db, username=user_username)
    return db_user

@app.delete("/users/{user_uuid}", status_code=status.HTTP_200_OK)
def delete_user(user_uuid: str, db: Session = Depends(get_db)):
    try:
        uuid_test = UUID(user_uuid, version=4)
    except ValueError:
        raise HTTPException(status_code=404, detail="User not found")
    db_user = crud.get_user(db, user_uuid=user_uuid)
    if db_user is None:
        raise HTTPException(status_code=404, detail="The user with uuid: {} is not present in database".format(user_uuid))
        #return {"message": "The user with uuid: {} is not present in database".format(user_uuid)}
    result = crud.delete_user(db, user_uuid=user_uuid)
    if result:
        return {"message": "User with uuid: {} was deleted successfully!".format(user_uuid)}
    raise HTTPException(status_code=500, detail="Internal Server Error")

@app.put("/users/{user_uuid}", response_model=schemas.User)
def update_user(user_uuid: str, user_in: schemas.UserCreate, db: Session = Depends(get_db)):
    try:
        uuid_test = UUID(user_uuid, version=4)
    except ValueError:
        raise HTTPException(status_code=404, detail="User not found")
    db_user = crud.get_user(db, user_uuid=user_uuid)
    if not db_user:
        raise HTTPException(status_code=400, detail="Bad Request")
    user_obj_username = crud.get_user_by_username(db, username=user_in.username)
    if (user_in.username != db_user.username and user_obj_username):
        raise HTTPException(status_code=400, detail="Username already registered")
    user = crud.update_user(db, db_obj=db_user, user_obj=user_in)
    return user


@app.post('/auth/token')
def login(data: OAuth2PasswordRequestForm = Depends(), db: Session = Depends(get_db)):
    credentials = schemas.Credentials(
        username=data.username,
        password=data.password
    )
    print(credentials.username)
    print(credentials.password)

    db_credentials = crud.get_credentials(db, credentials=credentials)
    if not db_credentials:
        raise InvalidCredentialsException
    access_token = manager.create_access_token(
        data=dict(sub=credentials.username)
    )
    print(db_credentials)
    db_user = crud.get_user_by_username(db, username=credentials.username)
    return {'uuid': db_user.uuid, 'access_token': access_token, 'token_type': 'bearer'}

if __name__ == "__main__":
    uvicorn.run(app, host="0.0.0.0", port=8000)