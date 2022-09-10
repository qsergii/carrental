<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
  <%@ include file="/WEB-INF/jspf/head_tag.jspf" %>
  <title>${requestScope.title}</title>
</head>
<body>
<jsp:include page="${requestScope.content}" />
</body>
</html>