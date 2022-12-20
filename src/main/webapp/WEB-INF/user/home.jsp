<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<c:if test="${empty param.brand && empty param.quality && empty param.sort}">
    <customtag:Quote/>
    <section id="slider" class="container">
        <div id="carouselExampleCaptions" class="carousel slide" data-bs-ride="carousel">
            <div class="carousel-indicators">
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="0" class="active"
                        aria-current="true" aria-label="Slide 1"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="1"
                        aria-label="Slide 2"></button>
                <button type="button" data-bs-target="#carouselExampleCaptions" data-bs-slide-to="2"
                        aria-label="Slide 3"></button>
            </div>
            <div class="carousel-inner">
                <div class="carousel-item active">
                    <img src="${path}/assets/img/slider/1.png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>CAR SUITE YOU</h5>
                        <p></p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="${path}/assets/img/slider/2.png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>SAVE BIG ON LUXURY CARS</h5>
                        <p></p>
                    </div>
                </div>
                <div class="carousel-item">
                    <img src="${path}/assets/img/slider/3.png" class="d-block w-100" alt="...">
                    <div class="carousel-caption d-none d-md-block">
                        <h5>WANT SOME SPECIAL?</h5>
                        <p></p>
                    </div>
                </div>
            </div>
            <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleCaptions"
                    data-bs-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Previous</span>
            </button>
            <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleCaptions"
                    data-bs-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="visually-hidden">Next</span>
            </button>
        </div>
    </section>
</c:if>

<main>
    <div id="filters" class="container">
        <form>
            <div class="row">
                <div class="col">
                    <span><fmt:message key="Brand"/> :</span>
                    <select id="filter_brand" class="form-select">
                        <option></option>
                        <c:forEach items="${requestScope.brands}" var="brand">
                            <option
                                    value="${brand.id}"
                                ${param.brand == brand.id ? 'selected=""' : ''}
                            >${brand.name}</option>
                        </c:forEach>
                    </select></div>
                <div class="col">
                    <span><fmt:message key="Quality"/>:</span>
                    <select id="filter_quality" class="form-select">
                        <option></option>
                        <c:forEach items="${requestScope.qualities}" var="brand">
                            <option
                                    value="${brand.id}"
                                ${param.quality == brand.id ? 'selected=""' : ''}
                            >${brand.name}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <span><fmt:message key="sort.Sort_by"/>:</span>
                    <select id="sort_by" class="form-select">
                        <option></option>
                        <optgroup label="<fmt:message key="sort.By_price"/>">
                            <option value="price" ${param.sort == "price" ? 'selected=""' : ''}><fmt:message
                                    key="sort.by_price"/>
                            </option>
                            <option value="price-desc" ${param.sort == "price-desc" ? 'selected=""' : ''}>
                                <fmt:message key="sort.by_price_desc"/>
                            </option>
                        </optgroup>
                        <optgroup label="<fmt:message
                                    key="sort.By_name"/>">
                            <option value="name" ${param.sort == "name" ? 'selected=""' : ''}><fmt:message
                                    key="sort.by_name"/></option>
                            <option value="name-desc" ${param.sort == "name-desc" ? 'selected=""' : ''}>
                                <fmt:message key="sort.by_name_desc"/>
                            </option>
                        </optgroup>
                    </select>
                </div>
            </div>
        </form>
    </div>

    <div id="cars" class="container py-4 py-xl-5">
        <h1 class="text-center"><fmt:message key="Rent_car_in_one_minute"/></h1>
        <div class="border-3 mx-auto row" id="cards">
            <c:forEach items="${cars}" var="car">
                <my:car car="${car}"/>
            </c:forEach>
        </div>
    </div>

    <div id="pagination" class="d-flex justify-content-center">
        <c:set var="thisPage" value="${not empty param.page ? param.page :  1}" scope="page"/>
        <nav>
            <ul class="pagination">
                <li class="page-item"><a class="page-link page_change" aria-label="Previous"><span
                        aria-hidden="true">«</span></a></li>
                <c:forEach var="i" begin="1" end="${requestScope.pageCount}">
                    <li class="page-item ${thisPage == i ? 'active' : ''}">
                        <a class="page_change page-link">${i}</a>
                    </li>
                </c:forEach>
                <li class="page-item">
                    <a class="page-link page_change" aria-label="Next">
                        <span aria-hidden="true">»</span></a>
                </li>
            </ul>
        </nav>
    </div>

    <script src="${path}/assets/js/home.js"/>
</main>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
