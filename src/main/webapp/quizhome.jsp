<%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 8/31/2021
  Time: 3:44 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
<%if (session.getAttribute("authentication")==null)
    response.sendRedirect(pageContext.getServletContext().getContextPath()+"/login.jsp");%>
<html>
<head>
    <title><c:out value="${requestScope.quiz_title.toString()}"/></title>
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/icon/quiz.png" />
    <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet" />
    <link href="https://fonts.googleapis.com/css?family=Roboto:300,400,500,700" rel="stylesheet" />
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/yui-moodlesimple-min.css"/>
    <script id="firstthemesheet" type="text/css">/** Required in order to fix style inclusion problems in IE with YUI **/</script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/all.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/stylesheet/newstyle.css">
    <script src="https://code.jquery.com/jquery-3.4.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/quiz.js"></script>
    <script src="${pageContext.request.contextPath}/js/home.js"></script>
    <script src="${pageContext.request.contextPath}/js/time.js"></script>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,100,300,700' rel='stylesheet' type='text/css'>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

</head>
<body  onload="getServerTime()" id="page-mod-quiz-attempt" class="format-topics  path-mod path-mod-quiz chrome dir-ltr lang-vi yui-skin-sam yui3-skin-sam pagelayout-incourse category-81">
<div class="toast-wrapper mx-auto py-0 fixed-top" role="status" aria-live="polite"></div>
<div id="page-wrapper" class="page-wrapper " style="padding-top: 0px;">
    <header id="header" class="page-header page-header-top-bar navbar" role="banner">
        <div class="top-bar">
            <div class="container-fluid">
                <div class="top-bar-inner">
                    <div class="special-wrapper navbar">
                        <div class="app-name-main" style="margin-top: -10px">
                            <img src="${pageContext.request.contextPath}/icon/app-icon.svg" class="app-icon-img">
                            <span class="app-name-text">PHẦN MỀM THI TRẮC NGHIỆM ONLINE</span>
                        </div>
                        <div name="time" class="time-block" hidden>
                            <p name="time" id="server-time-block">00000000000000</p>
                        </div>
                        <div class="utilities">
                            <div class="utilities-inner d-flex align-items-center">
                                <ul class="d-flex usernav p-0 ml-2 mb-0 align-items-center">
                                    <!-- navbar_plugin_output -->
                                    <li class="d-flex mr-3">
                                        <div class="popover-region collapsed popover-region-notifications"
                                             id="nav-notification-popover-container" data-userid="30807"
                                             data-region="popover-region">
                                        </div>
                                    </li>
                                    <!-- user_menu -->
                                    <li class="d-flex">
                                        <div class="usermenu">
                                            <div class="action-menu moodle-actionmenu nowrap-items d-inline" id="action-menu-1" data-enhance="moodle-core-actionmenu">
                                                <div class="menubar d-flex " id="action-menu-1-menubar" role="menubar">
                                                    <div class="action-menu-trigger">
                                                        <div class="dropdown" style="margin-top: 10px">
                                                            <a href="#" tabindex="0" class="d-inline-block  dropdown-toggle icon-no-margin"
                                                               id="action-menu-toggle-1" aria-label="Thư mục người dùng"
                                                               data-toggle="dropdown" role="button" aria-haspopup="true"
                                                               aria-expanded="false" aria-controls="action-menu-1-menu">
                                                                <span class="userbutton"><span class="usertext mr-1"><c:out value="${requestScope.user_name}"/></span><span class="avatars"><span class="avatar current">
                                                                    <img src="${pageContext.request.contextPath}/icon/profile.png" class="userpicture" width="35" height="35" aria-hidden="true" /></span></span></span>
                                                                <b class="caret"></b>
                                                            </a>
                                                            <div class="dropdown-menu dropdown-menu-right menu  align-tr-br" id="action-menu-1-menu" data-rel="menu-content" aria-labelledby="action-menu-toggle-1" role="menu" data-align="tr-br">
                                                                <a href="${pageContext.request.contextPath}/Home" class="dropdown-item menu-action" role="menuitem" data-title="mymoodle,admin" aria-labelledby="actionmenuaction-1">
                                                                    <i class="icon" aria-hidden="true"  >☖</i>
                                                                    <span class="menu-action-text" id="actionmenuaction-1">Nhà của tôi</span>
                                                                </a>

                                                                <div class="dropdown-divider" role="presentation"><span class="filler">&nbsp;</span></div>

                                                                <a href="${pageContext.request.contextPath}/History" class="dropdown-item menu-action" role="menuitem" data-title="profile,moodle" aria-labelledby="actionmenuaction-2">
                                                                    <i class="icon" aria-hidden="true">✍</i>
                                                                    <span class="menu-action-text" id="actionmenuaction-2">Hồ sơ</span>
                                                                </a>

                                                                <div class="dropdown-divider" role="presentation"><span class="filler">&nbsp;</span></div>

                                                                <a href="#" class="dropdown-item menu-action" role="menuitem" data-title="grades,grades" aria-labelledby="actionmenuaction-3">
                                                                    <i class="icon" aria-hidden="true">❏</i>
                                                                    <span class="menu-action-text" id="actionmenuaction-3">Điểm</span>
                                                                </a>

                                                                <div class="dropdown-divider" role="presentation"><span class="filler">&nbsp;</span></div>

                                                                <a onclick="logout()" class="dropdown-item menu-action" role="menuitem" data-title="logout,moodle" aria-labelledby="actionmenuaction-6">
                                                                    <i class="icon " aria-hidden="true"  >✖</i>
                                                                    <span class="menu-action-text" id="actionmenuaction-6">Thoát</span>
                                                                </a>

                                                                <form id="112233-logout-form" name="logout-form" action="${pageContext.request.contextPath}/Logout" method="POST" hidden></form>

                                                            </div>
                                                        </div>
                                                    </div>
                                                </div></div></div></li>
                                </ul><!--//user-nav-->
                            </div><!--//utilities-inner-->
                        </div><!--//utilities-->
                    </div>
                </div><!--//top-bar-inner-->
            </div>
        </div><!--//top-bar-->
    </header><!--//header-->
    <div id="page" class="container-fluid">
        <div id="page-content" class="row">
            <div id="region-main-box" class="col-12">
                <section id="region-main" >
                    <div class="card">
                        <div class="card-body">
                            <section class="ftco-section" style="padding-top: 10px;">
                                <div class="container">
                                    <div class="row justify-content-center">
                                        <div class="col-md-6 text-center mb-5">
                                            <h2 class="heading-section">Các bài thi bạn được mời tham gia</h2>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-md-12">
                                            <div class="table-wrap">
                                                <table class="table table-striped">
                                                    <thead>
                                                    <tr>
                                                        <th>MÃ BÀI THI</th>
                                                        <th>TÊN BÀI THI</th>
                                                        <th>SỐ CÂU HỎI</th>
                                                        <th>MỞ ĐỀ THI</th>
                                                        <th>ĐÓNG ĐỀ THI</th>
                                                        <th>TRẠNG THÁI</th>
                                                    </tr>
                                                    </thead>
                                                    <tbody>
                                                    <c:forEach var="quiz" items="${requestScope.quizAssigned}">
                                                        <tr>
                                                            <th scope="row"><c:out value="${quiz.id}"/></th>
                                                            <td><c:out value="${quiz.name}"/></td>
                                                            <c:choose>
                                                                <c:when test="${!quiz.ques.contentEquals('//')}">
                                                                    <td><c:out value="${quiz.ques}"/> Câu / <c:out value="${quiz.time}"/> Phút</td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td>//</td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                            <td><c:out value="${quiz.open}"/></td>
                                                            <td><c:out value="${quiz.close}"/></td>
                                                            <c:choose>
                                                                <c:when test="${quiz.status.contentEquals('OPEN')}">
                                                                    <td><a type="button" onclick="doTest('${quiz.id}')" class="btn btn-success">LÀM BÀI</a></td>
                                                                </c:when>
                                                                <c:when test="${quiz.status.contentEquals('WAIT')}">
                                                                    <td><a href="#" class="btn btn-warning">CHƯA MỞ</a></td>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <td><a href="#" class="btn btn-danger">ĐÃ ĐÓNG</a></td>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </tr>
                                                    </c:forEach>
                                                    </tbody>
                                                </table>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </section>
                        </div>
                    </div>
                </section>
            </div>
        </div>
    </div>
</div>
<footer id="page-footer" class="page-footer">
    <div class="footer-bottom-bar">
        <small class="copyright">Copyright Nguyen Quoc Ninh © 2021</small>
    </div>
</footer>
</div>
<input style="visibility: hidden;" hidden name="dynamic-prefix" value="${pageContext.request.contextPath}" id="112233-page-dynamic-prefix">
</body>
<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/js/popper.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/main.js"></script>
</html>
