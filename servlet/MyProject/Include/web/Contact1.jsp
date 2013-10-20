<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:import url="Header.jsp">
			<c:param name="subTitle" value="We take the thing out of SOAP." />
		</c:import>
		

		<br>
		<em>Welcome to our web services Support Group.</em><br/><br/>
		Contact us at : ${initParam.mainEmail}
	</body>
</html>
