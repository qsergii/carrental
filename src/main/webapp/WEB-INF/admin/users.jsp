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
<%--    <%@ include file="header.jspf" %>--%>
    <h2>Users</h2>
    <a class="btn btn-primary" href="?id=0">Add</a>
    <div class="table-responsive">
        <table class="table">
            <thead>
            <tr>
                <th>Login</th>
                <th>Role</th>
                <th>First name</th>
                <th>Last name</th>
                <th>Blocked</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.users}" var="invoice">
                <tr class='clickable-row' data-href='users?id=${invoice.getId()}'>
                    <td>${invoice.login}</td>
                    <td>${invoice.role}</td>
                    <td>${invoice.firstName}</td>
                    <td>${invoice.lastName}</td>
                    <td><input type="checkbox" ${invoice.blocked ? 'checked' : ''} disabled/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
