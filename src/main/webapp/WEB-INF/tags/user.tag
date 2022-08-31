<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ attribute
        name="user"
        type="com.epam.carrental.entity.User"
        required="true" %>

<c:choose>
    <c:when test="${requestScope.user == null}">
        <a href="login">Login</a>
    </c:when>
    <c:otherwise>
        User: ${requestScope.user.login}
        <a href="logout">Logout</a>
    </c:otherwise>
</c:choose>