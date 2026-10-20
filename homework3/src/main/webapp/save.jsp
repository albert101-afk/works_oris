<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Добавление пользователей</title>
</head>
<body>
<h2>Заполните данные для добавления пользователей</h2>

<form action="save" method="post">

    <%
        int count = (Integer) request.getAttribute("usersCount");
        for (int i = 1; i <= count; i++) {
    %>
    <div style="border: 1px solid #ccc; padding: 10px; margin-bottom: 15px;">
        <h3>Пользователь №<%= i %></h3>

        <label>ID пользователя (вручную):</label><br>
        <input type="number" name="user_id_<%= i %>" required><br>

        <label>Имя:</label><br>
        <input type="text" name="first_name_<%= i %>" required><br>

        <label>Фамилия:</label><br>
        <input type="text" name="last_name_<%= i %>" required><br>

        <label>Email:</label><br>
        <input type="email" name="email_<%= i %>" required><br>

        <label>Возраст:</label><br>
        <input type="number" name="age_<%= i %>" required><br>

        <label>Город:</label><br>
        <input type="text" name="city_<%= i %>" required><br>

        <label>Роль:</label><br>
        <input type="text" name="role_<%= i %>" required><br>
    </div>
    <%
        }
    %>

    <input type="hidden" name="total_count" value="<%= count %>">

    <button type="submit">Сохранить всех</button>
</form>
</body>
</html>
