<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>User</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section id="userSection">
    <div class="container">
        <h1>User</h1>
        <hr>
        <div>
            <label class="form-label">Login</label>
            <input class="form-control" type="text" value="${requestScope.user.login}" readonly>
        </div>
        <div>
            <label class="form-label">Phone</label>
            <input class="form-control" type="text" value="${requestScope.user.phone}" readonly>
        </div>
        <div>
            <label class="form-label">Email</label>
            <input class="form-control" type="text" value="${requestScope.user.email}" readonly>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
