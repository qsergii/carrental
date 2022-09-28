<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Administration</title>
</head>

<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <section>
        <div class="container">
            <div class="card">

                <div class="card-body">
                    <h4 class="card-title">Invoices</h4>
                    <a href="?export=pdf"><img src="${path}/assets/img/pdf.png" alt="pdf" width="64"/></a>
                    <a href="?export=xlsx"><img src="${path}/assets/img/xlsx.png" alt="xlsx" width="64"/></a>
                    <a href="?export=csv"><img src="${path}/assets/img/csv.webp" alt="csv" width="64"/></a>
                    <div class="table-responsive">
                        <table class="table">
                            <thead>
                            <tr>
                                <th>Invoice</th>
                                <th>Date</th>
                                <th>Order</th>
                                <th>Type</th>
                                <th>Amount</th>
                                <th>Payed</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach items="${requestScope.invoices}" var="invoice">
                                <tr class='clickable-row' data-href='invoices?id=${invoice.getId()}'>
                                    <td>${invoice.id}</td>
                                    <td>${invoice.getDate()}</td>
                                    <td>${invoice.getOrder().getId()}</td>
                                    <td>${invoice.type}</td>
                                    <td>${invoice.amount}</td>
                                    <td><input type="checkbox" ${invoice.payed ? "checked" : ""} disabled/></td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </section>

</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
