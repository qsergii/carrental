<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Administration</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/header.jspf" %>

<div class="container">

    <section>
        <div class="container">
            <p>Invoice ${requestScope.invoice.id}</p>
            <div class="row row-cols-1">

                <div class="col">
                    <form method="post">
                        <input type="hidden" name="id" value="${requestScope.invoice.id}">

                        <customtag:carImage car="${requestScope.invoice.order.car}"/>

                        <div id="name_group" class="input-group">
                            <span class="input-group-text">Order:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.invoice.order.id}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Type:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.invoice.type}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Amount:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.invoice.amount}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Payed:</span>
                            <input class="form-check" type="checkbox" name="payed" disabled
                            ${requestScope.invoice.payed ? 'checked' : ''}/>
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">Payed:</span>
                            <input class="form-control" type="text" name="name" readonly
                                   value="${requestScope.invoice.payedDate}"/>
                        </div>

                    </form>


                    <c:choose>
                        <c:when test="${requestScope.invoice.payed}">

                        </c:when>
                        <c:otherwise>
                            <form action="invoices" method="post">
                                <input type="hidden" name="action" value="pay"/>
                                <input type="hidden" name="id" value="${requestScope.invoice.id}"/>
                                <button class="btn btn-info" type="submit">Pay</button>
                            </form>
                        </c:otherwise>
                    </c:choose>

                </div>
            </div>
        </div>
    </section>

</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
