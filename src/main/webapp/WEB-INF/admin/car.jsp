<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Administration</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">
    <%@ include file="header.jspf" %>

    <section>
        <div class="container">
            <p>Car</p>
            <div class="row row-cols-1">

                <div class="col">
                    <customtag:carImage car="${requestScope.car}"/>
                </div>

                <div class="col">

                    <form action="cars" method="post" enctype="multipart/form-data">
                        <input type="hidden" name="id" value="${requestScope.car.id}">

                        <%--image--%>
                        <div class="input-group mb-3">
                            <input type="file" id="inputGroupFile02" class="form-control" name="file" size="50">
                            <label class="input-group-text" for="inputGroupFile02">Upload</label>
                        </div>

                        <div id="brand_group" class="input-group">
                            <span class="input-group-text">Brand:</span>
                            <select id="brand" class="form-select" name="brand">
                                <c:forEach items="${requestScope.brands}" var="brand">
                                    <option value="${brand.id}"
                                        ${requestScope.car.brand.equals(brand) ? 'selected' : ''}>
                                            ${brand.name}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div id="name_group" class="input-group">
                            <span class="input-group-text">Name:</span>
                            <input class="form-control" type="text" name="name" value="${requestScope.car.name}"/>
                        </div>

                        <div id="quality_group" class="input-group">
                            <span class="input-group-text">Quality:</span>
                            <select id="quality" class="form-select" name="quality" required>
                                <c:forEach items="${requestScope.qualities}" var="quality">
                                    <option value="${quality.id}" ${requestScope.car.quality.equals(quality) ? 'selected' : ''}>
                                            ${quality.getName()}
                                    </option>
                                </c:forEach>
                            </select>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Price:</span>
                            <input class="form-control" name="price" type="number" min="0.01" step="0.01"
                                   value="${requestScope.car.price}"/>
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">Description:</span>
                            <textarea class="form-control" id="description"
                                      name="description">${requestScope.car.description}</textarea>
                        </div>
                        <div class="input-group"><span class="input-group-text">Blocked:</span>
                            <input type="checkbox" class="form-check-input"
                                   name="blocked" ${requestScope.car.blocked ? 'checked' : ''}/>
                        </div>
                        <div class="input-group">
                            <button class="btn btn-primary" type="submit">Save</button>
                        </div>
                    </form>

                </div>
            </div>
        </div>
    </section>

</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
