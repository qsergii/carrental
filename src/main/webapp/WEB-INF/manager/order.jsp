<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <%@ include file="/WEB-INF/jspf/head_tag.jspf" %>
    <title>Administration</title>
</head>
<body>

<%@ include file="/WEB-INF/jspf/menu.jspf" %>

<div class="container">
    <%--    <%@ include file="header.jspf" %>--%>

    <section>
        <div class="container">
            <p>Order ${requestScope.order.id}</p>
            <div class="row row-cols-1">

                <div class="col">
                    <form method="post">
                        <input type="hidden" name="id" value="${requestScope.order.id}">

                        <div id="name_group" class="input-group">
                            <span class="input-group-text">Car:</span>
                            <input class="form-control" type="text" name="name"
                                   value="${requestScope.order.car.brand.name} ${requestScope.order.car.name}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">User:</span>
                            <input class="form-control" type="text" name="name"
                                   value="${requestScope.order.user.login}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Passport number:</span>
                            <input class="form-control" type="text" name="name"
                                   value="${requestScope.order.passportNumber}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Passport valid:</span>
                            <input class="form-control" type="text" name="name"
                                   value="${requestScope.order.passportValid}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Price:</span>
                            <input class="form-control" name="price" type="number" min="0.01" step="0.01"
                                   value="${requestScope.order.price}"/>
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">With driver:</span>
                            <input type="checkbox" class="form-check-input"
                                   name="blocked" ${requestScope.order.withDriver ? 'checked':''} />
                        </div>

                        <div class="input-group">
                            <span class="input-group-text">Rejected:</span>
                            <input type="checkbox" class="form-check-input"
                                   name="blocked" ${requestScope.order.rejected ? 'checked':''} />
                        </div>
                        <div class="input-group">
                            <span class="input-group-text">Rejected reason:</span>
                            <input class="form-control" type="text" name="name"
                                   value="${requestScope.order.rejectReason}"/>
                        </div>

                        <div class="input-group">
                            <button class="btn btn-primary" type="submit">Save</button>

                        </div>
                    </form>

<%--                    reject--%>
                    <form action="orders" method="post">
                        <input type="hidden" name="id" value="${requestScope.order.id}"/>
                        <input type="hidden" name="action" value="reject"/>
                        <label for="reason">Reason:</label>
                        <input id="reason" type="text" name="reason"/>
                        <button class="btn btn-danger" type="submit">Reject</button>
                    </form>

<%--                    return--%>
                    <form action="orders" method="post">
                        <h2>Return</h2>
                        <input type="hidden" name="action" value="return">
                        <input type="hidden" name="id" value="${requestScope.order.id}"/>
                        <input class="form-control" type="text" name="damage" placeholder="damage description" /><br/>
                        <label for="amount">Amount:</label>
                        <input id="amount" class="form-control" type="number" name="amount"/>
                        <button type="submit" class="btn btn-info">Repairs</button>
                    </form>

                </div>
            </div>
        </div>
    </section>

</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>
