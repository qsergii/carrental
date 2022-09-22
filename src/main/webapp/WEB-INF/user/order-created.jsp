<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<c:set var="title" value="Order" scope="page"/>
<%@include file="/WEB-INF/jspf/head.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container card order-new" style="padding-top: 54px;">
        <div class="row">
            <div class="col">
                <img class="card-img" src="assets/img/clipboard-image-1.png">
            </div>
            <div class="col">
                <p class="card-header header">Order #${requestScope.order.id}</p>
                <form>
                    <div class="input-group"><span class="input-group-text">Passport number:</span>
                        <span class="input-group-text">${requestScope.order.passportNumber}</span>
                    </div>
                    <div class="input-group"><span class="input-group-text">Passport valid:</span>
                        <span class="input-group-text">${requestScope.order.passportValid}</span>
                    </div>
                    <div class="input-group"><span class="input-group-text">With driver:</span>
                        <span class="input-group-text">${requestScope.order.withDriver}</span>
                    </div>
                    <div class="input-group"><span class="input-group-text">Price for 24h:</span>
                        <span class="input-group-text">$ ${requestScope.order.price}</span>
                    </div>
                    <div class="input-group"><span class="input-group-text">Begin:</span>
                        <span class="input-group-text">${requestScope.order.leaseBegin}</span>
                    </div>
                    <div class="input-group"><span class="input-group-text">Finish:</span>
                        <span class="input-group-text">${requestScope.order.leaseFinish}</span>
                    </div>
                    <div class="input-group"><span class="input-group-text">Days:</span>
                        <span class="input-group-text">${requestScope.order.leaseTerm}</span>
                    </div>
                </form>
                New <a href="invoices?id=${requestScope.invoice.id}">invoice</a> for rent, please pay ASAP!
                <p>Please, wait for your car</p>
            </div>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>