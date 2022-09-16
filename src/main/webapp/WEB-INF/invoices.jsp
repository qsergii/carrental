<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!doctype html>
<html lang="en">

<head>
    <%@ include file="/WEB-INF/jspf/head_tag.jspf" %>
    <title>Administration</title>
</head>

<body>

<%@ include file="/WEB-INF/jspf/menu.jspf" %>

<div class="container">

    <section>
        <div class="container">
            <div class="card">
                <%--                <%@ include file="/WEB-INF/header.jspf" %>--%>

                <div class="card-body">
                    <h4 class="card-title">Invoices</h4>
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
                                <th>User</th>
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
                                    <td>${invoice.payed}</td>
                                    <td>${invoice.user.login}</td>
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
