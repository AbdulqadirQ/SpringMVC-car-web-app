package com.fdmgroup.model.dao;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fdmgroup.model.*;

public class TransactionDAO {

	private EntityManager em;
	EntityTransaction entityTransaction;
	private ArrayList<Transaction> transactionList = new ArrayList();
	
	public TransactionDAO(EntityManager entityManager, EntityTransaction entityTransaction) {
		this.em = entityManager;
		this.entityTransaction = entityTransaction;
	}

	public ArrayList<Transaction> getTransactionList() {
		
		return transactionList;
	}

	public ArrayList<Transaction> addTransactionToList(Transaction transaction) {

		entityTransaction.begin();
		transactionList.add(transaction);
		entityTransaction.commit();
		return transactionList;
	}

	public ArrayList<Transaction> removeTransactionFromList(Transaction transaction) {

		entityTransaction.begin();
		transactionList.remove(transaction);
		entityTransaction.commit();
		
		return transactionList;
	}

	public Transaction retrieveTransaction(int transactionId) {

		Transaction transaction = new Transaction();
		try{
			transaction = em.find(Transaction.class, transactionId);
		}
		catch (Exception e){			
		}
		return transaction;

	}

	public Transaction updateTransaction(Transaction newtransaction) {
		
		int transactionId = newtransaction.getTransactionId();
		String username = newtransaction.getUser().getUsername();
		int carId = newtransaction.getCar().getCarId();
		Date transactionTime = newtransaction.getTransactionTime();
		double price = newtransaction.getPrice();
		
		Transaction transaction = em.find(Transaction.class, transactionId);
		entityTransaction.begin();
		transaction.setUser(em.find(WebsiteUser.class, username));
		transaction.setCar(em.find(Car.class, carId));
		transaction.setTransactionTime(transactionTime);
		transaction.setPrice(price);

		entityTransaction.commit();
		
		Transaction returnedTransaction =  em.find(Transaction.class, transactionId);
		
		return returnedTransaction;
	}

}
