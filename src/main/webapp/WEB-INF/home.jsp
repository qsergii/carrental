<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<c:set var="title" value="Car rental" scope="page"/>
<%@include file="/WEB-INF/jspf/head_tag.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/menu.jspx" %>

<section>
    <div class="container py-4 py-xl-5">
        <h1>Rent car in one minute</h1>
        <c:forEach items="${cars}" var="user">
            <div class="border-3 mx-auto card" style="background: #fff;max-width: 700px;">
                <div class="row" style="opacity: 1;">
                    <div class="col">
                        <picture><img src="assets/img/clipboard-image-1.png"></picture>
                    </div>
                    <div class="col">
                        <p class="card-header">${user.getBrand().getName()} - ${user.name}
                            - ${user.getQuality().getName()}</p>
                        <p>${user.getDescription()}</p>
                    </div>
                    <div class="col">
                        <p>$${user.price} / 24h</p>
                        <a class="btn btn-primary" href="car?id=${user.getId()}">Rent</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
