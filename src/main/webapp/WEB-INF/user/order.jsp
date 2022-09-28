<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Administration</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <section>
        <div class="container">
            <p>Order ${requestScope.order.id}</p>
            <div class="row row-cols-1">

                <div class="col">
                    <form method="post">
                        <input id="id" type="hidden" name="id" value="${requestScope.order.id}">
                        <input id="carId" type="hidden" name="id" value="${requestScope.order.car.id}">

                        <customtag:carImage car="${requestScope.order.car}"/>

                        <div id="name_group" class="input-group">
                            <span class="input-group-text">Car:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.order.car.brand.name} ${requestScope.order.car.name}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">User:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.order.user.login}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Passport number:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.order.passportNumber}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Passport valid:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.order.passportValid}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Price:</span>
                            <input class="form-control" name="price" type="number" min="0.01" step="0.01" readonly
                                   value="${requestScope.order.price}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">With driver:</span>
                            <input type="checkbox" class="form-check-input" disabled
                                   name="blocked" ${requestScope.order.withDriver ? 'checked':''} />
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Rejected:</span>
                            <input type="checkbox" class="form-check-input" disabled
                                   name="rejected" ${requestScope.order.rejected ? 'checked':''} />
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">Rejected reason:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.order.rejectReason}"/>
                        </div>
                        <br>
                        <div class="input-group">
                            <button id="reorderCar" type="button" class="btn btn-primary">Reorder car</button>
                        </div>

                    </form>

                </div>
            </div>
        </div>
        <script src="${path}/assets/js/order.js"></script>
    </section>

    <section id="invoices">
        <div class="container">
            <div class="card">

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
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.invoices}" var="invoice">
                                <tr class='clickable-row' data-href='invoices?id=${invoice.getId()}'>
                                    <td>${invoice.id}</td>
                                    <td>${invoice.getDate()}</td>
                                    <td>${invoice.getOrder().getId()}</td>
                                    <td>${invoice.type}</td>
                                    <td>${invoice.amount}</td>
                                    <td><input type="checkbox" ${invoice.payed ? "checked" : ""} disabled/></td>
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
