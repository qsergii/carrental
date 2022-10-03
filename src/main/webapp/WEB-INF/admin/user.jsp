<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>


<section>
    <div class="container content">
        <c:set var="order" value="${requestScope.user}" scope="page"/>

        <h2><fmt:message key="user.User"/></h2>

        <form method="post">
            <input type="hidden" name="id" value="${requestScope.user.id}"/>

            <div class="mb-3 row"><label class="form-label col-sm-2 col-form-label"><fmt:message
                    key="user.login"/>:</label>
                <div class="col-sm-10">
                    <input class="form-control form-control" type="text" name="login" value="${user.login}"/>
                </div>
            </div>

            <div class="mb-3 row">
                <label class="form-label col-sm-2 col-form-label"><fmt:message key="user.role"/>:</label>
                <div class="col-sm-10">
                    <select id="quality" class="form-select" name="role" required>
                        <c:forEach items="${requestScope.roles}" var="role">
                            <option value="${role.id}" ${user.role.id == role.id ? 'selected' : ''}>
                                <fmt:message key="role.${role}"/>
                            </option>
                        </c:forEach>
                    </select>
                </div>
            </div>

            <div class="mb-3 row"><label class="form-label col-sm-2 col-form-label"><fmt:message key="user.first_name"/>:</label>
                <div class="col-sm-10">
                    <input class="form-control form-control" type="text" name="firstName"
                           value="${user.firstName}"/>
                </div>
            </div>

            <div class="mb-3 row"><label class="form-label col-sm-2 col-form-label"><fmt:message
                    key="user.last_name"/>:</label>
                <div class="col-sm-10">
                    <input class="form-control form-control" type="text" name="lastName"
                           value="${user.lastName}"/>
                </div>
            </div>

            <div class="mb-3 row">
                <label class="form-label col-sm-2 col-form-label"><fmt:message key="user.password"/>:</label>
                <div class="col-sm-10">
                    <input class="form-control form-control" type="text" name="password"/>
                </div>
            </div>

            <div class="input-group mb-3">
                <label class="form-label col-sm-2 col-form-label"><fmt:message key="user.blocked"/>:</label>
                <div class="input-group-text">
                    <input class="form-check-input mt-0" type="checkbox" name="blocked"
                    ${user.blocked ? 'checked' : ''}
                           aria-label="Checkbox for following text input">
                </div>

            </div>

            <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
        </form>

        <hr/>
        <div>
            <div class="mb-3 row"><label class="form-label col-sm-2 col-form-label"><fmt:message
                    key="orders.amount_summary"/>:</label>
                <div class="col-sm-10">
                    <input class="form-control form-control" type="text" name="login"
                           value="${requestScope.amountSummary}" readonly/>
                </div>
            </div>
        </div>
    </div>

    <div class="container content">
        <h2><fmt:message key="orders.Orders"/></h2>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="orders.Order"/></th>
                    <th><fmt:message key="Car"/></th>
                    <th><fmt:message key="orders.with_driver"/></th>
                    <th><fmt:message key="orders.days_rent"/></th>
                    <th><fmt:message key="Price"/></th>
                    <th><fmt:message key="orders.rejected"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.orders}" var="order">
                    <tr class='clickable-row' data-href='orders?id=${order.getId()}'>
                        <td>${order.id}</td>
                        <td>${order.getCar().getBrand().getName()} ${order.getCar().getName()}</td>
                        <td><input type="checkbox" ${order.isWithDriver() ? "checked" : ""} disabled/></td>
                        <td>${order.getLeaseTerm()}</td>
                        <td class="text-sm-end">
                            <fmt:formatNumber value="${order.price}" type="number" minFractionDigits="2"/>
                        </td>
                        <td><input type="checkbox" ${order.rejected ? "checked" : ""} disabled/></td>
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
