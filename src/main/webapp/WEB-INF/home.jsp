<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
  <head>
    <%@include file="jspf/head.jspf"%>
    <title>Car rental</title>
  </head>
  <body>
  <%@include file="jspf/main-menu.jspx"%>

  <h1>Rent car in one minute</h1>

  <table>
    <tr>
      <th>Name</th>
      <th>Brand</th>
      <th>Description</th>
      <th>Blocked</th>
      <th>Price</th>
      <th>Quality</th>
      <th>edit</th>
    </tr>
    <c:forEach items="${cars}" var="user">
      <tr>
        <td>${user.name}</td>
        <td>${user.getBrand().getName()}</td>
        <td>${user.getDescription()}</td>
        <td>${user.isBlocked()}</td>
        <td>${user.price}</td>
        <td>${user.getQuality().getName()}</td>
        <td><a href="car?id=${user.getId()}">Select</a></td>
      </tr>
    </c:forEach>
  </table>

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
  <%@include file="jspf/bottom.jspf"%>
</html>
