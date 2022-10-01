<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Administration</title>
</head>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section id="cars">
    <div class="container">
        <div class="container">
            <div class="card">
                <div class="card-body">
                    <h4 class="card-title">Cars</h4>
                    <p class="card-text">List of cars.<br>To add a car - press Add button.<br>To edit (or delete) car -
                        press on line of a car.</p>
                    <a class="btn btn-primary" href="cars?id=0">Add</a>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Picture</th>
                                <th>Brand</th>
                                <th>Quality</th>
                                <th>Name</th>
                                <th>Description</th>
                                <th>Blocked</th>
                                <th>Price</th>
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
                                    <td><input type="checkbox" ${car.blocked ? "checked" : ""} disabled></td>
                                    <td>${car.price}</td>
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

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
