<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>${requestScope.car.name}</title>
</head>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>
${requestScope.car}
<section>
    <div class="container card">
        <div class="row">
            <div class="col">

                <customtag:carImage car="${requestScope.car}"/>
            </div>
            <div class="col">
                <p class="card-header">${requestScope.car.brand.name} ${requestScope.car.name} A8</p>
                <p class="card-text">${requestScope.car.description}</p>
                <p class="price">${requestScope.car.price} UAH / 24h</p>

                <form method="get" action="create-order">
                    <input type="hidden" name="car-id" value="${requestScope.car.id}">
                    <button class="btn btn-primary border rounded d-md-flex" type="submit">Rent</button>
                </form>

            </div>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>