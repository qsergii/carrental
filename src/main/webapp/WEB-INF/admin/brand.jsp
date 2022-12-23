<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="filetags" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">
        <h2><fmt:message key="brands.brand"/></h2>
        <filetags:message/>
        <div class="row row-cols-1">
            <custom:message/>

            <form action="brands" method="post">
                <input type="hidden" name="action" value="add">
                <input id="id" type="hidden" name="id" value="${requestScope.get("brand").getId()}">

                <div class="input-group mb-3">
                    <label class="input-group-text" for="name"><fmt:message key="brands.name"/>:</label>
                    <input id="name" class="form-control" name="name"
                           value="${requestScope.get("brand").getName()}"
                           placeholder="Rolls Royce"/>
                </div>

                <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
                <button id="deleteButton" class="btn btn-danger" type="button"><fmt:message
                        key="button.delete"/></button>
            </form>

        </div>
    </div>
    <script src="${path}/assets/js/admin-brands.js"></script>
</section>

<c:if test="${requestScope.cars != null}">
    <section id="cars">
        <div class="container content">
            <h2><fmt:message key="cars.cars"/></h2>
            <div class="table-responsive">
                <table class="table table-bordered">
                    <thead>
                    <tr class="text-center">
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
                            <td>${car.getQuality().getName()}</td>
                            <td>${car.name}</td>
                            <td>${car.getDescription()}</td>
                            <td class="text-center"><input type="checkbox" ${car.blocked ? "checked" : ""} disabled/>
                            </td>
                            <td class="text-sm-end"><fmt:formatNumber value="${car.price}" type="number"
                                                                      minFractionDigits="2"/></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>
</c:if>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
