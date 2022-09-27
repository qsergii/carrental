<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Error</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="h-100 w-100 alert alert-warning fill-height">
    <div class="align-middle">
        <p class="text-center d-xl-flex justify-content-xl-center align-items-xl-center error-code">
            ${pageContext.errorData.statusCode}<br></p>
        <p class="text-center d-xl-flex justify-content-xl-center align-items-xl-center error-text" role="alert">
            <c:choose>
                <c:when test="${pageContext.errorData.statusCode == 400}">
                    <fmt:message key="error.400"/>
                </c:when>
                <c:when test="${pageContext.errorData.statusCode == 403}">
                    <fmt:message key="error.403"/>
                </c:when>
                <c:when test="${pageContext.errorData.statusCode == 404}">
                    <fmt:message key="error.404"/>
                </c:when>
                <c:when test="${pageContext.errorData.statusCode == 500}">
                    <fmt:message key="error.500"/>
                </c:when>
            </c:choose>
        </p>
    </div>
</div>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
