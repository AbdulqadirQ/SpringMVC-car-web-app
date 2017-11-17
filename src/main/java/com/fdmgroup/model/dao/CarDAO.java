package com.fdmgroup.model.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fdmgroup.model.Car;

public class CarDAO {

	private EntityManager em;
	EntityTransaction entityTransaction;
	private ArrayList<Car> carList = new ArrayList();
	
	public CarDAO(EntityManager entityManager, EntityTransaction entityTransaction) {
		this.em = entityManager;
		this.entityTransaction = entityTransaction;
	}

	public ArrayList<Car> getCarList() {
		
		return carList;
	}

	public ArrayList<Car> addCarToList(Car car) {

		entityTransaction.begin();
		carList.add(car);
		entityTransaction.commit();
		return carList;
	}

	public ArrayList<Car> removeCarFromList(Car car) {

		entityTransaction.begin();
		carList.remove(car);
		entityTransaction.commit();
		
		return carList;
	}

	public Car retrieveCar(int carId) {

		Car car = new Car();
		try{
			car = em.find(Car.class, carId);
		}
		catch (Exception e){			
		}
		return car;

	}

	public Car updateCar(Car newcar) {
		
		int carId = newcar.getCarId();
		String make = newcar.getMake();
		String carModel = newcar.getCarModel();
		int mileage = newcar.getMileage();
		int carRating = newcar.getCarRating();
		
		Car car = em.find(Car.class, carId);
		entityTransaction.begin();
		car.setMake(make);
		car.setCarModel(carModel);
		car.setMileage(mileage);
		car.setCarRating(carRating);

		entityTransaction.commit();
		
		Car returnedCar =  em.find(Car.class, carId);
		
		return returnedCar;
	}

}
