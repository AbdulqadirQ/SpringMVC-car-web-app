<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<title>Register</title>
</head>
<body>


<h1>Register</h1>

	<!-- img src="<c:url value="/resources/image.jpg" />"> -->
	
	<sf:form action="submitRegister" method="POST" modelAttribute="user" >
		<sf:label path="username">Username</sf:label>
		<sf:input path="username"  size="30" />
		<br />
		<sf:label path="password">Password</sf:label>
		<sf:input path="password" size="30" />
		<br />
		<sf:label path="email">Email</sf:label>
		<sf:input path="email" size="30" />
		<br />
		<input type="submit" name="commit" value="submit" />	
	</sf:form>

</body>
</html>