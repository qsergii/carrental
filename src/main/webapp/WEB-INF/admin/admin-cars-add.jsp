<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Administration</title>
</head>
<body>
<h1>Administration</h1>
<jsp:include page="menu.jspf"/>
<h2>Cars</h2>
<form action="admin?page=cars&action=add" method="post">
    <label for="name">Name:</label><input id="name" name="name"/><br/>
    <label for="carBrand">Model:</label>
    <select id="carBrand" name="carBrand">
        <c:forEach items="${carsBrands}" var="brand">
            <option value="${brand.getId()}">
                    ${brand.getName()}
            </option>
        </c:forEach>
    </select><br/>
    <label for="price">Price:</label><input id="price" name="price" type="number" min="0.01" step="0.01"/><br/>
    <label for="description">Description</label><textarea id="description" name="description"
                                                          placeholder="Car suites for family trip and small bussiness"></textarea><br/>
    <label for="blocked">Blocked:</label><input id="blocked" name="blocked" type="checkbox"><br/>
    <button type="submit">Add</button>
</form>
</body>
</html>
