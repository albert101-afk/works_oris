<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
</head>
<body>
<form action="search" method="post">
    <label>Поиск по городу:</label>
    <input type="text" name="city">
    <button type="submit">Выполнить SELECT по городу</button>
</form>
<form action = "search" method="post">
    <label>Поиск по возрасту:</label>
    <input type="text" name="age">
    <button type="submit">Выполнить SELECT по возрасту</button>
</form>
<form action = "search" method="post">
    <label>Поиск по роли:</label>
    <input type = "text" name = "role">
    <button type = "submit">Выполнить SELECT по роли</button>
</form>
</body>
</html>
