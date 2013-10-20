<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
	<body>
		<c:choose>
			<c:when test="${userPref == 'P'}" >
				Now you can stop even if <em>do<em> drive insanely fast .
			</c:when>
			<c:when test="${userPref == 'S'}" >
			 	Our breaks will never look up,no matter how bad a  driver you are.
			</c:when>
			<c:when test="${userPref == 'M'}" >
				Lost , lost ,lost.
			</c:when>
			<c:otherwise>
				Our breaks are best .
			</c:otherwise>
		</c:choose>
	</body>
</html>
