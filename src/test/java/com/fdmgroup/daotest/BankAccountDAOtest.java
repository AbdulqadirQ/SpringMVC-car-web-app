package com.fdmgroup.daotest;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.model.*;
import com.fdmgroup.model.dao.*;

public class BankAccountDAOtest {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;		
	EntityTransaction entityTransaction;
	BankAccountDAO bankAccountDao;
	
	@Before
	public void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this); 
		//userDao = new WebsiteUserDAO(em);
		
		entityManagerFactory = Persistence.createEntityManagerFactory("SoloProject");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		
		bankAccountDao = new BankAccountDAO(entityManager, entityTransaction);
	}
	
	@Test
	public void test_bankAccountListReturnsEmptyList(){
		
		ArrayList<BankAccount> bankAccount = bankAccountDao.getBankAccountList();	
		entityManager.close();
		entityManagerFactory.close();
		assertEquals(0, bankAccount.size());

	}
	
	@Test
	public void test_bankAccountListReturnsSizeOneWhenOneItemIsAdded(){		
		BankAccount bankAccount = new BankAccount();
		
		ArrayList<BankAccount> bankAccountList = bankAccountDao.addBankAccountToList(bankAccount);
		assertEquals(1, bankAccountList.size());		
	}
	
	@Test
	public void test_bankAccountListReturnsSizeTwoWhenTwoItemsAreAdded(){		
		BankAccount bankAccount1 = new BankAccount();
		BankAccount bankAccount2 = new BankAccount();
		
		ArrayList<BankAccount> bankAccountList = bankAccountDao.addBankAccountToList(bankAccount1);
		bankAccountList = bankAccountDao.addBankAccountToList(bankAccount2);
		
		assertEquals(2, bankAccountList.size());		
	}
	
	@Test
	public void test_bankAccountListReturnsSizeZeroWhenOneItemIsaddedThenRemoved(){		
		BankAccount bankAccount = new BankAccount();
		
		ArrayList<BankAccount> bankAccountList = bankAccountDao.addBankAccountToList(bankAccount);
		bankAccountList = bankAccountDao.removeBankAccountFromList(bankAccount);
		
		assertEquals(0, bankAccountList.size());		
	}
	
	@Test
	public void test_bankAccountListReturnsSizOneWhenOneUserObjectIsaddedThenANonExistentUserObjectIsRemoved(){		
		BankAccount bankAccount1 = new BankAccount();
		
		ArrayList<BankAccount> bankAccountList = bankAccountDao.addBankAccountToList(bankAccount1);
		
		BankAccount bankAccount2 = new BankAccount();
		bankAccountList = bankAccountDao.removeBankAccountFromList(bankAccount2);		
		
		assertEquals(1, bankAccountList.size());		
	}
	
	@Test
	public void test_retrieveBankAccountReturnsNullWhenNonExistantBankAccountIsInput() {
		
		BankAccount user = bankAccountDao.retrieveBankAccount(999);
		
		assertEquals(null, user);		
	}
	
	@Test
	public void test_retrieveBankAccountReturnsRelevantObjectWhenId5isInput() {
		
		BankAccount bankAccount = bankAccountDao.retrieveBankAccount(5);

		assertEquals(860.01, bankAccount.getBalance(),0.001);		
	}
	
	@Test
	public void test_CallupdateBankAccountToChangeBalanceOfBankAccountObjectWithAccountId1To500Point50(){		

		BankAccount bankAccount = new BankAccount();
		bankAccount.setAccountId(1);
		bankAccount.setBalance(500.50);
		
		
		double balance = bankAccountDao.updateBankAccount(bankAccount).getBalance();

		assertEquals(500.50, balance,0.001);
	}
	
	@Test
	public void test_CallupdateBankAccountToChangeWebsiteUserofBankAccountObjectWithAccountId1ToUsernameBobbo(){		

		BankAccount bankAccount = new BankAccount();
		WebsiteUser websiteUser = new WebsiteUser();
		
		//WebsiteUser websiteUser = entityManager.find(WebsiteUser.class, "samwise");
		
		
		websiteUser.setUsername("bobbo");
		websiteUser.setEmail("test@test.com");
		websiteUser.setFirstName("bob");
		websiteUser.setLastName("bo");
		websiteUser.setPassword("passwordBOB");
		websiteUser.setSellerRating(0);
		
		
		bankAccount.setUser(websiteUser);
		bankAccount.setAccountId(1);
		
		String username = bankAccountDao.updateBankAccount(bankAccount).getUser().getUsername();

		assertEquals("bobbo", username);
	}
	
	@Test
	public void test_CallupdateBankAccountToChangeCardObjectOfBankAccountObjectWithAccountId1ToCardNumber555(){		

		BankAccount bankAccount = new BankAccount();
		Card card = new Card();
		card.setCardNumber(555);
		bankAccount.setAccountId(1);		
		bankAccount.setCard(card);
		
		
		
		long cardNumber = bankAccountDao.updateBankAccount(bankAccount).getCard().getCardNumber();

		assertEquals(555, cardNumber);
	}

}
