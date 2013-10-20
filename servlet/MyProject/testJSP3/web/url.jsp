<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="last" value="Hidden Cursor"/>
<c:set var="first" value="Crouching Pixels" />

<c:url value="/inputComment.jsp" var="inputURL">
	<c:param name="firstName" value="${first}" />
	<c:param name="lastName" value="${last}" />
</c:url>

The URL using params is: ${inputURL} <br>

