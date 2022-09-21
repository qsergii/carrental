<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ attribute
        name="car"
        type="com.epam.carrental.entity.Car"
        required="true" %>

<div class="col-sm-4">
    <div class="card">
        <div class="card-body">
            <h4 class="card-title">
                <span>${car.brand.name}</span>
            </h4>
            <h6 class="text-muted card-subtitle mb-2">${car.name},
                <span>${car.getQuality().getName()}</span></h6>
            <p class="card-text">${car.getDescription()}</p>
            <picture><img src="assets/img/clipboard-image-1.png"></picture>
            <p><fmt:formatNumber value="${car.price}" type="number" minFractionDigits = "2"/> UAH/24h</p><a class="btn btn-primary" href="car?id=${car.getId()}">Rent</a>
        </div>
    </div>
</div>