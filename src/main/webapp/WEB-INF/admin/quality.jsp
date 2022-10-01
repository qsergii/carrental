<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="filetags" tagdir="/WEB-INF/tags" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body class="d-flex flex-column min-vh-100">

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content h-75">
        <h2>Quality</h2>
        <filetags:message/>
        <form method="post">
            <input type="hidden" name="action" value="add">
            <input id="id" type="hidden" name="id" value="${requestScope.quality.id}">

            <div class="input-group mb-3">
                <label class="input-group-text" for="name">Name</label>
                <input id="name" class="form-control" name="name" value="${requestScope.quality.name}"
                       placeholder="VIP, First class"/><br/>
            </div>
            <button class="btn btn-primary" type="submit">Save</button>
            <button id="deleteButton" class="btn btn-danger" type="button">Delete</button>
        </form>
    </div>
    <script src="${path}/assets/js/admin-qualities.js"></script>
</section>

<c:if test="${not empty requestScope.cars}">
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
                                    <th>Brand</th>
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
                                        <td>${car.getBrand().getName()}</td>
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
