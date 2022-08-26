<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Administration</title>
</head>
<body>
<h1>Administration</h1>
<jsp:include page="admin-menu.jsp"/>
<h2>Cars brands</h2>
<form action="admin" method="get">
    <input type="hidden" name="page" value="cars">
    <input type="hidden" name="action" value="carsBrands-add">
    <button type="submit">Add</button>
</form>

<table>
    <th>Brand</th>
    <c:forEach items="${carsBrands}" var="carsBrand">
        <tr>
            <td>${carsBrand.getName()}</td>
        </tr>
    </c:forEach>
</table>

</body>
</html>
