<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${not empty param.message}">
    <div class="alert alert-warning" role="alert">${param.message}</div>
</c:if
