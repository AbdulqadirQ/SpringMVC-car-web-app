package com.fdmgroup.model.dao;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import com.fdmgroup.model.WebsiteUser;

public class WebsiteUserDAO {

	private EntityManager em;
	private EntityTransaction entityTransaction;
	private List<WebsiteUser> userList = new ArrayList();

	public WebsiteUserDAO(EntityManager entityManager, EntityTransaction entityTransaction) {
		this.em = entityManager;
		this.entityTransaction = entityTransaction;
	}

	public List<WebsiteUser> getUserList() {
		
		TypedQuery<WebsiteUser> queryAllUsers = em.createQuery("SELECT p FROM WebsiteUser p", WebsiteUser.class);
		userList = queryAllUsers.getResultList();
		
		return userList;
	}

	public List<WebsiteUser> addUserToList(WebsiteUser user) {

		entityTransaction.begin();
		userList.add(user);
		entityTransaction.commit();
		return userList;

	}

	// creates user in database
	public void createUser(WebsiteUser user) {
		entityTransaction.begin();
		em.persist(user);
		entityTransaction.commit();
	}

	public List<WebsiteUser> removeUserFromList(WebsiteUser user) {
		entityTransaction.begin();
		//removeUser(user);
		userList.remove(user);
		entityTransaction.commit();

		return userList;
	}

	// removes uers from database
	public void removeUser(WebsiteUser user) {
		if (user != null) {
			em.remove(user);
		}
	}

	public WebsiteUser retrieveUser(String username) {
		WebsiteUser user = new WebsiteUser();
		try {
			user = em.find(WebsiteUser.class, username);
		} catch (Exception e) {
		}
		return user;
	}

	public WebsiteUser updateUser(WebsiteUser newWebsiteUser) {

		String username = newWebsiteUser.getUsername();
		String password = newWebsiteUser.getPassword();
		String email = newWebsiteUser.getEmail();
		String firstName = newWebsiteUser.getFirstName();
		String lastName = newWebsiteUser.getLastName();
		int sellerRating = newWebsiteUser.getSellerRating();
		
		WebsiteUser user = em.find(WebsiteUser.class, username);
		
		entityTransaction.begin();
		user.setPassword(password);
		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setSellerRating(sellerRating);
		entityTransaction.commit();
		//em.persist(user);

		WebsiteUser returnedUser = em.find(WebsiteUser.class, username);

		return returnedUser;

	}

}
