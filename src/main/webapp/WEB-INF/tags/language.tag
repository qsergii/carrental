<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<%--<select id="language">--%>
<%--    <option value="en" ${sessionScope.language == "en" ? "selected" : ""}>EN</option>--%>
<%--    <option value="ua" ${sessionScope.language == "ua" ? "selected" : ""}>UA</option>--%>
<%--</select>--%>

<c:choose>
    <c:when test="${sessionScope.language == 'en'}">
        <a class="" role="button" href="?lang=ua">
            <img class="language" src="${path}/assets/img/flag_ua.png" alt="ua"/>
        </a>
    </c:when>
    <c:otherwise>
        <a class="" role="button" href="?lang=en">
            <img class="language" src="${path}/assets/img/flag_en.png" alt="en"/>
        </a>
    </c:otherwise>
</c:choose>