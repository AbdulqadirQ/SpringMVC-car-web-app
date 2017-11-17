package com.fdmgroup.model.dao;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.fdmgroup.model.Card;

public class CardDAO {

	private EntityManager em;
	EntityTransaction entityTransaction;
	private ArrayList<Card> cardList = new ArrayList();
	
	public CardDAO(EntityManager entityManager, EntityTransaction entityTransaction) {
		this.em = entityManager;
		this.entityTransaction = entityTransaction;
	}

	public ArrayList<Card> getCardList() {
		
		return cardList;
	}

	public ArrayList<Card> addCardToList(Card card) {

		entityTransaction.begin();
		cardList.add(card);
		entityTransaction.commit();
		return cardList;
	}

	public ArrayList<Card> removeCardFromList(Card card) {

		entityTransaction.begin();
		cardList.remove(card);
		entityTransaction.commit();
		
		return cardList;
	}

	public Card retrieveCard(long cardId) {

		Card card = new Card();
		try{
			card = em.find(Card.class, cardId);
		}
		catch (Exception e){			
		}
		return card;

	}

	public Card updateCard(Card newcard) {
		
		long cardId = newcard.getCardNumber();
		String startDate = newcard.getStartDate();
		String endDate = newcard.getEndDate();
		String carType = newcard.getCardType();
		
		Card card = em.find(Card.class, cardId);
		entityTransaction.begin();
		card.setStartDate(startDate);
		card.setEndDate(endDate);
		card.setCardType(carType);

		entityTransaction.commit();
		
		Card returnedCard =  em.find(Card.class, cardId);
		
		return returnedCard;
	}

}
