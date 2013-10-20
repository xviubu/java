<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isErrorPage="true" %>
<html>
	<body>
		About to be a risky thing: <br>
		<c:catch var="myException">
			Inside the catch ...
			<% int x = 10/0 ;%>
		</c:catch>
		<c:if test="${myException != null}">
		The was an Exception: ${myException}  <br>
		</c:if>

		If you see this,we survived.
	</body>
</html>
