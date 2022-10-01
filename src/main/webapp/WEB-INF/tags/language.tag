<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:choose>
    <c:when test="${sessionScope.language == 'en'}">
        <a class="" role="button" href="?lang=ua">
            <img id="language" class="language" src="${path}/assets/img/flag_ua.png" alt="ua"/>
        </a>
    </c:when>
    <c:otherwise>
        <a class="" role="button" href="?lang=en">
            <img id="language" class="language" src="${path}/assets/img/flag_en.png" alt="en"/>
        </a>
    </c:otherwise>
</c:choose>