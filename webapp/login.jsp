<%@page import="java.util.Objects"%>
<%@page import="com.medianpckg.helper.Keys"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <jsp:include page="/WEB-INF/head_tag.jsp">
        <jsp:param name="title" value="Login" />
    </jsp:include>

    <body>
        <nav class="container-fluid" id="main-nav-login">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <a href="./news-feed.jsp" id="logo-large">MEDIAN</a>
                    </div>
                </div>
            </div>
        </nav>

        <section class="container-fluid" style="background-color: #C4EDDE; margin-top:-30px;">
            <div class="container">
                <div class="row">
                    <div class="col-md-12">
                        <form action="login" method="post" id="body-login-form">
                            <label>Median - The Final Destination</label>
                            <div class="alert-danger">
                              ${sessionScope[Keys.ERROR]}
                            </div>
                            <input type="text" name="user-login-id" placeholder="Email address or phone number" />
                            <input type="password" name="user-login-password" placeholder="Password" />
                            <button type="submit" id="btn-login">Login</button>
                            <a href="/">Forgotten password?</a> <br>
                            <a href="./register.jsp">Signup for Median?</a>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <footer class="container">
            MEDIAN &copy 2022
        </footer>
    </body>

</html>
