package com.fdmgroup.model;

import javax.persistence.*;

@Entity
public class BankAccount {
	
	@Id
	private int accountId;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "username", name = "username")
	private WebsiteUser user; 
/*	
	private String username;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
*/		
	private double balance;
	
	@OneToOne
	@JoinColumn(referencedColumnName = "cardNumber", name = "cardNumber")
	private Card card;	
	
/*	private long cardNumber;
	public long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(long cardNumber) {
		this.cardNumber = cardNumber;
	}
*/	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public WebsiteUser getUser() {
		return user;
	}
	public void setUser(WebsiteUser user) {
		this.user = user;
	}
	public Card getCard() {
		return card;
	}
	public void setCard(Card card) {
		this.card = card;
	}
	
	
}
