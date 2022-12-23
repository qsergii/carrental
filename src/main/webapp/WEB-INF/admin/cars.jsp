<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section id="cars">
    <div class="container content">
        <h1><fmt:message key="cars.cars"/></h1>
        <a class="btn btn-primary" href="cars?id=0"><fmt:message key="Add"/></a>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr class="text-center">
                    <th><fmt:message key="cars.picture"/></th>
                    <th><fmt:message key="cars.brand"/></th>
                    <th><fmt:message key="cars.quality"/></th>
                    <th><fmt:message key="cars.name"/></th>
                    <th><fmt:message key="cars.description"/></th>
                    <th><fmt:message key="cars.blocked"/></th>
                    <th><fmt:message key="Price"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${cars}" var="car">
                    <tr class='clickable-row' data-href='cars?id=${car.getId()}'>
                        <td><customtag:carImage car="${car}"/></td>
                        <td>${car.getBrand().getName()}</td>
                        <td>${car.getQuality().getName()}</td>
                        <td>${car.name}</td>
                        <td>${car.getDescription()}</td>
                        <td class="text-center"><input type="checkbox" ${car.blocked ? "checked" : ""} disabled></td>
                        <td class="text-end"><fmt:formatNumber value="${car.price}" type="number"
                                                               minFractionDigits="2"/></td>
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
