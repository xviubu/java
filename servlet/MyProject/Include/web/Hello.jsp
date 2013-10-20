<html><body>
		Welcome to our page!
		<% if(request.getParameter("userName") == null) { %>
			<jsp:forward page="HandleIt.jsp" />	
					
			<% } %>

		Hello ${param.userName}
</body></html>
