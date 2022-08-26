<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration</title>
</head>
<body>
<h1>Administration</h1>
<jsp:include page="admin-menu.jsp"/>
<h2>Cars brands</h2>
<form action="admin" method="post">
    <input type="hidden" name="page" value="cars">
    <input type="hidden" name="action" value="carsBrands-add">
    <label for="name">Name:</label>
    <input id="name" name="name" placeholder="Rols roys"/>
    <button type="submit">Add</button>
</form>
</body>
</html>
