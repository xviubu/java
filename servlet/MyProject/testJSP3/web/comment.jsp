<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html><body>
		<strong>Member Comments</strong> <br>
		<c:forEach  var="comment" items="${commentList}" >
			<hr>${comment}</hr>
		</c:forEach>

		<c:if test="${userType eq 'member'}">
			<jsp:include page="inputComments.jsp"/>
		</c:if>
</body></html>
