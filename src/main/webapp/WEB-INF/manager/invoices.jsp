<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container content">
        <h1><fmt:message key="invoices.Invoices"/></h1>
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
                <c:forEach items="${requestScope.invoices}" var="invoice">
                    <tr class='clickable-row' data-href='invoices?id=${invoice.getId()}'>
                        <td class="text-sm-end">${invoice.id}</td>
                        <td>${invoice.getDate()}</td>
                        <td class="text-sm-end">${invoice.getOrder().getId()}</td>
                        <td><fmt:message key="type.${invoice.type}"/></td>
                        <td class="text-sm-end">
                            <fmt:formatNumber value="${invoice.amount}" type="number" minFractionDigits="2"/></td>
                        <td><input type="checkbox" ${invoice.payed ? "checked" : ""} disabled/></td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
