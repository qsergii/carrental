<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag pageEncoding="UTF-8" description="user card in top right corner" %>

<%@ attribute
        name="user"
        type="com.epam.carrental.dao.entity.User"
        required="true" %>

<c:choose>
    <c:when test="${requestScope.authUser == null}">
        <a class="btn btn-outline-primary" role="button" href="${path}/login"><fmt:message key="user.login"/></a>
    </c:when>
    <c:otherwise>
        <div class="user_card top-container" onclick="window.location='${path}/user'">
            <img src="${path}/assets/img/user.png" class="img-fluid profile-image">
            <div class="ml-3">
                <h5 class="name">${requestScope.authUser.firstName}</h5>
                <p class="role"><fmt:message key="role.${authUser.role}"/></p>
            </div>
        </div>
        <div class="d-flex">
            <a class="btn btn-outline-success" role="button" href="${path}/logout"><fmt:message key="auth.logout"/></a>
        </div>
    </c:otherwise>
</c:choose>
