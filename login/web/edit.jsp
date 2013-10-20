<?xml version="1.0" encoding="UTF-8" ?>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<div>
<h2> Create a free Account</h2>
<sf:form method="POST" modelAttribute="user">
	<fieldset>
		<table>
			<tr>
				<th><label for="username">Username</label></th>
				<td><sf:input path="username" size="15" maxlength="15" id="username"/></td>
				<small id="username_msg">No places,Please</small>
			</tr>
			<tr>
				<th><label for="password">Password</label></th>
				<td><sf:password path="password" size="30" showpassword="true" id="password"/></td>
				<small id="password_msg"> 6 characters or more </small>
			</tr>
			<tr>
				<th><label for="email">Email</label></th>
				<td><sf:input path="email" size="30" id="email"/></td>
				<small>In case forget something!</small> 
			</tr>
			<tr>
				<td><sf:checkbox path="active" id ="active" /> 
					<label for="send_email">Send me email now !</label>
				</td>
		</table>
	</fieldset>
</sf:form>
</div>
