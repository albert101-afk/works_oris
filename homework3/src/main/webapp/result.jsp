<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="entities.User" %>
<html>
<head>
    <title>Веб-консоль результатов</title>
</head>
<body style="background-color: #1e1e1e; color: #d4d4d4; padding: 20px;">

<h2 style="color: #4fc1ff; font-family: monospace;">[DATABASE CONSOLE] -> SELECT * FROM users WHERE ...</h2>
<hr style="border-color: #333;">

<%
    List<User> users = (List<User>) session.getAttribute("usersList");
    if (users == null) {
        out.println("<pre style='color: #f44336;'>[Error] Нет активного запроса на поиск.</pre>");
    } else if (users.isEmpty()) {
        out.println("<pre style='color: #ff9800;'>[Console] Записей по вашему запросу не найдено.</pre>");
    } else {
        out.println("<pre style='color: #4caf50;'>[Success] Найдено строк: " + users.size() + "</pre>");
        out.println("<br>");

        for (User u : users) {
%>
<pre style="font-family: 'Courier New', monospace; font-size: 14px; margin: 5px 0;">> ID: <%= u.getUser_id() %> | Name: <%= u.getFirst_name() %> <%= u.getLast_name() %> | Age: <%= u.getAge() %> | City: <%= u.getCity() %> | Role: <%= u.getRole() %></pre>
<%
        }
    }
%>

<br><br>
<hr style="border-color: #333;">
<a href="search" style="font-family: monospace; display: inline-block; padding: 8px 15px; background-color: #333; color: #fff; text-decoration: none; border-radius: 4px;">
    [ Сбросить и вернуться к поиску ]
</a>
</body>
</html>

