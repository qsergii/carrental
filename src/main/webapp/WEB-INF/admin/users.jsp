<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">
        <h2><fmt:message key="user.Users"/></h2>
        <a class="btn btn-primary" href="?id=0"><fmt:message key="button.add"/></a>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="user.login"/></th>
                    <th><fmt:message key="user.role"/></th>
                    <th><fmt:message key="user.first_name"/></th>
                    <th><fmt:message key="user.last_name"/></th>
                    <th><fmt:message key="user.blocked"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.users}" var="user">
                    <tr class='clickable-row' data-href='users?id=${user.getId()}'>
                        <td>${user.login}</td>
                        <td>${user.role}</td>
                        <td>${user.firstName}</td>
                        <td>${user.lastName}</td>
                        <td><input type="checkbox" ${user.blocked ? 'checked' : ''} disabled/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
