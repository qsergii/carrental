<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">
        <form method="post">

            <div class="input-group mb-3">
                <span class="input-group-text" for="inBase"><fmt:message key="driver.current_price"/>:</span>
                <input id="inBase" class="form-control" value="${requestScope.driverPrice}" readonly/>
            </div>

            <div class="input-group mb-3">
                <span class="input-group-text" for="price"><fmt:message key="driver.new_price"/>:</span>
                <input id="price" class="form-control" name="price" type="number" required><br>
            </div>
            
            <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
        </form>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
