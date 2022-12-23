<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/header.jspf" %>

<section>
    <div class="container">
        <h1><fmt:message key="invoices.Invoice"/> #${requestScope.invoice.id}</h1>
        <div class="row row-cols-1">

            <div class="col">
                <form method="post">
                    <input type="hidden" name="id" value="${requestScope.invoice.id}">

                    <customtag:carImage car="${requestScope.invoice.order.car}"/>

                    <div id="name_group" class="input-group">
                        <span class="input-group-text"><fmt:message key="orders.Order"/>:</span>
                        <input class="form-control" type="text" name="name" readonly
                               value="${requestScope.invoice.order.id}"/>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="Type"/>:</span>
                        <input class="form-control" type="text" name="name" readonly
                               value="<fmt:message key="type.${requestScope.invoice.type}"/>"/>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="Amount"/>:</span>
                        <input class="form-control" type="text" name="name" readonly
                               value="<fmt:formatNumber value="${requestScope.invoice.amount}" type="number" minFractionDigits="2"/>"/>
                    </div>

                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="Payed"/>:</span>
                        <input class="form-check" type="checkbox" name="payed" disabled
                        ${requestScope.invoice.payed ? 'checked' : ''}/>
                    </div>
                    <div class="input-group">
                        <span class="input-group-text"><fmt:message key="Payed"/>:</span>
                        <input class="form-control" type="text" name="name" readonly
                               value="<fmt:formatDate value="${requestScope.invoice.payedDate}"  pattern="dd.MM.yyyy"/>"/>
                    </div>
                </form>

                <c:choose>
                    <c:when test="${requestScope.invoice.payed}">
                    </c:when>
                    <c:otherwise>
                        <form action="invoices" method="post">
                            <input type="hidden" name="action" value="pay"/>
                            <input type="hidden" name="id" value="${requestScope.invoice.id}"/>
                            <button class="btn btn-info" type="submit"><fmt:message key="Pay"/></button>
                        </form>
                    </c:otherwise>
                </c:choose>

            </div>
        </div>
    </div>
</section>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
