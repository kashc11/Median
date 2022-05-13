<%@page import="com.medianpckg.helper.Keys"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div id="online-list">
<!--    <div id="friend-detail-popup">
        <span id="back-triange"></span>
        <span id="front-triange"></span>
        <img class="avatar-small" src="img/fog-3914990_640.jpg">
        <div id="friend-detail-popup-detail">
            <p>
                <a id="profile-full-name" href="/">Peter Jonathan</a>
                <span>36 posts, 23 comments</span>
            </p>
            <p>
                <i class="fa fa-user-plus" aria-hidden="true"></i> Became friends with <br><a href="/">Hien Tran and 3 others...</a>
            </p>
            <p>
                <i class="fa fa-user-plus" aria-hidden="true"></i> Became friends with <br><a href="/">Ca Chua and 3 others...</a>
            </p>
        </div>
    </div>-->
    <ul>
        <c:set var="list" value="${sessionScope[Keys.FRIEND_LIST]}" />
        <c:forEach var="friend" items="${list}">
            <li id="${friend.emailOrMobile}">
                <img class="avatar-small" 
                     src="data:image/png;base64,${friend.avatar}">
                <span>${friend.lastName} ${friend.firstName}</span>
                <span></span>
        </c:forEach>
    </ul>
</div>