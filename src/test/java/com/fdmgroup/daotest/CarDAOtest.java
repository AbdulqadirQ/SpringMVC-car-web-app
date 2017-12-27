package com.fdmgroup.daotest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.model.*;
import com.fdmgroup.model.dao.*;

public class CarDAOtest {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;		
	EntityTransaction entityTransaction;
	CarDAO carDao;
	
	@Before
	public void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this); 
		//userDao = new WebsiteUserDAO(em);
		
		entityManagerFactory = Persistence.createEntityManagerFactory("SoloProject");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		
		carDao = new CarDAO(entityManager, entityTransaction);
	}
	
	@Test
	public void test_carListReturnsEmptyList(){
		
		List<Car> car = carDao.getCarList();	
		entityManager.close();
		entityManagerFactory.close();
		assertEquals(0, car.size());

	}
	
	@Test
	public void test_carListReturnsSizeOneWhenOneItemIsAdded(){		
		Car car = new Car();
		
		List<Car> carList = carDao.addCarToList(car);
		assertEquals(1, carList.size());		
	}
	
	@Test
	public void test_carListReturnsSizeTwoWhenTwoItemsAreAdded(){		
		Car car1 = new Car();
		Car car2 = new Car();
		
		List<Car> carList = carDao.addCarToList(car1);
		carList = carDao.addCarToList(car2);
		
		assertEquals(2, carList.size());		
	}
	
	@Test
	public void test_carListReturnsSizeZeroWhenOneItemIsaddedThenRemoved(){		
		Car car = new Car();
		
		List<Car> carList = carDao.addCarToList(car);
		carList = carDao.removeCarFromList(car);
		
		assertEquals(0, carList.size());		
	}
	
	@Test
	public void test_carListReturnsSizOneWhenOneUserObjectIsaddedThenANonExistentUserObjectIsRemoved(){		
		Car car1 = new Car();
		
		List<Car> carList = carDao.addCarToList(car1);
		
		Car car2 = new Car();
		carList = carDao.removeCarFromList(car2);		
		
		assertEquals(1, carList.size());		
	}
	
	@Test
	public void test_retrieveCarReturnsNullWhenNonExistantCarIsInput() {
		
		Car user = carDao.retrieveCar(999);
		
		assertEquals(null, user);		
	}
	
	@Test
	public void test_retrieveCarReturnsRelevantObjectWhenCarId5isInput() {
		
		Car car = carDao.retrieveCar(5);

		assertEquals(43000, car.getMileage());		
	}
	
	@Test
	public void test_CallupdateCarToChangeMakeOfCarObjectWithCarId1ToVolvo(){		

		Car car = new Car();
		car.setCarId(1);
		car.setMake("volvo");

		String make = carDao.updateCar(car).getMake();

		assertEquals("volvo", make);
	}
	
	@Test
	public void test_CallupdateCarToChangecarModelOfCarObjectWithCarId1ToXC90(){		

		Car car = new Car();
		car.setCarId(1);
		car.setCarModel("XC90");
		
		
		String carModel = carDao.updateCar(car).getCarModel();

		assertEquals("XC90", carModel);
	}
	
	@Test
	public void test_CallupdateCarToChangeMileageOfCarObjectWithCarId1To35000(){		

		Car car = new Car();
		car.setCarId(1);
		car.setMileage(35000);
		
		
		int mileage = carDao.updateCar(car).getMileage();

		assertEquals(35000, mileage);
	}
	
	@Test
	public void test_CallupdateCarToChangecarRatingOfCarObjectWithCarId1To10(){		

		Car car = new Car();
		car.setCarId(1);
		car.setCarRating(10);
		
		
		int carRating = carDao.updateCar(car).getCarRating();

		assertEquals(10, carRating);
	}

}
