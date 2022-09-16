<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jspf/head_tag.jspf" %>
    <title>Administration</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/menu.jspf" %>

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
        <c:forEach items="${users}" var="invoice">
            <tr>
                <td>${invoice.login}</td>
                <td>${invoice.role}</td>
                <td>${invoice.blocked}</td>
                <td><a href="?id=${invoice.id}">edit</a></td>
            </tr>
        </c:forEach>
    </table>
</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
