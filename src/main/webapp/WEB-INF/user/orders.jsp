<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container">
        <div class="card">
            <div class="card-body">
                <h4 class="card-title"><fmt:message key="orders.Orders"/></h4>

                <div class="table-responsive">
                    <table class="table table-bordered">
                        <thead>
                        <tr class="text-center">
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
                                <td class="text-sm-end">${order.id}</td>
                                <td>${order.getCar().getBrand().getName()} ${order.getCar().getName()}</td>
                                <td class="text-center">
                                    <input type="checkbox" ${order.isWithDriver() ? 'checked' : ''}
                                           disabled/>
                                </td>
                                <td class="text-sm-end">${order.getLeaseTerm()}</td>
                                <td class="text-sm-end">
                                    <fmt:formatNumber value="${order.price}" type="number" minFractionDigits="2"/></td>
                                <td class="text-center"><input type="checkbox" ${order.rejected ? "checked" : ""}
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

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
