from flask import Flask, render_template, request, redirect, url_for
app = Flask(__name__)

from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker
from database_setup import Base, Account

from flask_sqlalchemy import SQLAlchemy

# connect to database and create database session
engine = create_engine('sqlite:///accounts-collection.db?check_same_thread=False')
Base.metadata.bind = engine

DBSession = sessionmaker(bind=engine)
session = DBSession()


"""
api functions
"""
from flask import jsonify


def get_accounts():
	accounts = session.query(Account).all()
	return jsonify(accounts=[a.serialize for a in accounts])


def get_account(account_username, account_password):
    accounts = session.query(Account).filter_by(username=account_username).filter_by(password=account_password).one()
    return jsonify(accounts=accounts.serialize)


def makeANewAccount(account_type, username, password, balance):
    addedaccount = Account(account_type=account_type, username=username, password=password, balance=balance)
    session.add(addedaccount)
    session.commit()
    return jsonify(Account=addedaccount.serialize)



@app.route('/')
@app.route('/api', methods = ['GET', 'POST'])
def accountsFunction():
	if request.method == 'GET':
		return get_accounts()
	# code that code be used to create a new account
	# elif request.method == 'POST':
	#         account_type = request.args.get('account_type', '')
	#         username = request.args.get('username', '')
	#         password = request.args.get('password', '')
	#         balance = request.args.get('balance', '')
	#         return makeANewAccount(account_type, username, password, balance)

@app.route('/api/<username>/<password>')
def accountFunctionUsernamePassword(username, password):
	return get_account(username, password)



if __name__ == '__main__':
    app.debug = True
    app.run(host='0.0.0.0', port=4996)
