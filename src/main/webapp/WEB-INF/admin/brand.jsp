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
        <h2>Brands</h2>
        <filetags:message/>
        <div class="row row-cols-1">
            <custom:message/>
            <form action="brands" method="post">
                <input type="hidden" name="action" value="add">
                <input id="id" type="hidden" name="id" value="${requestScope.get("brand").getId()}">
                <div class="input-group mb-3">
                    <label class="input-group-text" for="name">Name:</label>
                    <input id="name" class="form-control" name="name"
                           value="${requestScope.get("brand").getName()}"
                           placeholder="Rolls Royce"/>
                </div>
                <button class="btn btn-primary" type="submit">Save</button>
                <button id="deleteButton" class="btn btn-danger" type="button">Delete</button>
            </form>
        </div>
    </div>
    <script src="${path}/assets/js/admin-brands.js"></script>
</section>

<c:if test="${requestScope.cars != null}">
    <section id="cars">
        <div class="container">
            <div class="container">
                <div class="card">
                    <div class="card-body">
                        <h4 class="card-title">Cars</h4>
                        <div class="table-responsive">
                            <table class="table">
                                <thead>
                                <tr>
                                    <th>Quality</th>
                                    <th>Name</th>
                                    <th>Description</th>
                                    <th>Blocked</th>
                                    <th>Price</th>
                                    <th>Quality</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${cars}" var="car">
                                    <tr class='clickable-row' data-href='cars?id=${car.getId()}'>
                                        <td>${car.getQuality().getName()}</td>
                                        <td>${car.name}</td>
                                        <td>${car.getDescription()}</td>
                                        <td>${car.isBlocked()}</td>
                                        <td><input type="checkbox" ${car.blocked ? "checked" : ""} disabled></td>
                                        <td>${car.price}</td>
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
