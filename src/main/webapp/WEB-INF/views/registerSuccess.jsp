<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<html>
<head>
<title>Registration Successful</title>
</head>
<body>

	<h1>Registration Successful</h1>
	
	You are now registered!!<BR>
	
	Username was: ${user.username} <BR>
	Password was: ${user.password} <BR>
	Email was: ${user.email} <BR>
	

</body>
</html>