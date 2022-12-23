<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <section>
        <div class="container">
            <h1><fmt:message key="Order"/> #${requestScope.order.id}</h1>
            <div class="row row-cols-1">

                <div class="col">
                    <form method="post">
                        <input id="id" type="hidden" name="id" value="${requestScope.order.id}">
                        <input id="carId" type="hidden" name="id" value="${requestScope.order.car.id}">

                        <customtag:carImage car="${requestScope.order.car}"/>

                        <div id="name_group" class="input-group">
                            <span class="input-group-text"><fmt:message key="Car"/>:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.order.car.brand.name} ${requestScope.order.car.name}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text"><fmt:message key="orders.passport_number"/>:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.order.passportNumber}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text"><fmt:message key="orders.passport_valid"/>:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="<fmt:formatDate value="${requestScope.order.passportValid}" pattern="dd.MM.yyyy"/>"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text"><fmt:message key="Price"/>:</span>
                            <input class="form-control" name="price" type="number" min="0.01" step="0.01" readonly
                                   value="<fmt:formatNumber value="${requestScope.order.price}" type="number" minFractionDigits="2"/>"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text"><fmt:message key="orders.with_driver"/>:</span>
                            <input type="checkbox" class="form-check-input" disabled
                                   name="blocked" ${requestScope.order.withDriver ? 'checked':''} />
                        </div>

                        <div class="input-group">
                            <span class="input-group-text"><fmt:message key="orders.rejected"/>:</span>
                            <input type="checkbox" class="form-check-input" disabled
                                   name="rejected" ${requestScope.order.rejected ? 'checked':''} />
                        </div>
                        <div class="input-group">
                            <span class="input-group-text"><fmt:message key="orders.rejected_reason"/>:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.order.rejectReason}"/>
                        </div>

                        <br>
                        <div class="input-group">
                            <button id="reorderCar" type="button" class="btn btn-primary"><fmt:message
                                    key="orders.reorder"/></button>
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
                    <h4 class="card-title"><fmt:message key="invoices.Invoices"/></h4>
                    <div class="table-responsive">
                        <table class="table table-bordered">
                            <thead>
                            <tr class="text-center">
                                <th><fmt:message key="invoices.Invoice"/></th>
                                <th><fmt:message key="Date"/></th>
                                <th><fmt:message key="Order"/></th>
                                <th><fmt:message key="Type"/></th>
                                <th><fmt:message key="Amount"/></th>
                                <th><fmt:message key="Payed"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.invoices}" var="car">
                                <tr class='clickable-row' data-href='invoices?id=${car.getId()}'>
                                    <td class="text-end">${car.id}</td>
                                    <td><fmt:formatDate value="${car.getDate()}" pattern="dd.MM.yyyy"/></td>
                                    <td class="text-end">${car.getOrder().getId()}</td>
                                    <td><fmt:message key="type.${car.type}"/></td>
                                    <td class="text-end"><fmt:formatNumber value="${car.amount}" type="number"
                                                                           minFractionDigits="2"/></td>
                                    <td class="text-center"><input type="checkbox" ${car.payed ? "checked" : ""}
                                                                   disabled/></td>
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
