package com.fdmgroup.model.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fdmgroup.model.*;

public class BankAccountDAO {

	private EntityManager em;
	EntityTransaction entityTransaction;
	private ArrayList<BankAccount> bankAccountList = new ArrayList();
	
	public BankAccountDAO(EntityManager entityManager, EntityTransaction entityTransaction) {
		this.em = entityManager;
		this.entityTransaction = entityTransaction;
	}

	public ArrayList<BankAccount> getBankAccountList() {
		
		return bankAccountList;
	}

	public ArrayList<BankAccount> addBankAccountToList(BankAccount bankAccount) {

		entityTransaction.begin();
		bankAccountList.add(bankAccount);
		entityTransaction.commit();
		return bankAccountList;
	}

	public ArrayList<BankAccount> removeBankAccountFromList(BankAccount bankAccount) {

		entityTransaction.begin();
		bankAccountList.remove(bankAccount);
		entityTransaction.commit();
		
		return bankAccountList;
	}

	public BankAccount retrieveBankAccount(int accountId) {

		BankAccount bankAccount = new BankAccount();
		try{
			bankAccount = em.find(BankAccount.class, accountId);
		}
		catch (Exception e){			
		}
		return bankAccount;

	}

	public BankAccount updateBankAccount(BankAccount newbankAccount) {
		
		int accountId = newbankAccount.getAccountId();
		double balance = newbankAccount.getBalance();
		String username = newbankAccount.getUser().getUsername();
		long cardNumber = newbankAccount.getCard().getCardNumber();
		
		BankAccount bankAccount = em.find(BankAccount.class, accountId);
		entityTransaction.begin();
		bankAccount.setBalance(balance);
		bankAccount.setUser(em.find(WebsiteUser.class, username));
		bankAccount.setCard(em.find(Card.class, cardNumber));

		entityTransaction.commit();
		
		BankAccount returnedBankAccount =  em.find(BankAccount.class, accountId);
		
		return returnedBankAccount;
	}


}
