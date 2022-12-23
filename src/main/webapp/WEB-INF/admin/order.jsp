<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">
        <h1><fmt:message key="orders.Order"/> #${requestScope.order.id}</h1>
        <div class="row row-cols-1">
            <div class="col">
                <form method="post">
                    <input type="hidden" name="id" value="${requestScope.order.id}">

                    <div id="name_group" class="input-group">
                        <span class="input-group-text"><fmt:message key="Car"/>:</span>
                        <input class="form-control clickable-row" type="text" name="name"
                               data-href="cars?id=${requestScope.order.car.id}"
                               value="${requestScope.order.car.brand.name} ${requestScope.order.car.name}"
                               readonly/>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="Client"/>:</span>
                        <input class="form-control clickable-row"
                               data-href="users?id=${requestScope.order.user.id}" type="text" name="name"
                               value="${requestScope.order.user.login}"
                               readonly/>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.passport_number"/>:</span>
                        <input class="form-control" type="text" name="name"
                               value="${requestScope.order.passportNumber}"/>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.passport_valid"/>:</span>
                        <input class="form-control" type="date" pattern="\d{4}-\d{2}-\d{2}"
                               name="name" value="${requestScope.order.passportValid}"/>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="Price"/>:</span>
                        <input class="form-control" name="price" type="number" min="0.01" step="0.01"
                               value="${requestScope.order.price}"/>
                    </div>

                    <div class=" input-group">
                        <span class="input-group-text"><fmt:message key="orders.with_driver"/>:</span>
                        <input type="checkbox" class="form-check-input"
                               name="blocked" ${requestScope.order.withDriver ? 'checked':''} />
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.rejected"/>:</span>
                        <input type="checkbox" class="form-check-input"
                               name="blocked" ${requestScope.order.rejected ? 'checked':''} />
                    </div>
                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.rejected_reason"/>:</span>
                        <input class="form-control" type="text" name="name"
                               value="${requestScope.order.rejectReason}"/>
                    </div>

                    <br>
                    <div class="input-group">
                        <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <div class="container content">
        <h2><fmt:message key="orders.rejected"/></h2>
        <form action="orders" method="post">
            <input type="hidden" name="id" value="${requestScope.order.id}"/>
            <input type="hidden" name="action" value="reject"/>
            <div class="input-group">
                <span class="input-group-text" for="reason"><fmt:message key="orders.rejected_reason"/>:</span>
                <input id="reason" class="form-control" type="text" name="reason"/>
            </div>
            <br/>
            <button class="btn btn-danger" type="submit"><fmt:message key="orders.rejected"/></button>
        </form>
    </div>

    <div class="container content">
        <h2><fmt:message key="return.Return"/></h2>
        <form action="orders" method="post">
            <input type="hidden" name="action" value="return">
            <input type="hidden" name="id" value="${requestScope.order.id}"/>
            <div class="input-group">
                <span class="input-group-text" for="amount"><fmt:message key="return.damage_description"/>:</span>
                <input class="form-control" type="text" name="damage"/><br/>
            </div>
            <div class="input-group">
                <span class="input-group-text" for="amount"><fmt:message key="Amount"/>:</span>
                <input id="amount" class="form-control" type="number" name="amount"/><br/>
            </div>
            <br/>
            <button type="submit" class="btn btn-info"><fmt:message key="return.Return"/></button>
        </form>
    </div>

</section>

<%--Invoices--%>
<section id="invoices">
    <div class="container content">
        <h2><fmt:message key="invoices.Invoices"/></h2>
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
                        <td class="text-sm-end">${car.getOrder().getId()}</td>
                        <td><fmt:message key="type.${car.type}"/></td>
                        <td class="text-sm-end">
                            <fmt:formatNumber value="${car.amount}" type="number" minFractionDigits="2"/>
                        </td>
                        <td class="text-center"><input type="checkbox" ${car.payed ? "checked" : ""} disabled/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
