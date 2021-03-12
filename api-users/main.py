from typing import List
from fastapi import Depends, FastAPI, HTTPException, status
from sqlalchemy.orm import Session
from . import crud, models, schemas
from .database import SessionLocal, engine

from fastapi_login import LoginManager
from fastapi.security import OAuth2PasswordRequestForm
from fastapi_login.exceptions import InvalidCredentialsException

models.Base.metadata.create_all(bind=engine)

#FastAPI-Login (Secret Key 256-bit)
SECRET = "WhmWC5obY94hZrf8TMODfXzxCeDeVBSm"
manager = LoginManager(SECRET, tokenUrl='/auth/token')

app = FastAPI()


# Dependency
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()


@app.post("/users/", response_model=schemas.User)
def create_user(user: schemas.UserCreate, db: Session = Depends(get_db)):
    db_user = crud.get_user_by_username(db, username=user.username)
    if db_user:
        raise HTTPException(status_code=400, detail="Username already registered")
    return crud.create_user(db=db, user=user)

@app.get("/users/", response_model=List[schemas.User])
def read_users(skip: int = 0, limit: int = 100, db: Session = Depends(get_db)):
    users = crud.get_users(db, skip=skip, limit=limit)
    return users

@app.get("/users/{user_uuid}", response_model=schemas.User)
def read_user(user_uuid: str, db: Session = Depends(get_db)):
    db_user = crud.get_user(db, user_uuid=user_uuid)
    if db_user is None:
        raise HTTPException(status_code=404, detail="User not found")
    return db_user

@app.delete("/users/{user_uuid}", status_code=status.HTTP_200_OK)
def delete_user(user_uuid: str, db: Session = Depends(get_db)):
    db_user = crud.get_user(db, user_uuid=user_uuid)
    if db_user is None:
        return {"message": "The user with uuid: {} is not present in database".format(user_uuid)}
    result = crud.delete_user(db, user_uuid=user_uuid)
    return {"message": "User with uuid: {} was deleted successfully!".format(user_uuid)}

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
    return {'access_token': access_token, 'token_type': 'bearer'}