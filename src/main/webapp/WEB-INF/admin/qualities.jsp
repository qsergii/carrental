<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Administration</title>
</head>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">
        <h2>Qualities</h2>
        <a class="btn btn-primary" href="?id=0">Create new</a>
        <div class="table-responsive">
            <table class="table">
                <tr>
                    <th>Brand</th>
                </tr>
                <c:forEach items="${requestScope.qualities}" var="brand">
                    <tr class='clickable-row' data-href='?id=${brand.id}'>
                        <td>${brand.name}</td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
