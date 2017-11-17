package com.fdmgroup.validators;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import com.fdmgroup.model.WebsiteUser;
import com.fdmgroup.model.dao.WebsiteUserDAO;

public class Validation {
	
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;	
	EntityTransaction entityTransaction;

	public boolean validate(WebsiteUser user) {
		
		entityManagerFactory = Persistence.createEntityManagerFactory("SoloProject");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		boolean usernameExists = false;
		
		
		WebsiteUserDAO websiteDao = new WebsiteUserDAO(entityManager, entityTransaction);		
		
		List<WebsiteUser> userList = websiteDao.getUserList();
		
		String username = user.getUsername();
		String password = user.getPassword();				
		
		for(WebsiteUser temp: userList){
			if(temp.getUsername().equalsIgnoreCase(username)){
				usernameExists = true;
			}

		}
		
		if(!usernameExists) return false;
		String databasePass =  websiteDao.retrieveUser(username).getPassword();
		if((databasePass.equals(password))){			
			return true;
		}
		
		return false;
	}

}
