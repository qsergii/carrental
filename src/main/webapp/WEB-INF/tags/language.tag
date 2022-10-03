<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:choose>
    <c:when test="${sessionScope.language == 'en'}">
        <c:set var="lang" value="ua" scope="page"/>
    </c:when>
    <c:otherwise>
        <c:set var="lang" value="en" scope="page"/>
    </c:otherwise>
</c:choose>
<a class="" role="button" href="?lang=${pageScope.lang}">
    <img id="language" class="language" src="${path}/assets/img/flag_${pageScope.lang}.png" alt="${pageScope.lang}"/>
</a>
