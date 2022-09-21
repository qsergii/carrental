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
                <%--                <%@ include file="/WEB-INF/header.jspf" %>--%>

                <div class="card-body">
                    <h4 class="card-title">Cars</h4>
                    <p class="card-text">List of cars.<br>To add a car - press Add button.<br>To edit (or delete) car -
                        press on line of a car.</p>
                    <a class="btn btn-primary" href="cars?id=0">Add</a>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Order</th>
                                <th>Car</th>
                                <th>User</th>
                                <th>With driver</th>
                                <th>Term</th>
                                <th>Price</th>
                                <th>Rejected</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.orders}" var="car">
                                <tr class='clickable-row' data-href='orders?id=${car.getId()}'>
                                    <td>${car.id}</td>
                                    <td>${car.getCar().getBrand().getName()} ${car.getCar().getName()}</td>
                                    <td>${car.getUser().getLogin()}</td>
                                    <td>${car.isWithDriver()}</td>
                                    <td>${car.getLeaseTerm()}</td>
                                    <td>${car.getPrice()}</td>
                                    <td>${car.rejected}</td>
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
