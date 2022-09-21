<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
                <div class="card-body">
                    <h4 class="card-title">Orders</h4>

                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Order</th>
                                <th>Car</th>
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
                                    <td>${car.isWithDriver()}</td>
                                    <td>${car.getLeaseTerm()}</td>
                                    <td class="text-sm-end">
                                        <fmt:formatNumber value="${car.price}" type="number" minFractionDigits = "2"/> UAH</td>
                                    <td>${car.rejected ? "yes" : "no"}</td>
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
