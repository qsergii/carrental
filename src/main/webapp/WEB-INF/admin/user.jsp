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

    <c:set var="order" value="${requestScope.user}" scope="page" />

    <h2>User ${user.login}</h2>

    <section>
        <div class="container">
            <form method="post">
                <input type="hidden" name="id" value="${requestScope.user.id}" />

                <div class="mb-3 row"><label class="form-label col-sm-2 col-form-label">Login:</label>
                    <div class="col-sm-10">
                        <input class="form-control form-control" type="text" name="login" value="${user.login}" />
                    </div>
                </div>

                <div class="mb-3 row">
                    <label class="form-label col-sm-2 col-form-label">Role:</label>
                    <div class="col-sm-10">
                        <select id="quality" class="form-select" name="role" required>
                            <c:forEach items="${requestScope.roles}" var="role">
                                <option value="${role.id}" ${user.role.id == role.id ? 'selected' : ''}>
                                        ${role}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="mb-3 row"><label class="form-label col-sm-2 col-form-label">First name:</label>
                    <div class="col-sm-10">
                        <input class="form-control form-control" type="text" name="firstName" value="${user.firstName}" />
                    </div>
                </div>

                <div class="mb-3 row"><label class="form-label col-sm-2 col-form-label">Last name:</label>
                    <div class="col-sm-10">
                        <input class="form-control form-control" type="text" name="lastName" value="${user.lastName}" />
                    </div>
                </div>

                <div class="mb-3 row">
                    <label class="form-label col-sm-2 col-form-label">Password:</label>
                    <div class="col-sm-10">
                        <input class="form-control form-control" type="text" name="password" />
                    </div>
                </div>

                <div class="input-group mb-3">
                    <label class="form-label col-sm-2 col-form-label">Blocked:</label>
                    <div class="input-group-text">
                        <input class="form-check-input mt-0" type="checkbox" name="blocked"
                               ${user.blocked ? 'checked' : ''}
                               aria-label="Checkbox for following text input">
                    </div>

                </div>

                <button class="btn btn-primary" type="submit">Save</button>

            </form>

            <hr/>
            <div>
            <div class="mb-3 row"><label class="form-label col-sm-2 col-form-label">Amount summary:</label>
                <div class="col-sm-10">
                    <input class="form-control form-control" type="text" name="login"
                           value="${requestScope.amountSummary}" readonly />
                </div>
            </div>
            </div>

            <hr/>
            <h2>Orders</h2>
            <div class="table-responsive">
                <table class="table">
                    <thead>
                    <tr>
                        <th>Order</th>
                        <th>Car</th>
                        <th>With driver</th>
                        <th>Term</th>
                        <th>Price</th>
                        <th>Rejected</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${requestScope.orders}" var="order">
                        <tr class='clickable-row' data-href='orders?id=${order.getId()}'>
                            <td>${order.id}</td>
                            <td>${order.getCar().getBrand().getName()} ${order.getCar().getName()}</td>
                            <td>${order.isWithDriver()}</td>
                            <td>${order.getLeaseTerm()}</td>
                            <td class="text-sm-end">
                                <fmt:formatNumber value="${order.price}" type="number" minFractionDigits = "2"/> UAH</td>
                            <td>${order.rejected ? "yes" : "no"}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </section>

</div>

<%@include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
