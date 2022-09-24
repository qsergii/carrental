<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute
        name="user"
        type="com.epam.carrental.dao.entity.User"
        required="true" %>

<c:choose>
    <c:when test="${requestScope.user == null}">
        <a class="btn btn-primary" role="button" href="${path}/login"><fmt:message key="user.login"/></a>
    </c:when>
    <c:otherwise>
        <div class="d-md-flex d-xl-flex align-items-md-center align-items-xl-center" id="client">
            <div>
                <a id="client_link" class="nav-link"
                   href="${path}/user">${requestScope.user.firstName} ${requestScope.user.lastName}</a>
                <c:if test="${user.role != 'CLIENT'}">
                   <fmt:message key="role.${user.role}"/>
                </c:if>

            </div>
            <a class="btn btn-primary" role="button" href="${path}/logout"><fmt:message key="user.logout"/></a>
        </div>
    </c:otherwise>
</c:choose>