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

<div class="container">

<%--    <%@include file="/WEB-INF/admin/header.jspf" %>--%>

    <section>
        <h2>Qualities</h2>
        <a class="btn btn-primary" href="?action=add">Create new</a>

        <table class="table">
            <tr>
                <th>Brand</th>
                <th>edit</th>
                <th>delete</th>
            </tr>
            <c:forEach items="${requestScope.get('brands')}" var="brand">
                <tr>
                    <td>${brand.getName()}</td>
                    <td><a href="?action=edit&id=${brand.getId()}">edit</a></td>
                    <td>
                        <form action="?action=delete&id=${brand.getId()}" method="post">
                            <button>delete</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </section>

</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
