<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.medianpckg.helper.Keys"%>
<%@page import="java.util.Objects"%>
<!DOCTYPE html>
<html>


    <jsp:include page="/WEB-INF/head_tag.jsp">
        <jsp:param name="title" value="Profile" />
    </jsp:include>

    <body>
        <%@include file="/WEB-INF/top_nav.jsp" %>

        <section class="container-fluid" id="main-body">
            <div class="row no-pad">
                <div class="col-md-8 no-pad">
                    <div class="profile-header">
                        <div class="profile-header-top">
                            <span id="profile-button-add-cover">
                                
<!--                                allows the entire file will be included in the form data to be sent to the server-side.-->
                                <form action="upload-image" id="frmUploadPhoto" enctype="multipart/form-data" method="post">
                                    <i class="fa fa-camera" aria-hidden="true"></i> Add Cover Photo
                                    <input class="input-cover-photo" type="file" id="userCoverPhoto" name="userCoverPhoto" />
                                </form>
                            </span>
                            <img src="data:image/png;base64,<%= user.getAvatar()%>">

                            <h3>
                                <%= user.getFirstName()%>
                                <%= user.getLastName()%>
                            </h3>
                        </div>
                    </div>
                    <div class="profile-body">
                        <div class="profile-body-header">
                            <h3><i class="fa fa-user" aria-hidden="true"></i> About</h3>
                        </div>
                        <div class="profile-body-content">
                            <div class="row no-pad">P
                                <div class="col-md-8 profile-body-content-editing">
                                    <h4>Overview</h4>
                                    <div class="overview-form">
                                        <div class="alert-danger">
                                            <%= Objects.toString(session.getAttribute(Keys.ERROR), "")%>
                                        </div>
                                        <div class="alert-success">
                                            <%= Objects.toString(session.getAttribute(Keys.SUCCESS), "")%>
                                        </div>

                                        <form action="profile" method="post">
                                            <label>First Name:</label>
                                            <input required type="text" name="first-name" 
                                                   value="<%=user.getFirstName()%>" maxlength="30" />

                                            <label>Last Name:</label>
                                            <input required type="text" name="last-name" 
                                                   value="<%=user.getLastName()%>" maxlength="30" />

                                            <label>Email/Mobile:</label>
                                            <input required type="text" name="mobile-or-email" 
                                                   value="<%=user.getEmailOrMobile()%>" />

                                            <label>Password:</label>
                                            <input required type="password" name="user-password" 
                                                   value="<%=user.getPassword()%>" />

                                            <label>Sex:</label>
                                            <input checked type="radio" name="sex" value="male"> <label class="light" for="male">Male</label>
                                            <input type="radio" name="sex" value="female"> <label class="light" for="female">Female</label>

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

                                            <button name="action" value="update-profile" 
                                                    type="submit">Save Changes</button>
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <script>
            $(function () {
                
                $('#userCoverPhoto').change(function() {
                    $("#frmUploadPhoto").submit();
                });
                
                for (var i = 1; i <= 31; i++) {
                    $("#days").append("<option>" + i + "</option>");
                }

                for (var i = 1; i <= 12; i++) {
                    $("#months").append("<option>" + i + "</option>");
                }

                for (var i = 2016; i >= 1905; i--) {
                    $("#years").append("<option>" + i + "</option>");
                }
                var viewportHeight = $(window).height();
                $("#online-list").css("max-height", viewportHeight);
            });
            
        </script>
        <script src="js/app.js"></script>
    </body>

</html>
