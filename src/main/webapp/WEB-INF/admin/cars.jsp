<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Administration</title>
</head>

<body>

<%@ include file="/WEB-INF/jspf/menu.jspx" %>

<div class="container">

    <section>
        <div class="container">
            <div class="card">
                <%@ include file="header.jspf" %>

                <div class="card-body">
                    <h4 class="card-title">Cars</h4>
                    <p class="card-text">List of cars.<br>To add a car - press Add button.<br>To edit (or delete) car - press on line of a car.</p>
                    <a class="btn btn-primary" href="cars?id=0">Add</a>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Picture</th>
                                <th>Brand</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Blocked</th>
                                <th>Price</th>
                                <th>Quality</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cars}" var="user">
                                <tr class='clickable-row' data-href='cars?id=${user.getId()}'>
                                    <td>
                                        <picture><img src="../assets/img/clipboard-image-1.png"></picture>
                                    </td>
                                    <td>${user.getBrand().getName()}</td>
                                    <td>${user.name}</td>
                                    <td>${user.getDescription()}</td>
                                    <td>${user.isBlocked()}</td>
                                    <td><input type="checkbox"></td>
                                    <td>${user.price}</td>
                                    <td>${user.getQuality().getName()}</td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>

</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
