<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="my" tagdir="/WEB-INF/tags" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section class="container">

    <customtag:Quote/>

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
        <h1><fmt:message key="Rent_car_in_one_minute"/></h1>
        <div class="border-3 mx-auto row" id="cards">
            <c:forEach items="${cars}" var="car">
                <my:car car="${car}"/>
            </c:forEach>
        </div>
    </div>

    <div id="pagination" class="d-lg-flex justify-content-lg-center">
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
                <li class="page-item"><a class="page-link page_change" aria-label="Next"><span
                        aria-hidden="true">»</span></a>
                </li>
            </ul>
        </nav>
    </div>

    <script src="${path}/assets/js/home.js"/>
</section>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
