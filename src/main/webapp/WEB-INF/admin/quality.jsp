<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="filetags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content h-75">
        <h2><fmt:message key="qualities.qualities"/></h2>
        <filetags:message/>
        <form method="post">
            <input type="hidden" name="action" value="add">
            <input id="id" type="hidden" name="id" value="${requestScope.quality.id}">

            <div class="input-group mb-3">
                <label class="input-group-text" for="name"><fmt:message key="qualities.quality"/></label>
                <input id="name" class="form-control" name="name" value="${requestScope.quality.name}"
                       placeholder="VIP, 1st class"/><br/>
            </div>
            <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
            <button id="deleteButton" class="btn btn-danger" type="button"><fmt:message key="button.delete"/></button>
        </form>
    </div>
    <script src="${path}/assets/js/admin-qualities.js"></script>
</section>

<c:if test="${not empty requestScope.cars}">
    <section id="cars">
        <div class="container content">
            <h2 class="card-title"><fmt:message key="cars.cars"/></h2>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr class="text-center">
                        <th><fmt:message key="brands.brand"/></th>
                        <th><fmt:message key="cars.name"/></th>
                        <th><fmt:message key="cars.description"/></th>
                        <th><fmt:message key="cars.blocked"/></th>
                        <th><fmt:message key="Price"/></th>
                        <th><fmt:message key="qualities.quality"/></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${cars}" var="car">
                        <tr class='clickable-row' data-href='cars?id=${car.getId()}'>
                            <td>${car.getBrand().getName()}</td>
                            <td>${car.name}</td>
                            <td>${car.getDescription()}</td>
                            <td class="text-center">
                                <input type="checkbox" ${car.isBlocked() ? "checked" : ""} disabled/></td>
                            <td class="text-sm-end"><fmt:formatNumber value="${car.price}" type="number"
                                                                      minFractionDigits="2"/></td>
                            <td>${car.getQuality().getName()}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        </div>
        </div>
        </div>
    </section>
</c:if>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
