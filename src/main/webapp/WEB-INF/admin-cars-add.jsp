<%@ page contentType="text/html;charset=UTF-8" %>
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
<form action="admin?page=cars&action=add" method="post">
    <label for="name">Name:</label>
    <input id="name" name="name"/>
    <label for="price">Price:</label>
    <input id="price" name="price" type="number" min="0.01" step="0.01"/><br>
    <label for="information">Information</label>
    <textarea id="information" name="description"></textarea>
    <button type="submit">Add</button>
</form>
</body>
</html>
