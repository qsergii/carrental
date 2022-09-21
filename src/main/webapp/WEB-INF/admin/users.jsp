<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Administration</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">
    <%@ include file="header.jspf" %>
    <h2>Users</h2>
    <a href="?id=0">Add</a>
    <table>
        <tr>
            <th>Login</th>
            <th>Role</th>
            <th>Blocked</th>
            <th>edit</th>
        </tr>
        <c:forEach items="${users}" var="car">
            <tr>
                <td>${car.login}</td>
                <td>${car.role}</td>
                <td>${car.blocked}</td>
                <td><a href="?id=${car.id}">edit</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
