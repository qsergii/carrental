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
        <div class="input-group mb-3">
            <span class="input-group-text" for="inBase">Current price:</span>
            <input id="inBase" class="form-control" value="${requestScope.driverPrice}" readonly/>
        </div>
        <div class="input-group mb-3">
            <span class="input-group-text" for="price">New price: </span>
            <input id="price" class="form-control" name="price" type="number" required><br>
        </div>
        <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
    </form>
</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
