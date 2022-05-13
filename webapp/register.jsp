<%@page import="java.util.Objects"%>
<%@page import="com.medianpckg.helper.Keys"%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>


    <jsp:include page="/WEB-INF/head_tag.jsp">
        <jsp:param name="title" value="Register" />
    </jsp:include>

    <body>
        <nav class="container-fluid" id="main-nav-login">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <a href="./news-feed.jsp" id="logo-large">MEDIAN</a>
                    </div>
                    <div class="col-md-6">
                        <form action="/" method="post" class="login-form-top">
                            <div class="login-form-input">
                                <label>Email or phone</label>
                                <input type="text" name="user-login-id" />
                            </div>
                            <div class="login-form-input">
                                <label>Password</label>
                                <input type="password" name="user-login-password" />
                                <button type="button" id="btn-login">Login</button>
                                <a href="/">Forgotten password?</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </nav>

        <section class="container-fluid" id="body-register-form">
            <div class="container">
                <div class="row">
                    <div class="col-md-6">
                        <h3>Create an account</h3>

                        <!--                    used to display an error message-->
                        <div class=”alert-danger”>
                           ${requestScope[Keys.ERROR]}
                        </div>
                        
<!--                        post method as form contains password-->
<!--when the form is submitted, the form data will be sent to the RegisterController-->

                        <form action="register" method="post" class="register-form">
                            <div class="reg-input-full-name">
                                <div class="reg-input" id="first-name">
                                    <input type="text" name="first-name" value="${param["first-name"]}" placeholder="First name">
                                </div>
                                <div class="reg-input" id="last-name">
                                    <input type="text" name="last-name" value="${param["last-name"]}" placeholder="Last name">
                                </div>
                            </div>
                            <div class="reg-input" id="mobile-or-email">
                                <input type="text" name="mobile-or-email" value="${param["mobile-or-email"]}" placeholder="Mobile phone or email address">
                            </div>
                            <div class="reg-input" id="user-password">
                                <input type="password" name="user-password" placeholder="New password">
                            </div>
                            <label style="display:block;">Birthday</label>
                            <div class="reg-input">
                                <select name="day" id="days">
                                    <option>Day</option>
                                </select>
                                <select name="month" id="months">
                                    <option>Month</option>
                                </select>
                                <select name="year" id="years">
                                    <option>Year</option>
                                </select>
                            </div>
                            <div class="reg-input">
                                <input type="radio" checked name="sex" value="female"><label>Female</label>
                                <input type="radio" name="sex" value="male"><label>Male</label>
                            </div>
                            <button type="submit" id="reg-button">Create an account</button>
                            <a href="./login.jsp">Have account? login here</a>
                        </form>
                    </div>
                </div>
            </div>
        </section>

        <footer class="container">
            MEDIAN &copy; 2022
        </footer>

        <script src="./js/app.js"></script>
<!--we want to keep the selected item in the dropdown list and in the radio input type, we need to write some jQuery code.-->
         <script>
            $(function () {
               let day = "${param["day"]}";
                let month = "${param["month"]}";
                let year = "${param["year"]}";
                
                for (var i = 1; i <= 31; i++) {
                    if (i == day)
                        $("#days").append("<option selected>" + i + "</option>");
                    else
                        $("#days").append("<option>" + i + "</option>");
                }
                for (var i = 1; i <= 12; i++) {
                    if (i == month)
                        $("#months").append("<option selected>" + i + "</option>");
                    else
                        $("#months").append("<option>" + i + "</option>");
                }
                for (var i = 2016; i >= 1905; i--) {
                    if (i == year)
                        $("#years").append("<option selected>" + i + "</option>");
                    else
                        $("#years").append("<option>" + i + "</option>");
                }
                 var selectedSex = "${param["sex"]}";
                $("input[name=sex][value=" + selectedSex + "]").prop('checked', true);
            });
        </script>
    </body>

</html>
