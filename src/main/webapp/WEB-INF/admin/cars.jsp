<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <%@ include file="../jspf/head.jspf" %>
    <title>Administration</title>
</head>
<body>
<%@ include file="../jspf/main-menu.jspx" %>
<h1>Administration</h1>
<%@ include file="menu.jspf" %>
<h2>Cars</h2>

<a href="cars?id=0">New</a>
<%--<form action="" method="get">--%>
<%--    <input type="hidden" name="page" value="cars">--%>
<%--    <input type="hidden" name="action" value="add">--%>
<%--    <button type="submit">Add</button>--%>
<%--</form>--%>

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
    <c:forEach items="${cars}" var="user">
        <tr>
            <td>${user.name}</td>
            <td>${user.getBrand().getName()}</td>
            <td>${user.getDescription()}</td>
            <td>${user.isBlocked()}</td>
            <td>${user.price}</td>
            <td>${user.getQuality().getName()}</td>
            <td><a href="admin/car/${user.getId()}">edit</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
