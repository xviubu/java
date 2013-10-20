<html><body>
		<%-- %@ include file="Header.jsp" % --%>
		<jsp:include page="Header.jsp">
			<jsp:param name="subTitle" value="We take the sting out of SOAP." />
		</jsp:include>
		<br>
		<em>We can help.<em> <br><br>
		Contact us at:${initParam.mainEmail}
		<%@ include file="Footer.html" %>
</body></html>
