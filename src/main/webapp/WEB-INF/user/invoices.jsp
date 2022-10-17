<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">

        <h1 class="card-title"><fmt:message key="invoices.Invoices"/></h1>
        <a id="a_print" href="?export=print"><img src="${path}/assets/img/printer.png" alt="print" width="64"/></a>
        <a href="?export=pdf"><img src="${path}/assets/img/pdf.png" alt="pdf" width="64"/></a>
        <a href="?export=xlsx"><img src="${path}/assets/img/xlsx.png" alt="xlsx" width="64"/></a>
        <a href="?export=csv"><img src="${path}/assets/img/csv.webp" alt="csv" width="64"/></a>

        <div class="table-responsive">
            <table class="table">
                <thead>
                <tr>
                    <th><fmt:message key="invoices.Invoice"/></th>
                    <th><fmt:message key="Date"/></th>
                    <th><fmt:message key="Order"/></th>
                    <th><fmt:message key="Type"/></th>
                    <th><fmt:message key="Amount"/></th>
                    <th><fmt:message key="Payed"/></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${requestScope.invoices}" var="car">
                    <tr class='clickable-row' data-href='invoices?id=${car.getId()}'>
                        <td class="text-sm-end">${car.id}</td>
                        <td>${car.getDate()}</td>
                        <td class="text-sm-end">${car.getOrder().getId()}</td>
                        <td><fmt:message key="type.${car.type}"/></td>
                        <td class="text-sm-end">
                            <fmt:formatNumber value="${car.amount}" type="number" minFractionDigits="2"/>
                        </td>
                        <td><input type="checkbox" ${car.payed ? "checked" : ""} disabled/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
    <script src="${path}/assets/js/invoices.js"></script>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
