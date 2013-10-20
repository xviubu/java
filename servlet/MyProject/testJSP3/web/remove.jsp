<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:set var="userStatus" scope="request" value="Brilliant"/>
		userStatus: ${userStatus} <br/>
		<c:remove var="userStatus" scope="request" />
		userStatus is now : ${userStatus} <br/>
	</body>
</html>
