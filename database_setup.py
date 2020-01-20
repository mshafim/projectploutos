import sys, os

# for creating the mapper code
from sqlalchemy import Column, ForeignKey, Integer, String, Float

# for configuration and class code
from sqlalchemy.ext.declarative import declarative_base

# for creating foreign key relationship between the tables
from sqlalchemy.orm import relationship

# for configuration
from sqlalchemy import create_engine

from flask import Flask


# create declarative_base instance
Base = declarative_base()

# classes
class Account(Base):
	__tablename__ = "accounts"

	id = Column(Integer(), primary_key=True)
	account_type = Column(String(250), nullable=False)
	username = Column(String(250), nullable=False)
	password = Column(String(250), nullable=False)
	balance = Column(Float(), nullable=False)

	@property
	def serialize(self):
		return {
			'id': self.id,
			'account_type': self.account_type,
			'username': self.username,
			'password': self.password,
			'balance': '$' + str(self.balance)
		}
	


# creates a create_engine instance at the bottom of the file
engine = create_engine("sqlite:///accounts-collection.db")

Base.metadata.create_all(engine)
