<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Login</title>
</head>
<body>

<%@include file="../jspf/header.jspf" %>

<section class="position-relative py-4 py-xl-5">
    <div class="container">
        <div class="row mb-5">
            <div class="col-md-8 col-xl-6 text-center mx-auto">
                <h2>
                    <fmt:message key="login.login"/>
                </h2>
                <p class="w-lg-50"><fmt:message key="login.info"/></p>
            </div>
        </div>
        <div class="row d-flex justify-content-center">
            <div class="col-md-6 col-xl-4">
                <div class="card mb-5">
                    <div class="card-body d-flex flex-column align-items-center">
                        <div class="bs-icon-xl bs-icon-circle bs-icon-primary bs-icon my-4">
                            <svg xmlns="http://www.w3.org/2000/svg" width="1em" height="1em" fill="currentColor"
                                 viewBox="0 0 16 16" class="bi bi-person">
                                <path d="M8 8a3 3 0 1 0 0-6 3 3 0 0 0 0 6zm2-3a2 2 0 1 1-4 0 2 2 0 0 1 4 0zm4 8c0 1-1 1-1 1H3s-1 0-1-1 1-4 6-4 6 3 6 4zm-1-.004c-.001-.246-.154-.986-.832-1.664C11.516 10.68 10.289 10 8 10c-2.29 0-3.516.68-4.168 1.332-.678.678-.83 1.418-.832 1.664h10z"></path>
                            </svg>
                        </div>
                        <p class="text-muted"><fmt:message key="login.new_here"/> <a href="registration"><fmt:message
                                key="login.sign_up"/></a></p>
                        <form class="text-center" method="post">
                            <div class="mb-3">
                                <input class="form-control" type="text" name="login"
                                       placeholder="<fmt:message key="auth.login_word"/>"
                                       required>
                            </div>
                            <div class="mb-3">
                                <input class="form-control" type="password" name="password" placeholder=
                                <fmt:message key="auth.password"/>
                                        required>
                            </div>
                            <c:if test="${param.message != null}">
                                <div class="alert alert-warning" role="alert">${param.message}</div>
                            </c:if>
                            <%--                            <c:if test="${not sessionScope.trusted}">--%>
                            <div class="mb-3">
                                <img src="${path}/captcha/image.jpg"/>
                                <input class="form-control" type="text" name="captcha"
                                       placeholder="<fmt:message key="auth.captcha"/>" required>
                            </div>
                            <%--                            </c:if>--%>
                            <div class="mb-3">
                                <button class="btn btn-primary d-block w-100" type="submit"><fmt:message
                                        key="login.sign_in"/></button>
                            </div>
                            <p class="text-muted">
                                <fmt:message
                                        key="login.forgot_password"/><br><fmt:message
                                    key="Call"/> +380-50-500-50-50
                            </p>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
