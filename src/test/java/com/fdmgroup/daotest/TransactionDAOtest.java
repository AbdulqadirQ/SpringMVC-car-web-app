package com.fdmgroup.daotest;

import static org.junit.Assert.*;

import java.sql.Date;
import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.model.*;
import com.fdmgroup.model.dao.*;

public class TransactionDAOtest {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;		
	EntityTransaction entityTransaction;
	TransactionDAO transactionDao;
	
	@Before
	public void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this); 
		//userDao = new WebsiteUserDAO(em);
		
		entityManagerFactory = Persistence.createEntityManagerFactory("SoloProject");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		
		transactionDao = new TransactionDAO(entityManager, entityTransaction);
	}
	
	@Test
	public void test_transactionListReturnsEmptyList(){
		
		ArrayList<Transaction> transaction = transactionDao.getTransactionList();	
		entityManager.close();
		entityManagerFactory.close();
		assertEquals(0, transaction.size());

	}
	
	@Test
	public void test_transactionListReturnsSizeOneWhenOneItemIsAdded(){		
		Transaction transaction = new Transaction();
		
		ArrayList<Transaction> transactionList = transactionDao.addTransactionToList(transaction);
		assertEquals(1, transactionList.size());		
	}
	
	@Test
	public void test_transactionListReturnsSizeTwoWhenTwoItemsAreAdded(){		
		Transaction transaction1 = new Transaction();
		Transaction transaction2 = new Transaction();
		
		ArrayList<Transaction> transactionList = transactionDao.addTransactionToList(transaction1);
		transactionList = transactionDao.addTransactionToList(transaction2);
		
		assertEquals(2, transactionList.size());		
	}
	
	@Test
	public void test_transactionListReturnsSizeZeroWhenOneItemIsaddedThenRemoved(){		
		Transaction transaction = new Transaction();
		
		ArrayList<Transaction> transactionList = transactionDao.addTransactionToList(transaction);
		transactionList = transactionDao.removeTransactionFromList(transaction);
		
		assertEquals(0, transactionList.size());		
	}
	
	@Test
	public void test_transactionListReturnsSizOneWhenOneTransactionObjectIsaddedThenANonExistentTransactionObjectIsRemoved(){		
		Transaction transaction1 = new Transaction();
		
		ArrayList<Transaction> transactionList = transactionDao.addTransactionToList(transaction1);
		
		Transaction transaction2 = new Transaction();
		transactionList = transactionDao.removeTransactionFromList(transaction2);		
		
		assertEquals(1, transactionList.size());		
	}
	
	@Test
	public void test_retrieveTransactionReturnsNullWhenNonExistantTransactionIsInput() {
		
		Transaction transaction = transactionDao.retrieveTransaction(999);
		
		assertEquals(null, transaction);		
	}
	
	@Test
	public void test_retrieveTransactionReturnsRelevantObjectWhenTransactionNumber5isInput() {
		
		double price = transactionDao.retrieveTransaction(5).getPrice();

		assertEquals(119993, price, 0.001);		
	}
	
	@Test
	public void test_CallupdateTransactionToChangeUsernameOfTransactionObjectWithTransactionNumber1Totest(){		

		Transaction transaction = new Transaction();
		transaction.setTransactionId(1);
		
		WebsiteUser user = new WebsiteUser();
		user.setUsername("test");
		transaction.setUser(user);


		String username = transactionDao.updateTransaction(transaction).getUser().getUsername();

		assertEquals("test", username);
	}
	
	@Test
	public void test_CallupdateTransactionToChangeCarIdOfTransactionObjectWithTransactionNumber1To10(){		

		Transaction transaction = new Transaction();
		transaction.setTransactionId(1);
		
		Car car = new Car();
		car.setCarId(10);
		transaction.setCar(car);


		int carId = transactionDao.updateTransaction(transaction).getCar().getCarId();

		assertEquals(10, carId);
	}
	
	@Test
	public void test_CallupdateTransactionToChangetransactionTimeOfTransactionObjectWithTransactionId1To4POINT11POINT17(){		

		Transaction transaction = new Transaction();
		transaction.setTransactionId(1);
		transaction.setTransactionTime(new Date(2017,11,04));		
		
		Date transactionTime = transactionDao.updateTransaction(transaction).getTransactionTime();

		assertEquals(new Date(2017,11,04), transactionTime);
	}
	
	@Test
	public void test_CallupdateTransactionToChangepriceOfTransactionObjectWithTransactionId1To300000(){		

		Transaction transaction = new Transaction();
		transaction.setTransactionId(1);
		transaction.setPrice(300000);		
		
		double price = transactionDao.updateTransaction(transaction).getPrice();

		assertEquals(3000000, price);
	}
}
