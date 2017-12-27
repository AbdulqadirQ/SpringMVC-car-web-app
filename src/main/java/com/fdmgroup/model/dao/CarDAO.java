package com.fdmgroup.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import com.fdmgroup.model.Car;
import com.fdmgroup.model.WebsiteUser;

public class CarDAO {

	private EntityManager em;
	EntityTransaction entityTransaction;
	private List<Car> carList;
	
	public CarDAO(EntityManager entityManager, EntityTransaction entityTransaction) {
		this.em = entityManager;
		this.entityTransaction = entityTransaction;
	}

	public List<Car> getCarList() {
		
		TypedQuery<Car> queryAllUsers = em.createQuery("SELECT p FROM Car p", Car.class);
		carList = queryAllUsers.getResultList();
		
		return carList;
	}

	public List<Car> addCarToList(Car car) {

		entityTransaction.begin();
		carList.add(car);
		entityTransaction.commit();
		return carList;
	}

	public List<Car> removeCarFromList(Car car) {

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
	
	public void createCar(Car car) {
		entityTransaction.begin();
		em.persist(car);
		entityTransaction.commit();
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
