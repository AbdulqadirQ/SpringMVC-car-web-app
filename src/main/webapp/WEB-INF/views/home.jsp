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
</head>
<body>

	<h1>Qasim's Cars</h1>

	<h3>Login</h3>

	<sf:form action="submitLogin" method="POST" modelAttribute="user">
		<!-- 'submiteLogin' and 'user' must be the same as defined in HomeController.java class -->
		<sf:label path="username">Username</sf:label>
		<sf:input path="username" size="30" value="user" />
		<br />
		<sf:label path="password">Password</sf:label>
		<sf:input path="password" size="30" value="password" />
		<input type="submit" name="commit" value="submit" />
	</sf:form>
	


</body>
</html>