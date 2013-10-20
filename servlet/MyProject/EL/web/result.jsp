<html>
	<body>
		<%@ taglib prefix="mine" uri="DiceFunctions" %>
		${mine:roolIt()} <br>

		Dog's name is : ${person.dog.name} <br>
		Person's name is : ${person["name"]} <br>
		Music is : ${musicList} <br>
		First music is : ${musicList[0]} <br>
		Second music is : ${musicList["1"]} <br>
		Foods are : ${food} <br>
		First food is : ${food[0]} <br>
		Second food is : ${food[1]} <br>
		Ambient is : ${musicMap.Ambient}
		Ambient is : ${musicMap["Ambient"]}
		Music is ${musicMap[Genre]} <br>
		Music is ${musicMap[musicType[0]]}
	</body>
</html>
