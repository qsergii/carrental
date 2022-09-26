<%@ page contentType="text/html;charset=UTF-8" %>
<html lang="en">
<head>
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <title>Registration</title>
</head>
<body>

<%@include file="/WEB-INF/jspf/header.jspf" %>

<section class="position-relative py-4 py-xl-5">
    <div class="container">
        <div class="row mb-5">
            <div class="col-md-8 col-xl-6 text-center mx-auto">
                <h2>Log in</h2>
                <p class="w-lg-50">Type your email and password and press Sign In</p>
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
                        <p class="text-muted">Already registered? <a href="login">Log In</a></p>
                        <form class="text-center" action="registration" method="post">
                            <div class="mb-3">
                                <input class="form-control" type="text" name="login" placeholder="Login" required>
                            </div>
                            <div class="mb-3">
                                <input id="phone" class="form-control" lass="form-control" type="text"
                                       name="phone" placeholder="Phone +380501234567" required>
                            </div>
                            <div class="mb-3">
                                <input class="form-control" type="email" name="email" placeholder="Email" required>
                            </div>
                            <div class="mb-3">
                                <input class="form-control" type="text" name="first_name" placeholder="First name" required>
                            </div>
                            <div class="mb-3">
                                <input class="form-control" type="text" name="last_name" placeholder="Last name" required>
                            </div>
                            <div class="mb-3">
                                <input class="form-control" type="password" name="password" placeholder="Password" required>
                            </div>
                            <div class="mb-3">
                                <input class="form-control" type="password" name="password2" placeholder="Re-type password" required>
                            </div>
                            <c:if test="${param.message != null}">
                                <div class="alert alert-warning" role="alert">${param.message}</div>
                            </c:if>
                            <div class="mb-3">
                                <button class="btn btn-primary d-block w-100" type="submit">Sign Up</button>
                            </div>
                        </form>
                        <script src="${path}/assets/js/signup.js"></script>
                        <p class="text-muted">Forgot your password?<br>
                            Call manager +380507007070</p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</section>

<%@ include file="/WEB-INF/jspf/footer.jspf" %>

</body>
</html>
