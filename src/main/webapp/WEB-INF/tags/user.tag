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
        <div class="d-md-flex d-xl-flex align-items-md-center align-items-xl-center" id="client">
            <div>
                <a id="client_link" class="nav-link" href="${path}/user">${requestScope.user.login}</a>
            </div>
            <a class="btn btn-primary" role="button" href="${path}/logout">Logout</a>
        </div>
    </c:otherwise>
</c:choose>