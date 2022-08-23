<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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

  <%@ taglib prefix="c" uri="/" %>
  <c:if test="${sessionScope.userRole == 'admin'}">
    <a href="...">settings</a>
  </c:if>

  </body>
</html>
