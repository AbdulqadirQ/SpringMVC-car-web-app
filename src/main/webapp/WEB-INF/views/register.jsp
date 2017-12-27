<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Register</title>
<link rel="stylesheet"  type="text/css" href='<c:url value="/resources/css/homeStyle.css" />'>
</head>
<body>

	<h1>Qasim's Cars</h1>

	<h3>Register</h3>

	<div class="topnav" id="myTopnav">
		<a href="home">Home</a>
		<a href="submitLogin">Login</a>
		<a href="register">Register</a>
		<a href="listCar">List Your Car</a>
	</div>

	<!-- img src="<c:url value="/resources/image.jpg" />"> -->
	
	<sf:form action="submitRegister" method="POST" modelAttribute="user" >
		<sf:label path="username">Username:</sf:label>
		<sf:input path="username"  size="30" />
		<br />
		<sf:label path="password">Password:</sf:label>
		<sf:input path="password" size="30" />
		<br />
		<label >Confirm Password:</label>
		<input name="passwordConfirm" size="30" />
		<br />
		<sf:label path="email">Email:</sf:label>
		<sf:input path="email" size="30" />
		<br />
		<sf:label path="firstName">Firstname:</sf:label>
		<sf:input path="firstName" size="30" />
		<br />
		<sf:label path="lastName">Lastname:</sf:label>
		<sf:input path="lastName" size="30" />
		<br />
		<input type="submit" name="commit" value="submit" />	
	</sf:form>
	
	<B>${requestScope.message}</B>

</body>
</html>