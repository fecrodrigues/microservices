from sqlalchemy.orm import Session
from fastapi.encoders import jsonable_encoder
import models, schemas
from datetime import datetime
import bcrypt

def get_user(db: Session, user_uuid: str):
    return db.query(models.User).filter(models.User.uuid == user_uuid).first()

def get_user_by_username(db: Session, username: str):
    return db.query(models.User).filter(models.User.username == username).first()

def get_users(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.User).offset(skip).limit(limit).all()

def create_user(db: Session, user_obj: schemas.UserCreate):
    
    hashed_password = bcrypt.hashpw(user_obj.password.encode('utf-8'), bcrypt.gensalt(14))
    print(hashed_password)
    print(user_obj.password.encode('utf-8'))
    print(bcrypt.checkpw(user_obj.password.encode('utf-8'), hashed_password))
    db_user = models.User(
        username=user_obj.username,
        password=str(hashed_password.decode('utf-8')),
        advocate=user_obj.advocate,
        biography=user_obj.biography,
        birthdate=user_obj.birthdate,
        firstname=user_obj.firstname,
        lastname=user_obj.lastname,
        oabnumber=user_obj.oabnumber,
        areasofexpertise=user_obj.areasofexpertise,
        phone=user_obj.phone,
        created=str(datetime.now())
        )
    db.add(db_user)
    db.commit()
    db.refresh(db_user)
    return db_user

def delete_user(db: Session, user_uuid: str):
    db.query(models.User).filter_by(uuid=user_uuid).delete()
    #db.query(models.User).filter(models.User.uuid == user_uuid).delete()
    #db.query(models.User).delete().where(models.User.uuid == user_uuid)
    db.commit()
    return True

def update_user(db:Session, db_obj: models.User, user_obj: schemas.UserCreate):
    obj_data = jsonable_encoder(db_obj)
    print(obj_data)
    if isinstance(user_obj, dict):
        update_data = user_obj
    else:
        update_data = user_obj.dict(exclude_unset=True)
    if update_data["password"]:
        hashed_password = bcrypt.hashpw(user_obj.password.encode('utf-8'), bcrypt.gensalt(14))
        del update_data["password"]
        update_data["password"] = str(hashed_password.decode('utf-8'))
    for field in obj_data:
        if field in update_data:
            setattr(db_obj, field, update_data[field])
    db.add(db_obj)
    db.commit()
    db.refresh(db_obj)
    return db_obj

def get_credentials(db: Session, credentials: schemas.Credentials):
    print(credentials.username)
    db_credentials: schemas.Credentials
    db_credentials = db.query(models.User).filter(models.User.username == credentials.username).first()
    if db_credentials is None:
        return False
#    print(db_credentials.password)
    print(db_credentials.password.encode('utf-8'))
    print(db_credentials.uuid)
    print('User password:')
    print(credentials.password.encode('utf-8'))
    valid_pass = bcrypt.checkpw(credentials.password.encode('utf-8'), db_credentials.password.encode('utf-8'))
    print(valid_pass)
    #return db_credentials.uuid
    return valid_pass
