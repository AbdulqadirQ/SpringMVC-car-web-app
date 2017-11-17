<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Login</title>
</head>
<body>

	<h1>Login</h1>

	<sf:form action="submitLogin" method="POST" modelAttribute="user"> <!-- 'submiteLogin' and 'user' must be the same as defined in HomeController.java class -->
		<sf:label path="username">Username</sf:label>
		<sf:input path="username" size="30" value="user" />
		<br />
		<sf:label path="password">Password</sf:label>
		<sf:input path="password" size="30" value="password" />
		<input type="submit" name="commit" value="submit" />
	</sf:form>

	<B>${requestScope.message}</B>
	

	<br/><br/><br/>
	
	<a href="register">Register</a>
	
	<a href='listItems'>List the items</a> <br/>


	<!-- img src="<c:url value="/resources/image.jpg" />"> -->


</body>
</html>