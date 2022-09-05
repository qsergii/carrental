<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>${requestScope.car.name}</title>
</head>
<body>
<%--<%@include file="jspf/main-menu.jspf"%>--%>
<h1>${requestScope.car.name}</h1>
<form method="post">
    <input type="hidden" name="id" value="${requestScope.car.id}">
    <label for="name">Name:</label>
    <input id="name" name="name" value="${requestScope.car.name}"/><br/>
    <label for="brand">Model:</label>
    <input id="brand" name="brand" value="${requestScope.car.brand}"><br/>
    <label for="price">Price:</label>
    <input id="price" name="price" type="number" min="0.01" step="0.01" value="${requestScope.car.price}"/><br/>
    <label for="description">Description</label>
    <textarea id="description" name="description" placeholder="Car suites for family trip and small bussiness">${requestScope.car.description}</textarea><br/>
    <label for="blocked">Blocked:</label>
    <input id="blocked" name="blocked" type="checkbox" value="${requestScope.car.blocked}"><br/>
    <button type="submit">Select</button>
</form>
</body>
</html>