package com.fdmgroup.model;
import java.sql.Date;
import javax.persistence.*;

@Entity
public class Transaction {
	
	@Id
	private int transactionId;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "username", name = "username")
	private WebsiteUser user; 
	
	@OneToOne
	@JoinColumn(referencedColumnName = "carId", name = "carId")
	private Car car;	
	private Date transactionTime;
	private double price;
	
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}	
	public WebsiteUser getUser() {
		return user;
	}
	public void setUser(WebsiteUser user) {
		this.user = user;
	}	
	public Car getCar() {
		return car;
	}
	public void setCar(Car car) {
		this.car = car;
	}
	public Date getTransactionTime() {
		return transactionTime;
	}
	public void setTransactionTime(Date transactionTime) {
		this.transactionTime = transactionTime;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
		
}
