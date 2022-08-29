<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
  <head>
    <title>Car rental</title>
  </head>
  <body>
  <label for="language">Language</label>

  <select name="language" id="language">
    <option value="EN">EN</option>
    <option value="UA">UA</option>
  </select>

  <h1>Rent car in one minute</h1>
  <a href="auth">${auth}</a>

<%--  <my:user id="123" />--%>

<%--  <jsp:forward page="admin/cars.jsp" />--%>

<%--  <c:if test="${not empty requestScope.mes}">--%>
<%--    <hr>--%>
<%--  </c:if>--%>

<%--  <c:if test="${not empty mes}">--%>
<%--    <hr>--%>
<%--    ${mes}--%>
<%--  </c:if>--%>

<%--  <%@ taglib prefix="c" uri="/" %>--%>
<%--  <c:if test="${sessionScope.userRole == 'admin'}">--%>
<%--    <a href="...">settings</a>--%>
<%--  </c:if>--%>

  </body>
</html>
