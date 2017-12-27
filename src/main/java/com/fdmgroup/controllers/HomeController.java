package com.fdmgroup.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fdmgroup.model.Car;
import com.fdmgroup.model.WebsiteUser;
import com.fdmgroup.model.dao.CarDAO;
import com.fdmgroup.validators.Validation;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class HomeController {
	
	private static final String UPLOAD_DIRECTORY ="C:/Users/Abdulqadir.Qasim/JavaEclipseWorkspace/SoloProject/src/main/webapp/resources/images";

	@RequestMapping(value = "/") // short-hand for localhost@8088..../[page].jsp
	public String loginHandler(Model model) {
		model.addAttribute("user", new WebsiteUser()); // sends a blank user object to 'login' page
		return "home";
	}
	
	@RequestMapping(value = "home") // short-hand for localhost@8088..../[page].jsp
	public String homePage(Model model, HttpServletRequest request) {
		model.addAttribute("user", new WebsiteUser()); // sends a blank user object to 'login' page
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SoloProject");
		EntityManager entityManager = entityManagerFactory.createEntityManager();	
		EntityTransaction entityTransaction = entityManager.getTransaction();
		CarDAO carDao = new CarDAO(entityManager, entityTransaction);
		List<Car> carList = carDao.getCarList();
		request.setAttribute("carList", carList);
		//model.addAttribute("carList", carList);
		return "home";
	}

	// All POST requests for "submitLogin" handled by loginSuccess.jsp or login.jsp
	// You must add the user as a model attribute and pass it to the page

	@RequestMapping(value = "submitLogin")
	public String loginSubmitHandler(Model model, WebsiteUser user,HttpServletRequest request) {
		model.addAttribute("user", user);
		Validation validation = new Validation();
		boolean result = validation.validate(user);
		if (result == true)
			return "loginSuccess"; // calls loginSuccess jsp page
		else{
			request.setAttribute("message", "Wrong login and password");
			return "login"; // calls login jsp page 
		}
	}

	// register
	// ALL requests for "register" handled by register.jsp

	@RequestMapping(value = "register")
	public String registrationHandler(Model model) {
		model.addAttribute("user", new WebsiteUser());
		return "register";
	}

	// All requests for submitRegister handled by registerSuccess.jsp

	@RequestMapping(value = "submitRegister")
	public String submitRegistrationHandler(Model model, WebsiteUser user, HttpServletRequest request) {
		model.addAttribute("user", user);
		String passwordConfirm = (String) request.getParameter("passwordConfirm");
		System.out.println("controller check" + passwordConfirm);
		Validation validation = new Validation();
		boolean result = validation.validateRegister(user, passwordConfirm);
		if (result == true){
			request.setAttribute("message", "You have registered successfully!");
			return "register"; 
		}
		else{
			request.setAttribute("message", "Invalid details entered. Please try again");
			return "register"; 
		}
	}
	
	@RequestMapping(value = "listCar")
	public ModelAndView listingHandler(Model model) {
		model.addAttribute("car", new Car());
		return new ModelAndView("listCar");
	}

	// All requests for submitRegister handled by registerSuccess.jsp

	@RequestMapping(value = "submitListing")
	public String submitListingHandler(Model model, Car car, HttpServletRequest request) {
		model.addAttribute("car", car);
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SoloProject");;
		EntityManager entityManager = entityManagerFactory.createEntityManager();	
		EntityTransaction entityTransaction = entityManager.getTransaction();
		CarDAO carDao = new CarDAO(entityManager, entityTransaction);
		int carID = carDao.getCarList().size()+1;
		car.setCarId(carID);
		System.out.println(car.getCarId());System.out.println(car.getMake());
		carDao.createCar(car);				

		return "listingSuccess";		
	}
	
	@RequestMapping("uploadform")  
    public ModelAndView uploadForm(){  
        return new ModelAndView("uploadform");    
    }  
    
    @RequestMapping(value="savefile",method=RequestMethod.POST)  
    public ModelAndView saveimage(@RequestParam MultipartFile file) throws Exception{  

	    //String path = context.getRealPath(UPLOAD_DIRECTORY);  
	    String filename = file.getOriginalFilename();  
	  
	    System.out.println("****FILE PATH****" + UPLOAD_DIRECTORY+" "+filename);        
	  
	    byte[] bytes = file.getBytes();  
	    BufferedOutputStream stream =new BufferedOutputStream(new FileOutputStream(  
	         new File(UPLOAD_DIRECTORY + File.separator + filename)));  
	    stream.write(bytes);  
	    stream.flush();  
	    stream.close();  
           
    return new ModelAndView("uploadform","filesuccess","File successfully saved!");  
    
    }
	
	@RequestMapping(value = "buyPage")
	public String handleCarBid(Model model,HttpServletRequest request) {
		int carId = Integer.parseInt(request.getParameter("carId"));
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("SoloProject");
		EntityManager entityManager = entityManagerFactory.createEntityManager();	
		EntityTransaction entityTransaction = entityManager.getTransaction();
		CarDAO carDao = new CarDAO(entityManager, entityTransaction);
		Car car = carDao.retrieveCar(carId);
		request.setAttribute("carIdSent", car);
		return "buyPage";
	}
}