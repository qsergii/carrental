<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute
        name="user"
        type="com.epam.carrental.entity.User"
        required="true" %>

<c:choose>
    <c:when test="${requestScope.user == null}">
        <a class="btn btn-primary" role="button" href="${pageContext.request.contextPath}/login">Sign In</a>
    </c:when>
    <c:otherwise>
        User: ${requestScope.user.login}
        <label class="text-warning">${requestScope.get("user") != null ? requestScope.user.login : 'No auth'}</label>
        <a class="btn btn-primary" role="button" href="${pageContext.request.contextPath}/logout">Logout</a>
    </c:otherwise>
</c:choose>