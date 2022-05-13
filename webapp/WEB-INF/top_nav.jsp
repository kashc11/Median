<%@page import="com.medianpckg.helper.Keys"%>
<%@page import="com.medianpckg.helper.Keys"%>
<%@page import="com.medianpckg.model.User"%>

<nav class="container-fluid" id="main-nav-user-home">
    <div class="row">
        <div class="col-md-6" id="user-tools-left">
            <a href="./news-feed.jsp" id="main-logo">Median</a>
<!--            <div id="search-box">
                <input type="text" name="search-friend">
                <button><i class="fa fa-search"></i></button>
            </div>-->
        </div>
        <div class="col-md-6" id="user-tools-right">
            <ul>
               <li>
                    <a href="profile">                        
                        <%
                            User user = (User)session.getAttribute(Keys.USER);
                        %>
                        <img class="avatar24" src="data:image/png;base64,<%= user.getAvatar() %>">
                        <%= user.getFirstName() %>
                    </a>
                </li>
                <li>
                    <a href="/"><i class="fa fa-users" aria-hidden="true"></i></a>
                    <span class="mess-notif">9</span>
                </li>
                <li>
                    <a id="show-chat-box-button" href="/"><i class="fa fa-weixin" aria-hidden="true"></i></a>
                    <span class="mess-notif">25</span>
                </li>
                <li>
                    <a href="/"><i class="fa fa-globe" aria-hidden="true"></i></a>
                    <span class="mess-notif">5</span>
                </li>
                <%if (session.getAttribute(Keys.USER) != null) {%>
                <li>
                    <a href="logout"><i class="fa fa-power-off" aria-hidden="true"></i></a>
                </li>
                <%}%>
            </ul>
        </div>
    </div>
</nav>