<html>
	<body>
		<%! int doubleCount()
			{
				return count*2;
			}
		%>

		<%! int count = 1; %>
		The Page count is:
		<%= doubleCount() %>
	</body>
</html>
