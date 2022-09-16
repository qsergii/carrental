<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<c:set var="title" value="Car rental" scope="page"/>
<%@ include file="/WEB-INF/jspf/head_tag.jspf" %>
<body>

<%@include file="/WEB-INF/jspf/menu.jspf" %>

<section>
    <div id="filters" class="container">
        <form>
            <div class="row">
                <div class="col">
                    <span>Brand:</span>
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
                    <span>Quality:</span>
                    <select id="filter_quality" class="form-select">
                            <option></option>
                            <c:forEach items="${requestScope.qualities}" var="quality">
                                <option
                                        value="${quality.id}"
                                    ${param.quality == quality.id ? 'selected=""' : ''}
                                >${quality.name}</option>
                            </c:forEach>
                    </select>
                </div>
                <div class="col">
                    <span>Sort by:</span>
                    <select id="sort_by" class="form-select">
                            <option></option>
                        <optgroup label="By price">
                            <option value="price" ${param.sort == "price" ? 'selected=""' : ''}>price
                            </option>
                            <option value="price-desc" ${param.sort == "price-desc" ? 'selected=""' : ''}>
                                price desc
                            </option>
                        </optgroup>
                        <optgroup label="By name">
                            <option value="name" ${param.sort == "name" ? 'selected=""' : ''}>name
                            </option>
                            <option value="name-desc" ${param.sort == "name-desc" ? 'selected=""' : ''}>
                                name desc
                            </option>
                        </optgroup>
                    </select>
                </div>
            </div>
        </form>
    </div>
    <div class="container py-4 py-xl-5">
        <h1>Rent car in one minute</h1>
        <c:forEach items="${cars}" var="invoice">
            <div class="border-3 mx-auto card" style="background: #fff;max-width: 700px;">
                <div class="row" style="opacity: 1;">
                    <div class="col">
                        <picture><img src="assets/img/clipboard-image-1.png"></picture>
                    </div>
                    <div class="col">
                        <p class="card-header">${invoice.getBrand().getName()} - ${invoice.name}
                            - ${invoice.getQuality().getName()}</p>
                        <p>${invoice.getDescription()}</p>
                    </div>
                    <div class="col">
                        <p>$${invoice.price} / 24h</p>
                        <a class="btn btn-primary" href="car?id=${invoice.getId()}">Rent</a>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div id="pagination" class="d-lg-flex justify-content-lg-center">
        <nav>
            <ul class="pagination">
                <li class="page-item"><a class="page-link page_change" aria-label="Previous"><span
                        aria-hidden="true">«</span></a></li>
                <c:forEach var="i" begin="1" end="${requestScope.pageCount}">
                    <li class="page-item"><a class="page_change page-link">${i}</a></li>
<%--                    href="?page=${i}"--%>
                </c:forEach>
                <li class="page-item"><a class="page-link page_change" aria-label="Next"><span aria-hidden="true">»</span></a>
                </li>
            </ul>
        </nav>
    </div>

    <script src="${pageContext.request.contextPath}/assets/js/home.js"/>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>


</body>
</html>
