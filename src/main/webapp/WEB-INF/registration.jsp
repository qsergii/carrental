<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Registration</title>
</head>
<body>
<form action="registration" method="post">
    <label for="login">Login:</label>
    <input id="login" name="login"><br/>
    <label for="password">Password:</label>
    <input id="password" name="password" type="password"><br/>
    <label for="password2">Re-type password:</label>
    <input id="password2" name="password2" type="password"><br/>
    <button type="submit">Registration</button>
</form>
<%@ include file="/WEB-INF/jspf/bottom.jspf" %>
</body>
</html>
