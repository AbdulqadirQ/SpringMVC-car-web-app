package com.fdmgroup.daotest;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.fdmgroup.model.*;
import com.fdmgroup.model.dao.*;

import javax.persistence.*;

public class WebsiteUserDAOtest {
/*
	@Mock
	WebsiteUserDAO userDao;
	@Mock
	EntityManager em;
	*/
	
	EntityManagerFactory entityManagerFactory;
	EntityManager entityManager;		
	EntityTransaction entityTransaction;
	WebsiteUserDAO userDao;
	
	@Before
	public void setUp() throws Exception {
		//MockitoAnnotations.initMocks(this); 
		//userDao = new WebsiteUserDAO(em);
		
		entityManagerFactory = Persistence.createEntityManagerFactory("SoloProject");
		entityManager = entityManagerFactory.createEntityManager();
		entityTransaction = entityManager.getTransaction();
		userDao = new WebsiteUserDAO(entityManager, entityTransaction);
	}
	
	@Test
	public void test_usersListReturnsEmptyList(){
		
		List<WebsiteUser> users = userDao.getUserList();	
		
		assertEquals(0, users.size());
		
	}
	
	@Test
	public void test_usersListReturnsSizeOneWhenOneItemIsAdded(){		
		WebsiteUser user = new WebsiteUser();
		
		List<WebsiteUser> users = userDao.addUserToList(user);
		
		assertEquals(1, users.size());		
	}
	
	@Test
	public void test_usersListReturnsSizeTwoWhenTwoItemsAreAdded(){		
		WebsiteUser user1 = new WebsiteUser();
		WebsiteUser user2 = new WebsiteUser();
		
		List<WebsiteUser> users = userDao.addUserToList(user1);
		users = userDao.addUserToList(user2);
		
		assertEquals(2, users.size());		
	}
	
	@Test
	public void test_usersListReturnsSizeZeroWhenOneItemIsaddedThenRemoved(){		
		WebsiteUser user = new WebsiteUser();
		
		List<WebsiteUser> users = userDao.addUserToList(user);
		users = userDao.removeUserFromList(user);
		
		
		assertEquals(0, users.size());		
	}
	
	@Test
	public void test_usersListReturnsSizOneWhenOneUserObjectIsaddedThenANonExistentUserObjectIsRemoved(){		
		WebsiteUser user1 = new WebsiteUser();
		
		List<WebsiteUser> users = userDao.addUserToList(user1);
		
		WebsiteUser user2 = new WebsiteUser();
		users = userDao.removeUserFromList(user2);		
		
		assertEquals(1, users.size());		
	}
	
	@Test
	public void test_retrieveUserReturnsNullWhenNonExistantUserIsInput() {
		
		WebsiteUser user = userDao.retrieveUser("nonExistantUser");
		assertEquals(null, user);		
	}
	
	@Test
	public void test_retrieveUserReturnsRelevantObjectWhenUsernamenCageisInput() {
		
		WebsiteUser user = userDao.retrieveUser("nCage");

		assertEquals("nick", user.getFirstName());		
	}
	
	@Test
	public void test_CallupdateUserToChangePasswordOfUserObjectWithUsernamerwesleyTopassword2(){		

		WebsiteUser newWebsiteUser = new WebsiteUser();
		newWebsiteUser.setUsername("rwesley");
		newWebsiteUser.setPassword("password2");				
		
		String password = userDao.updateUser(newWebsiteUser).getPassword();
		
		assertEquals("password2", password);
	}
	
	@Test
	public void test_CallupdateUserToChangeEmailOfUserObjectWithUsernamerwesleyTotestATfdmDOTcom(){		

		WebsiteUser newWebsiteUser = new WebsiteUser();
		newWebsiteUser.setUsername("rwesley");
		newWebsiteUser.setEmail("test@fdm.com");				
		
		String email = userDao.updateUser(newWebsiteUser).getEmail();
		
		assertEquals("test@fdm.com", email);
	}
	
	@Test
	public void test_CallupdateUserToChangefirstNameOfUserObjectWithUsernamerwesleyTotestFirstName(){		

		WebsiteUser newWebsiteUser = new WebsiteUser();
		newWebsiteUser.setUsername("rwesley");
		newWebsiteUser.setFirstName("testFirstName");			
		
		String firstName = userDao.updateUser(newWebsiteUser).getFirstName();
		
		assertEquals("testFirstName", firstName);
	}
	
	@Test
	public void test_CallupdateUserToChangeLasttNameOfUserObjectWithUsernamerwesleyTotestLastName(){		

		WebsiteUser newWebsiteUser = new WebsiteUser();
		newWebsiteUser.setUsername("rwesley");
		newWebsiteUser.setLastName("testLastName");			
		
		String lastName = userDao.updateUser(newWebsiteUser).getLastName();
		
		assertEquals("testLastName", lastName);
	}
	
	@Test
	public void test_CallupdateUserToChangeSellerRatingOfUserObjectWithUsernamerwesleyTo10(){		

		WebsiteUser newWebsiteUser = new WebsiteUser();
		newWebsiteUser.setUsername("rwesley");
		newWebsiteUser.setSellerRating(10);				
		
		int sellerRating = userDao.updateUser(newWebsiteUser).getSellerRating();
		
		assertEquals(10, sellerRating);
	}
}
