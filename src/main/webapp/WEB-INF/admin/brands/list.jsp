<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <jsp:include page="./../../head.jsp"/>
    <title>Administration</title>
</head>
<body>
<h1 class="h1">Administration</h1>
<jsp:include page="../admin-menu.jsp"/>
<h2>Cars brands</h2>
<a href="?action=add">Create new</a>

<table class="table">
    <tr>
        <th>Brand</th>
        <th>edit</th>
        <th>delete</th>
    </tr>
    <c:forEach items="${requestScope.get('brands')}" var="brand">
        <tr>
            <td>${brand.getName()}</td>
            <td><a href="?action=edit&id=${brand.getId()}">edit</a></td>
            <td><a href="?action=delete&id=${brand.getId()}">delete</a></td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
