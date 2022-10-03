<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="custom" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="filetags" tagdir="/WEB-INF/tags" %>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">
        <div class="row row-cols-1">

            <div class="col">
                <customtag:carImage car="${requestScope.car}"/>
            </div>

            <div class="col">

                <filetags:message/>

                <form action="cars" method="post" enctype="multipart/form-data" accept-charset="UTF-8">
                    <input id="id" type="hidden" name="id" value="${requestScope.car.id}">

                    <%-- image --%>
                    <div class="input-group mb-3">
                        <input type="file" id="inputGroupFile02" class="form-control" name="file" size="50">
                        <label class="input-group-text" for="inputGroupFile02"><fmt:message key="Upload"/></label>
                    </div>

                    <div id="brand_group" class="input-group">
                        <span class="input-group-text"><fmt:message key="cars.brand"/>:</span>
                        <select id="brand" class="form-select" name="brand">
                            <c:forEach items="${requestScope.brands}" var="brand">
                                <option value="${brand.id}" ${requestScope.car.brand.equals(brand) ? 'selected' : ''}>${brand.name}</option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="name_group" class="input-group">
                        <span class="input-group-text"><fmt:message key="cars.name"/>:</span>
                        <input class="form-control" type="text" name="name" value="${requestScope.car.name}"/>
                    </div>

                    <div id="quality_group" class="input-group">
                        <span class="input-group-text"><fmt:message key="cars.quality"/>:</span>
                        <select id="quality" class="form-select" name="quality" required>
                            <c:forEach items="${requestScope.qualities}" var="brand">
                                <option value="${brand.id}" ${requestScope.car.quality.equals(brand) ? 'selected' : ''}>
                                        ${brand.getName()}
                                </option>
                            </c:forEach>
                        </select>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="Price"/>:</span>
                        <input class="form-control" name="price" type="number" min="0.01" step="0.01"
                               value="${requestScope.car.price}"/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="cars.description"/>:</span>
                        <textarea class="form-control" id="description"
                                  name="description">${requestScope.car.description}</textarea>
                    </div>
                    <div class="input-group"><span class="input-group-text"><fmt:message key="cars.blocked"/>:</span>
                        <input type="checkbox" class="form-check-input"
                               name="blocked" ${requestScope.car.blocked ? 'checked' : ''}/>
                    </div>
                    <br>
                    <div class="input-group">
                        <button class="btn btn-primary" type="submit"><fmt:message key="button.save"/></button>
                         
                        <button id="deleteCar" class="btn btn-danger" type="button"><fmt:message
                                key="button.delete"/></button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <script src="${path}/assets/js/admin-car.js"></script>
</section>

<section>
    <div class="container content">
        <h2><fmt:message key="orders.Orders"/></h2>
        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th class="text-sm-center"><fmt:message key="orders.Order"/></th>
                    <th class="text-sm-center"><fmt:message key="user.User"/></th>
                    <th class="text-sm-center"><fmt:message key="orders.with_driver"/></th>
                    <th class="text-sm-center"><fmt:message key="orders.days_rent"/></th>
                    <th class="text-sm-center"><fmt:message key="Price"/></th>
                    <th class="text-sm-center"><fmt:message key="orders.rejected"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.orders}" var="car">
                    <tr class='clickable-row' data-href='orders?id=${car.getId()}'>
                        <td class="text-sm-end">${car.id}</td>
                        <td>${car.getUser().getLogin()}</td>
                        <td><input type="checkbox" ${car.isWithDriver() ? "checked" : ""} disabled/></td>
                        <td class="text-sm-end">${car.getLeaseTerm()}</td>
                        <td class="text-sm-end"><fmt:formatNumber value="${car.getPrice()}" type="number"
                                                                  minFractionDigits="2"/></td>
                        <td><input type="checkbox" ${car.rejected ? "checked" : ""} disabled/></td>
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
