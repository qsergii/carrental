<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="../jspf/head.jspf" %>
    <title>Administration</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/menu.jspx" %>

<div class="container">
    <h1>Administration</h1>

    <%@include file="header.jspf" %>

    <h2>Users</h2>
    <h3>${requestScope.user.login}</h3>
    <form method="post">
        <input type="hidden" name="id" value="${requestScope.user.id}">
        <label for="login">Name:</label>
        <input id="login" name="login" value="${requestScope.user.login}"/><br/>
        <label for="password">Password:</label>
        <input id="password" name="password" type="password"/><br/>
        <label for="role">Role:</label>
        <input id="role" name="role" value="${requestScope.user.role}"/><br/>
        <label for="blocked">Blocked:</label>
        <input id="blocked" name="blocked" type="checkbox" value="${requestScope.car.blocked}"><br/>
        <button type="submit">Save</button>
    </form>
</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
