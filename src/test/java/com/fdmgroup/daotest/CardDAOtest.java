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

public class CardDAOtest {

	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;		
	EntityTransaction entityTransaction;
	CardDAO cardDao;
	
	@Before
	public void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this); 
		//userDao = new WebsiteUserDAO(em);
		
		entityManagerFactory = Persistence.createEntityManagerFactory("SoloProject");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		
		cardDao = new CardDAO(entityManager, entityTransaction);
	}
	
	@Test
	public void test_cardListReturnsEmptyList(){
		
		ArrayList<Card> card = cardDao.getCardList();	
		entityManager.close();
		entityManagerFactory.close();
		assertEquals(0, card.size());

	}
	
	@Test
	public void test_cardListReturnsSizeOneWhenOneItemIsAdded(){		
		Card card = new Card();
		
		ArrayList<Card> cardList = cardDao.addCardToList(card);
		assertEquals(1, cardList.size());		
	}
	
	@Test
	public void test_cardListReturnsSizeTwoWhenTwoItemsAreAdded(){		
		Card card1 = new Card();
		Card card2 = new Card();
		
		ArrayList<Card> cardList = cardDao.addCardToList(card1);
		cardList = cardDao.addCardToList(card2);
		
		assertEquals(2, cardList.size());		
	}
	
	@Test
	public void test_cardListReturnsSizeZeroWhenOneItemIsaddedThenRemoved(){		
		Card card = new Card();
		
		ArrayList<Card> cardList = cardDao.addCardToList(card);
		cardList = cardDao.removeCardFromList(card);
		
		assertEquals(0, cardList.size());		
	}
	
	@Test
	public void test_cardListReturnsSizOneWhenOneUserObjectIsaddedThenANonExistentUserObjectIsRemoved(){		
		Card card1 = new Card();
		
		ArrayList<Card> cardList = cardDao.addCardToList(card1);
		
		Card card2 = new Card();
		cardList = cardDao.removeCardFromList(card2);		
		
		assertEquals(1, cardList.size());		
	}
	
	@Test
	public void test_retrieveCardReturnsNullWhenNonExistantCardIsInput() {
		
		Card user = cardDao.retrieveCard(999);
		
		assertEquals(null, user);		
	}
	
	@Test
	public void test_retrieveCardReturnsRelevantObjectWhenCardNumber7772313471331340isInput() {
		
		Card card = cardDao.retrieveCard(7772313471331340L);

		assertEquals(7772313471331340L, card.getCardNumber());		
	}
	
	@Test
	public void test_CallupdateCardToChangeStartDateOfCardObjectWithCardNumber7467651811465078To0101(){		

		Card card = new Card();
		card.setCardNumber(7467651811465078L);
		card.setStartDate("0101");

		String startDate = cardDao.updateCard(card).getStartDate();

		assertEquals("0101", startDate);
	}
	
	@Test
	public void test_CallupdateCardToChangeEndDateOfCardObjectWithCardNumber7467651811465078To0909(){		

		Card card = new Card();
		card.setCardNumber(7467651811465078L);
		card.setEndDate("0909");

		String endDate = cardDao.updateCard(card).getEndDate();

		assertEquals("0909", endDate);
	}
	
	@Test
	public void test_CallupdateCardToChangecardTypeOfCardObjectWithCardNumber7467651811465078Tomastercard(){		

		Card card = new Card();
		card.setCardNumber(7467651811465078L);
		card.setCardType("mastercard");		
		
		String cardType = cardDao.updateCard(card).getCardType();

		assertEquals("mastercard", cardType);
	}
}
