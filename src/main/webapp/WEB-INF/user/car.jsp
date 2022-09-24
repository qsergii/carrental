<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.epam.carrental.dao.entity.Car" %>
<c:set var="car" value="${requestScope.car}" scope="page" />
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>${car.name}</title>
</head>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container card" style="padding-top: 54px;">
        <div class="row">
            <div class="col">
                <customtag:carImage car="${car}"/>
            </div>
            <div class="col">
                <p class="card-header" style="font-weight: bold;color: var(--bs-indigo);font-size: 30px;">${car.brand.name} ${car.name} A8</p>
                <p class="card-text">${car.description}</p>
                <p class="price" style="color: var(--bs-red);font-weight: bold;">$${car.price} per 24h</p>

                <form method="get" action="create-order">
                    <input type="hidden" name="car-id" value="${car.id}">
                    <button class="btn btn-primary border rounded d-md-flex" type="submit">Rent</button>
                </form>

            </div>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>