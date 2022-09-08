<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>${requestScope.car.name}</title>
</head>
<body>

<%@include file="jspf/menu.jspx" %>

<section>
    <div class="container card order" style="padding-top: 54px;">
        <div class="row">
            <div class="col"><img class="card-img" src="assets/img/clipboard-image-1.png"></div>
            <div class="col">
                <p class="card-header"
                   style="font-weight: bold;color: var(--bs-indigo);font-size: 30px;">${requestScope.car.brand.name} ${requestScope.car.name}</p>
                <p class="card-text">${requestScope.car.description}</p>
                <p class="price" style="color: var(--bs-red);font-weight: bold;">$${requestScope.car.price} per 24h</p>

                <form action="order" method="post">
                    <input type="hidden" name="car-id" value="${requestScope.car.id}">
                    <input type="hidden" name="price" value="${requestScope.car.price}">
                    <div class="input-group">
                        <span class="input-group-text">Passport number:</span>
                        <input class="form-control" type="text" name="passport-number" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">Passport valid:</span>
                        <input class="form-control" type="date" name="passport-valid" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">With driver:</span>
                        <select name="with-driver" class="form-select" required>
                            <optgroup label="Select driver options">
                                <option value="with-driver">With driver</option>
                                <option value="without-driver">Without driver</option>
                            </optgroup>
                        </select>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text">Number of days:</span>
                        <input class="form-control" type="number" name="lease-term" min="1" max="30" required/>
                    </div>
                    <input type="number" name="ammount" value="${requestScope.car.price}"/>
                    <p class="text-secondary">You passport data will be stored and use in next order</p>
                    <button class="btn btn-primary border rounded d-md-flex" type="submit">Place the order</button>
                </form>

            </div>
        </div>
    </div>
</section>

<%@include file="jspf/footer.jspf" %>

</body>
</html>