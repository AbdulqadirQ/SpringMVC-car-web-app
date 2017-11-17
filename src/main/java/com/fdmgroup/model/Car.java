package com.fdmgroup.model;
import javax.persistence.*;

@Entity
public class Car {
	
	@Id
	private int carId;
	private String make;
	private String carModel;
	private int mileage;
	private int carRating;
	public int getCarId() {
		return carId;
	}
	public void setCarId(int carId) {
		this.carId = carId;
	}
	public String getMake() {
		return make;
	}
	public void setMake(String make) {
		this.make = make;
	}
	public String getCarModel() {
		return carModel;
	}
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}
	public int getMileage() {
		return mileage;
	}
	public void setMileage(int mileage) {
		this.mileage = mileage;
	}
	public int getCarRating() {
		return carRating;
	}
	public void setCarRating(int carRating) {
		this.carRating = carRating;
	}
	


	
}
