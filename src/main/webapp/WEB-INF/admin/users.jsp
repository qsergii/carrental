<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf"%>
    <title>Administration</title>
</head>
<body>
<h1>Administration</h1>
<%@ include file="menu.jspf" %>
<h2>Users</h2>
<a href="?id=0">Add</a>
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
    <c:forEach items="${users}" var="user">
        <tr>
            <td>${user.login}</td>
            <td>${user.role}</td>
            <td>${user.blocked}</td>
            <td><a href="?id=${user.id}">edit</a></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
