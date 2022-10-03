<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@include file="/WEB-INF/jspf/header.jspf" %>

<section id="order_new">
    <div class="container content order">
        <div class="row">
            <div class="col">
                <customtag:carImage car="${requestScope.car}" classElement="car-big"/>
            </div>
            <div class="col">
                <p class="card-header">${requestScope.car.brand.name} ${requestScope.car.name}</p>
                <p class="card-text">${requestScope.car.description}</p>
                <p class="price">${requestScope.car.price} UAH / day</p>

                <form action="orders" method="post">
                    <input type="hidden" name="action" value="create"/>
                    <input type="hidden" name="car-id" value="${requestScope.car.id}">
                    <input type="hidden" id="driverPrice" name="driverPrice" value="${requestScope.driverPrice}">
                    <input id="price" type="hidden"
                           name="price" value="${requestScope.car.price}">

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.passport_number"/>:</span>
                        <input class="form-control" type="text" name="passport-number" required
                               value="${requestScope.user.passportNumber}">
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.passport_valid"/>:</span>
                        <input id="passportValid" class="form-control" type="date"
                               name="passport-valid" required
                               value="${requestScope.user.passportValid}"
                               min="<%= (new SimpleDateFormat("yyyy:MM:dd HH:mm:ss")).format(new Date()) %>">
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.with_driver"/>:</span>
                        <select id="withDriver" class="form-select" name="with-driver" required>
                            <option value="with-driver"><fmt:message key="orders.with_driver"/></option>
                            <option value="without-driver"><fmt:message key="orders.without_driver"/></option>
                        </select>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.begin_rent"/>:</span>
                        <input id="lease_begin" class="form-control" type="date"
                               name="lease_begin" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.finish_rent"/>:</span>
                        <input id="lease_finish" class="form-control" type="date"
                               name="lease_finish" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.days_rent"/>:</span>
                        <input id="lease_term" class="form-control" type="number" name="lease-term" min="1" max="30"
                               required/>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="Amount"/>:</span>
                        <input id="amount" class="form-control" type="number" readonly
                               name="amount" value="${requestScope.car.price}"/>
                    </div>
                    <p class="text-secondary"><fmt:message key="orders.info"/></p>
                    <button class="btn btn-primary border rounded d-md-flex" type="submit"><fmt:message
                            key="orders.send"/></button>
                </form>
                <p><fmt:message key="orders.driver_cost"/> ${requestScope.driverPrice} <fmt:message
                        key="UAH_per_day"/></p>
            </div>
        </div>
    </div>
    <script src="${path}/assets/js/order-confirm.js"></script>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
