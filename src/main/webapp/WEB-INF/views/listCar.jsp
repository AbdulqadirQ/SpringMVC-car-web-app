<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Car</title>
<link rel="stylesheet"  type="text/css" href='<c:url value="/resources/css/homeStyle.css" />'>

</head>
<body>

	<h1>Qasim's Cars</h1>

	<h3>Enter your car details</h3>

	<div class="topnav" id="myTopnav">
		<a href="home">Home</a>
		<a href="submitLogin">Login</a>
		<a href="register">Register</a>
		<a href="listCar">List Your Car</a>
	</div>	
	
	<sf:form action="submitListing" method="POST" modelAttribute="car" >

		<sf:label path="make">Make:</sf:label>
		<sf:input path="make"  size="30" />
		<br />
		<sf:label path="carModel">Model:</sf:label>
		<sf:input path="carModel" size="30" />
		<br />
		<sf:label path="mileage">Mileage:</sf:label>
		<sf:input path="mileage" size="30" />
		<br />
		<input type="submit" name="commit" value="submit" />	
	</sf:form>
	

	<sf:form method="post" action="savefile" enctype="multipart/form-data">  
		<p><label for="image">Choose Image</label></p>  
		<p><input name="file" id="fileToUpload" type="file" /></p>  
		<p><input type="submit" value="Upload"></p>  
	</sf:form>  
	<h3 style="color:red">${filesuccess}</h3>  
	
</body>
</html>