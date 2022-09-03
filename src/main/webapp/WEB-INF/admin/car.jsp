<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="../jspf/head.jspf" %>
    <title>Administration</title>
</head>
<body>
<h1>Administration</h1>

<%@include file="menu.jspf"%>

<h2>Cars</h2>
<form method="post">
    <input type="hidden" name="id" value="${requestScope.car.id}">
    <label for="name">Name:</label>
    <input id="name" name="name" value="${requestScope.car.name}"/><br/>
    <label for="brand">Model:</label>
    <select id="brand" name="brand">
        <c:forEach items="${requestScope.brands}" var="brand">
            <option value="${brand.getId()}" selected="${requestScope.car.brand==brand}">
                    ${brand.getName()}
            </option>
        </c:forEach>
    </select><br/>
    <label for="price">Price:</label>
    <input id="price" name="price" type="number" min="0.01" step="0.01" value="${requestScope.car.price}"/><br/>
    <label for="description">Description</label>
    <textarea id="description" name="description" placeholder="Car suites for family trip and small bussiness">${requestScope.car.description}</textarea><br/>
    <label for="blocked">Blocked:</label>
    <input id="blocked" name="blocked" type="checkbox" value="${requestScope.car.blocked}"><br/>
    <button type="submit">Save</button>
</form>

</body>
</html>
