<html>
	<body>
		<jsp:useBean id="person" type="foo.Person" class="foo.Employee" scope="request">
			<jsp:setProperty name="person" property="name" value="Fred" />
		</jsp:useBean>
		Name is : 
		<jsp:getProperty name="person" property="name"/>
	</body>
</html>
