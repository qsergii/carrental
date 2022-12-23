<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">
        <h2><fmt:message key="brands.brands"/></h2>
        <a class="btn btn-primary" href="?id=0"><fmt:message key="button.add"/></a>
        <div class="table-responsive">
            <table class="table table-bordered">
                <tr>
                    <th><fmt:message key="brands.brand"/></th>
                </tr>
                <c:forEach items="${requestScope.get('brands')}" var="brand">
                    <tr class='clickable-row' data-href='?action=edit&id=${brand.getId()}'>
                        <td>${brand.getName()}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
