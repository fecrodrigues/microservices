from sqlalchemy import Boolean, Column, ForeignKey, Integer, String, ARRAY
from sqlalchemy.dialects.postgresql import UUID
from sqlalchemy.orm import relationship
import uuid
from .database import Base


class User(Base):
    __tablename__ = "users"

    #uuid = Column(Integer, primary_key=True, index=True, unique=True, nullable=False)
    uuid = Column(UUID(as_uuid=True), primary_key=True, default=uuid.uuid4, index=True, unique=True, nullable=False)
    username = Column(String, unique=True, index=True, nullable=False)
    password = Column(String, nullable=False)
    advocate = Column(Boolean, nullable=False)
    biography = Column(String, nullable=True)
    birthdate = Column(String, nullable=True)
    firstname = Column(String, nullable=False)
    lastname  = Column(String, nullable=True)
    oabnumber = Column(String, nullable=True)
    areasofexpertise = Column(ARRAY(String), nullable=True)
    phone = Column(String, nullable=True)
    created = Column(String, nullable=True)
