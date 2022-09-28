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
                <div class="card-body">
                    <h4 class="card-title">Orders</h4>

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
                            <c:forEach items="${requestScope.orders}" var="invoice">
                                <tr class='clickable-row' data-href='orders?id=${invoice.getId()}'>
                                    <td>${invoice.id}</td>
                                    <td>${invoice.getCar().getBrand().getName()} ${invoice.getCar().getName()}</td>
                                    <td>${invoice.getUser().getLogin()}</td>
                                    <td>${invoice.isWithDriver()}</td>
                                    <td>${invoice.getLeaseTerm()}</td>
                                    <td class="align-content-end">${invoice.getPrice()}</td>
                                    <td>${invoice.rejected ? "Yes" : ""}</td>
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
