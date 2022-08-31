<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <%@include file="../../jspf/head.jspf"%>
    <title>Administration</title>
</head>
<body>
<h1>Administration</h1>
<%@include file="../menu.jspf"%>
<h2>Cars brands</h2>

<form action="brands" method="post">
    <input type="hidden" name="action" value="add">
    <input type="hidden" name="id" value="${requestScope.get("brand").getId()}">
    <label for="name">Name:</label>
    <input id="name" name="name" value="${requestScope.get("brand").getName()}" placeholder="Rolls Royce"/><br/>
    <button type="submit">Save</button>
</form>

</body>
</html>
