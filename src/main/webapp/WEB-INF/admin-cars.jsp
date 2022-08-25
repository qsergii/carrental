<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration</title>
</head>
<body>
<h1>Administration</h1>
<a href="?page=cars">Cars</a>
<a href="?page=users">Users</a>
<a href="?page=managers">Managers</a>
<h2>Cars</h2>
<form action="" method="get">
    <input type="hidden" name="page" value="cars">
    <input type="hidden" name="action" value="add">
    <button type="submit">Add</button>
</form>
</body>
</html>
