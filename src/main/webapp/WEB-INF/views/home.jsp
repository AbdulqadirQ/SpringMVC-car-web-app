<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>homepage</title>
<link rel="stylesheet"  type="text/css" href='<c:url value="/resources/css/homeStyle.css" />'>

</head>
<body>

	<h1>Qasim's Cars</h1>
	
	<div class="topnav" id="myTopnav">
		<a href="home">Home</a>
		<a href="submitLogin">Login</a>
		<a href="register">Register</a>
		<a href="listCar">List Your Car</a>
	</div>
	<input id="searchBar" type="text" name="search" placeholder="Search">
	<h4>Fiter by:</h4>
	<br/><br/><br/>
	
	<a href="uploadform">Upload Image</a>  
	
	<div class="col12 box">	
		<c:forEach var="carItem" items="${carList}" varStatus="loop">
			<form action="buyPage">
				<input type="hidden" name="carId" value="${carItem.getCarId()}" />
				<input class="carImages" type="image"  src="<c:url value='/resources/images/${carItem.getCarId()}.PNG' />" name="submit"/>
			</form>			
		</c:forEach>

	</div>


</body>
</html>