<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
	<head>
		<title>Jstl</title>
	</head>
	<body>
		<div class='tipBox'>
			<b>Tip of the Day</b>  <br/> <br/>
			<c:out value='${pageContent.currentTip}' escapeXml='true' /> <br/>
			<b>Hello <c:out value="${user}" default="guest"/>.</b>

			<table>
			<c:forEach var="ListElement" items="${movieList}"  > 
			<c:forEach var="movie" items="${ListElement}" varStatus="count">
					<tr>
						<td>${movie} </td>
						<td>${count.count} </td>
					</tr>
				</c:forEach>
			</c:forEach>
		</table>
	</body>
</html>
