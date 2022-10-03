<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">
        <h2><fmt:message key="qualities.qualities"/></h2>
        <a class="btn btn-primary" href="?id=0"><fmt:message key="button.add"/></a>
        <div class="table-responsive">
            <table class="table">
                <tr>
                    <th><fmt:message key="qualities.quality"/></th>
                </tr>
                <c:forEach items="${requestScope.qualities}" var="brand">
                    <tr class='clickable-row' data-href='?id=${brand.id}'>
                        <td>${brand.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
