<html><body>
<jsp:useBean id="person" type="foo.Employee" class="foo.Employee">
<%-- % person.setName(request.getParameter("userName")); % --%>
<%-- jsp:setProperty name="person" property="name" value=' <%= request.getParameter("userName")%> ' / --%>
	<jsp:setProperty name="person" property="*" />
</jsp:useBean>
Person is : <jsp:getProperty name="person" property="name" /> <br>
ID is : <jsp:getProperty name="person" property="empID" /> <br><br>

Request param name is: ${param.name} <br>
Request param empID is: ${param.empID} <br>
Request param food is: ${param.food} <br>
First food request param: ${paramValues.food[0]} <br>
Second food request param: ${paramValues.food[1]} <br>

Request param name: ${paramValues.name[0]} <br>
Host is: ${header.host} and  ${header["host"]} <br>
Method is: ${pageContext.request.method}<br>
Cookie userName is : ${cookie.userName.value} <br>
email is: ${initParam.mainEmail}<br>
</body></html>
