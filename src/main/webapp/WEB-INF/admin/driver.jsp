<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Driver</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container-sm">
    <form method="post">
        <label>Current price:</label>
        <input class="form-control" value="${requestScope.driverPrice}" readonly/>
        <label for="price">New price: </label>
        <input id="price" class="form-control" name="price" type="number" value="${requestScope.driverPrice}"><br>
        <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
    </form>
</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
