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
                    <h4 class="card-title">Invoices</h4>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Invoice</th>
                                <th>Date</th>
                                <th>Order</th>
                                <th>Type</th>
                                <th>Amount</th>
                                <th>Payed</th>
                                <th>User</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.invoices}" var="car">
                                <tr class='clickable-row' data-href='invoices?id=${car.getId()}'>
                                    <td>${car.id}</td>
                                    <td>${car.getDate()}</td>
                                    <td>${car.getOrder().getId()}</td>
                                    <td>${car.type}</td>
                                    <td>${car.amount}</td>
                                    <td>${car.payed}</td>
                                    <td>${car.user.login}</td>
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
