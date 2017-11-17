package com.fdmgroup.controllers;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.fdmgroup.model.WebsiteUser;
import com.fdmgroup.validators.Validation;

import static org.springframework.web.bind.annotation.RequestMethod.*;

@Controller
public class HomeController {

	// Things to notice throughout this code:

	// 1. Each controller class must be marked with an @Controller annotation

	// 2. method names can be called anything but make them meaningful/descriptive
	//
	// 3. Each controller class can have many methods but if we have too many we 
	// may need to have a separate controller for different sections of the site. 
	//    e.g. ItemController, OrderController, UserController and so on. 
	// The class name can be called anything but keep it descriptive
	//
	// 4. Methods can have any arguments you need. e.g. HttpServletRequest, 
	// HttpSession and so on. The class called Model is built into SpringMVC 
	// and is used as a container used to transfer things between your controller
	// methods and the .jsp file.  
	//
	// 5. RequestMapping attributes for each method and each method returns a string. 
	//    This is used to determine the name of the file which will be displayed
	//
	// 6. Model is a class built into SpringMVC
	//
	// 7. Across all the controllers in your application, the RequestMapping 
	// attributes must be unique. i.e. you cannot have 2 RequestMapping values
	// with the same value
	// Lets look at each of the controllers..

	
	// All requests for "/" sent to login.jsp
	// In other words, http://localhost:8088/<APPLICATION_NAME> /
	
	// send a Model attribute called "user" to that page
	// the Model attribute Object is a blank User object:   new User() 
	// this will be populated when the web page is filled 
	// with information in the page login.jsp
/*
	@RequestMapping(value = "listItems") // calls the @RequestMapping 'listItems' within login.jsp
	public String listItems(Model model){ // Model is part of SpringMVC
		
		ItemDAO itemDAO = new ItemDAO();
		List<Item> listOfItems = itemDAO.ListItems(); // method
		
		// listItems is the attribute name
		model.addAttribute("listItems", listOfItems); // listOfItems is the java variable
		return "listTheItems"; // 'listTheItems is the name of the jsp page
	}
*/	
	@RequestMapping(value = "/") // short-hand for localhost@8088..../[page].jsp
	public String loginHandler(Model model) {
		model.addAttribute("user", new WebsiteUser()); // sends a blank user object to 'login' page
		return "home";
	}

	// All POST requests for "submitLogin" handled by loginSuccess.jsp or login.jsp
	// You must add the user as a model attribute and pass it to the page

	@RequestMapping(value = "submitLogin", method = POST)
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
	public String submitRegistrationHandler(Model model, WebsiteUser user) {
		model.addAttribute("user", user);
		return "registerSuccess";
	}

	@RequestMapping(value = "passMyUser")
	public String passMyUserWithAttribute(@ModelAttribute("user") WebsiteUser user) {
		//UsersDAO usersDAO = new UsersDAO();
		//usersDAO.createUser(user);
		System.out.println("The username entered was: "+user.getUsername());
		return "testPage";
	}
	
	// GET requests for testPage handled by testPage.jsp

	@RequestMapping(value = "testPage", method = GET)
	public String testPageHandler() {
		return "testPage";
	}

	// GET requests for /testPage/{value}/{valueString} sent to testpage.jsp
	// and we pass the value attribute and valueString attribute to that page and display them
	// notice in the argument list how we define the type of each one
	
	@RequestMapping(value = "testPage/{valueInt}/{valueString}", method = GET)
	public String testPageHandlerWithPathVariables(Model model, @PathVariable int valueInt,@PathVariable String valueString) {
		model.addAttribute("valueInt", valueInt);
		model.addAttribute("valueString", valueString);
		return "testPage";
	}

	// ALL requests for /PassMap handled by newpage.jsp
	// and we set a value attribute and pass it to testPage.jsp

	@RequestMapping(value = "passMap")
	public String PassMap(Map<String, String> model) {
		model.put("value", "passmap");
		return "testPage";
	}

	// ALL requests for /passModelMap -> testPage.jsp
	// and we set a value attribute and pass it to testPage.jsp

	@RequestMapping(value = "passModelMap")
	public String passModelMap(ModelMap model) {
		model.put("value", "passmodelmap");
		return "testPage";
	}

	// call this handler with argument /passModelAttribute?modelatt=.....
	// ALL requests for /passModelAttribute -> testPage.jsp
	// and we set the value attribute and pass it to testPage.jsp

	@RequestMapping(value = "passModelAttribute")
	public String passModelMap(@ModelAttribute("modelatt") String attribute, ModelMap model) {
		model.put("value", attribute);
		return "testPage";
	}
}