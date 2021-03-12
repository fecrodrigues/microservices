from sqlalchemy.orm import Session
from . import models, schemas
from datetime import datetime
import bcrypt

def get_user(db: Session, user_uuid: str):
    return db.query(models.User).filter(models.User.uuid == user_uuid).first()

def get_user_by_username(db: Session, username: str):
    return db.query(models.User).filter(models.User.username == username).first()

def get_users(db: Session, skip: int = 0, limit: int = 100):
    return db.query(models.User).offset(skip).limit(limit).all()

def create_user(db: Session, user: schemas.UserCreate):
    
    hashed_password = bcrypt.hashpw(user.password.encode('utf-8'), bcrypt.gensalt(14))
    print(hashed_password)
    print(user.password.encode('utf-8'))
    print(bcrypt.checkpw(user.password.encode('utf-8'), hashed_password))
    db_user = models.User(
        username=user.username,
        password=str(hashed_password.decode('utf-8')),
        advocate=user.advocate,
        biography=user.biography,
        birthdate=user.birthdate,
        firstname=user.firstname,
        lastname=user.lastname,
        oabnumber=user.oabnumber,
        areasofexpertise=user.areasofexpertise,
        phone=user.phone,
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

def get_credentials(db: Session, credentials: schemas.Credentials):
    print(credentials.username)
    db_credentials: schemas.Credentials
    db_credentials = db.query(models.User).filter(models.User.username == credentials.username).first()
    if db_credentials is None:
        return False
#    print(db_credentials.password)
    print(db_credentials.password.encode('utf-8'))
    print('User password:')
    print(credentials.password.encode('utf-8'))
    valid_pass = bcrypt.checkpw(credentials.password.encode('utf-8'), db_credentials.password.encode('utf-8'))
    print(valid_pass)
    return valid_pass
