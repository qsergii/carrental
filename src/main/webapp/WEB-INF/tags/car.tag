<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="customtag" uri="/WEB-INF/custom_tags.tld" %>

<%@ attribute name="car" type="com.epam.carrental.dao.entity.Car" required="true" %>

<div class="col-sm-4">
    <div class="card">
        <div class="card-body d-flex flex-column justify-content-center">
            <span class="card-title">${car.brand.name}</span>
            <span class="text-muted card-subtitle mb-2">${car.name}, <span>${car.quality.name}</span></span>
            <span class="card-text">${car.description}<br/></span>
            <customtag:carImage car="${car}"/>
            <p class="text-center"><fmt:formatNumber value="${car.price}" type="number" minFractionDigits="2"/>
                <fmt:message key="UAH_per_day"/>
            </p>
            <a class="btn btn-primary" href="orders?car_id=${car.id}"><fmt:message key="Rent"/></a>
        </div>
    </div>
</div>
