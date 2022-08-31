<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Login</title>
</head>
<body>
<a href="main">Main page</a>
<form action="login" method="post">
    <label for="login">User name</label>
    <input name="login" id="login"/><br/>
    <label for="password">Password</label>
    <input name="password" type="password" id="password"/><br/>
    <button>Send</button>
</form>
New here? <a href="registration">Registration</a>
<%@ include file="/WEB-INF/jspf/bottom.jspf" %>
</body>
</html>
