from typing import List, Optional
from pydantic import BaseModel
from uuid import UUID
from typing import Union

class Credentials(BaseModel):
    username: str
    password: str

    class Config:
        orm_mode = True

#Commons
class UserBase(BaseModel):
    username: str
    advocate: bool
    biography: str = None
    birthdate: str = None
    firstname: str
    lastname: str = None
    oabnumber: str = None
    areasofexpertise: list = None
    phone: str = None

#Not Returned
class UserCreate(UserBase):
    password: str

#Not Send in Request
class User(UserBase):
    #uuid: int
    uuid: Union[str, UUID]
    created: str

    class Config:
        orm_mode = True