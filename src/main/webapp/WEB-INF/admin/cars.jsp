<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

    <section>
        <div class="container">
            <div class="card">
<%--                <%@ include file="header.jspf" %>--%>

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
                                <th>Quality</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Blocked</th>
                                <th>Price</th>
                                <th>Quality</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${cars}" var="invoice">
                                <tr class='clickable-row' data-href='cars?id=${invoice.getId()}'>
                                    <td><customtag:carImage car="${invoice}"/></td>
                                    <td>${invoice.getBrand().getName()}</td>
                                    <td>${invoice.getQuality().getName()}</td>
                                    <td>${invoice.name}</td>
                                    <td>${invoice.getDescription()}</td>
                                    <td>${invoice.isBlocked()}</td>
                                    <td><input type="checkbox" ${invoice.blocked ? "checked" : ""} disabled></td>
                                    <td>${invoice.price}</td>
                                    <td>${invoice.getQuality().getName()}</td>
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
