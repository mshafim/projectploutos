from flask import jsonify
import sys, os


# for creating the mapper code
from sqlalchemy import Column, ForeignKey, Integer, String, Float

# for configuration and class code
from sqlalchemy.ext.declarative import declarative_base

# for creating foreign key relationship between the tables
from sqlalchemy.orm import relationship
from sqlalchemy.orm import sessionmaker

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

def makeANewAccount(account_type, username, password, balance):
    addedaccount = Account(account_type=account_type, username=username, password=password, balance=balance)
    session.add(addedaccount)
    session.commit()
    # return jsonify(Account=addedaccount.serialize)

# creates a create_engine instance at the bottom of the file
engine = create_engine("sqlite:///accounts-collection.db");

# Base.metadata.drop_all(bind=engine)
Base.metadata.create_all(engine)

# DBSession = sessionmaker(bind=engine)
# session = DBSession() # this is in reference to accessing the latter database
#
# makeANewAccount("Venmo", "savila", "league", 1504.89)
# makeANewAccount("Zelle", "rmahmud", "newjersey", 2000.02)
# makeANewAccount("Cashapp", "mshafim", "b2", 1019.34)
# makeANewAccount("Venmo", "lcastro", "chipotle", 512.4)
# makeANewAccount("Zelle", "jsmith", "johnny", 614.45)
