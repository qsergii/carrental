<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">


        <h1><fmt:message key="orders.Orders"/></h1>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="Order"/></th>
                    <th><fmt:message key="Car"/></th>
                    <th><fmt:message key="user.User"/></th>
                    <th><fmt:message key="orders.with_driver"/></th>
                    <th><fmt:message key="orders.days_rent"/></th>
                    <th><fmt:message key="Price"/></th>
                    <th><fmt:message key="orders.rejected"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.orders}" var="car">
                    <tr class='clickable-row' data-href='orders?id=${car.getId()}'>
                        <td class="text-sm-end">${car.id}</td>
                        <td>${car.getCar().getBrand().getName()} ${car.getCar().getName()}</td>
                        <td>${car.getUser().getLogin()}</td>
                        <td>${car.isWithDriver()}</td>
                        <td class="text-sm-end">${car.getLeaseTerm()}</td>
                        <td class="text-sm-end">${car.getPrice()}</td>
                        <td><input type="checkbox" ${car.rejected ? "checked" : ""} disabled/></td>
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
