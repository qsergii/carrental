<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jspf/head_tag.jspf" %>
    <title>Administration</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/menu.jspx" %>

<div class="container">
    <%@include file="/WEB-INF/admin/header.jspf" %>
    <h2>Quality</h2>

    <form action="qualities" method="post">
        <input type="hidden" name="action" value="add">
        <input type="hidden" name="id" value="${requestScope.get("quality").getId()}">

        <label for="name">Name:</label>
        <input id="name" name="name" value="${requestScope.get("quality").getName()}" placeholder="VIP, First class"/><br/>

        <button type="submit">Save</button>
    </form>

</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
