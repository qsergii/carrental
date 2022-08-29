<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <%@ include file="../head.jspf" %>
    <title>Administration</title>
</head>
<body>
<h1>Administration</h1>
<%@ include file="admin-menu.jspf" %>
<h2>Cars</h2>

<form action="" method="get">
    <input type="hidden" name="page" value="cars">
    <input type="hidden" name="action" value="add">
    <button type="submit">Add</button>
</form>

<table>
    <tr>
        <th>Name</th>
        <th>Brand</th>
        <th>Description</th>
        <th>Blocked</th>
        <th>Price</th>
        <th>Quality</th>
        <th>edit</th>
    </tr>
    <c:forEach items="${cars}" var="car">
        <tr>
            <td>${car.name}</td>
            <td>${car.getBrand().getName()}</td>
            <td>${car.getDescription()}</td>
            <td>${car.isBlocked()}</td>
            <td>${car.price}</td>
            <td>${car.getQuality().getName()}</td>
            <td><a href="admin/car/${car.getId()}">edit</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
