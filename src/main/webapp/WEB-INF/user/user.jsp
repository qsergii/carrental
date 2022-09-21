<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>User</title>
</head>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section id="userSection">
    <div class="container">
        <h1>User</h1>
        <hr>
        <div>
            <label class="form-label">Login</label>
            <input type="text" value="${requestScope.user.login}">
        </div>
    </div>
</section>

<section id="orderSection">
    <div class="container">
        <h1>Order 7</h1>
        <hr>
        <div><label class="form-label">Car</label><label class="form-label">Audi A8</label></div>
        <div><label class="form-label">Date</label><label class="form-label">08 sep 2022</label></div>
        <div><label class="form-label">Price</label><label class="form-label">9.99 UAH</label></div>
    </div>
</section>

<section id="ordersSection">
    <div class="container">
        <h1>Orders</h1>
        <hr>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th>Number</th>
                    <th>Date</th>
                    <th>Car</th>
                    <th>Price</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.orders}" var="car">
                    <tr class='clickable-row' data-href='cars?id=${car.getId()}'>
                        <td>${car.id}</td>
                        <td>${car.date}</td>
                        <td>${car.car.brand.name} ${car.car.name}</td>
                        <td class="text-sm-end"><fmt:formatNumber value="${car.price}" type="number" minFractionDigits = "2"/> UAH</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
