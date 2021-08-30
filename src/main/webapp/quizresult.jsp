<%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 8/30/2021
  Time: 4:23 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
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
    <script src="${pageContext.request.contextPath}/js/time.js"></script>

</head>

<body  onload="getServerTime()" id="page-mod-quiz-attempt" class="format-topics  path-mod path-mod-quiz chrome dir-ltr lang-vi yui-skin-sam yui3-skin-sam pagelayout-incourse category-81">

<div class="toast-wrapper mx-auto py-0 fixed-top" role="status" aria-live="polite"></div>

<div id="page-wrapper" class="page-wrapper ">

    <header id="header" class="page-header page-header-top-bar navbar" role="banner">
        <div class="top-bar">
            <div class="container-fluid">
                <div class="top-bar-inner">
                    <div class="special-wrapper navbar">
                        <div class="app-name-main">
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
                                                        <div class="dropdown">
                                                            <a href="#" tabindex="0" class="d-inline-block  dropdown-toggle icon-no-margin"
                                                               id="action-menu-toggle-1" aria-label="Thư mục người dùng"
                                                               data-toggle="dropdown" role="button" aria-haspopup="true"
                                                               aria-expanded="false" aria-controls="action-menu-1-menu">
                                                                <span class="userbutton"><span class="usertext mr-1"><c:out value="${requestScope.user_name}"/></span><span class="avatars"><span class="avatar current">
                                                                    <img src="${pageContext.request.contextPath}/icon/profile.png" class="userpicture" width="35" height="35" aria-hidden="true" /></span></span></span>
                                                                <b class="caret"></b>
                                                            </a>
                                                            <div class="dropdown-menu dropdown-menu-right menu  align-tr-br" id="action-menu-1-menu" data-rel="menu-content" aria-labelledby="action-menu-toggle-1" role="menu" data-align="tr-br">
                                                                <a href="#" class="dropdown-item menu-action" role="menuitem" data-title="mymoodle,admin" aria-labelledby="actionmenuaction-1">
                                                                    <i class="icon fa fa-tachometer fa-fw " aria-hidden="true"  ></i>
                                                                    <span class="menu-action-text" id="actionmenuaction-1">Nhà của tôi</span>
                                                                </a>

                                                                <div class="dropdown-divider" role="presentation"><span class="filler">&nbsp;</span></div>

                                                                <a href="#" class="dropdown-item menu-action" role="menuitem" data-title="profile,moodle" aria-labelledby="actionmenuaction-2">
                                                                    <i class="icon fa fa-user fa-fw " aria-hidden="true"  ></i>
                                                                    <span class="menu-action-text" id="actionmenuaction-2">Hồ sơ</span>
                                                                </a>

                                                                <div class="dropdown-divider" role="presentation"><span class="filler">&nbsp;</span></div>

                                                                <a href="#" class="dropdown-item menu-action" role="menuitem" data-title="grades,grades" aria-labelledby="actionmenuaction-3">
                                                                    <i class="icon fa fa-table fa-fw " aria-hidden="true"  ></i>
                                                                    <span class="menu-action-text" id="actionmenuaction-3">Điểm</span>
                                                                </a>

                                                                <div class="dropdown-divider" role="presentation"><span class="filler">&nbsp;</span></div>

                                                                <a href="#" class="dropdown-item menu-action" role="menuitem" data-title="logout,moodle" aria-labelledby="actionmenuaction-6">
                                                                    <i class="icon fa fa-sign-out fa-fw " aria-hidden="true"  ></i>
                                                                    <span class="menu-action-text" id="actionmenuaction-6">Thoát</span>
                                                                </a>

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

    <div class="quiz-time-left-flex" hidden="true">
        <span class="text">Thời gian còn lại: <span class="time" id="time-left-1210">16:04</span></span>
    </div>

    <div class="page-header-wrapper has-course-header-image">
        <div class="course-header-bg"><div class="course-header-image"></div><div class="mask"></div></div>
        <div class="container-fluid">
            <header id="page-header" class="row">
                <div class="col-12 pt-3 pb-3">
                    <div class="card ">
                        <div class="card-body ">
                            <div class="d-sm-flex align-items-center">
                                <div class="mr-auto">
                                    <div class="page-context-header"><div class="page-header-headings"><h1><c:out value="${requestScope.quiz_name}"/></h1></div></div>
                                </div>

                                <div class="header-actions-container flex-shrink-0" data-region="header-actions-container">
                                </div>
                            </div>
                            <div class="d-flex flex-wrap">
                                <div id="page-navbar" style="padding-left: 20px;">
                                    <nav aria-label="Thanh điều hướng">Đề thi gồm có <c:out value="${requestScope.no_ques}"/> câu, thời gian thi là <c:out value="${requestScope.time}"/> phút</nav>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </header></div></div><!--//page-header-wrapper-->

    <div id="page" class="container-fluid">
        <div id="page-content" class="row">
            <div id="region-main-box" class="col-12">
                <section id="region-main" class="has-blocks">
                    <div class="card">
                        <div class="card-body">
                            <span class="notifications" id="user-notifications"></span>
                            <div role="main">
                                <span id="maincontent"></span>

                                <table class="generaltable generalbox quizreviewsummary">
                                    <tbody>
                                    <tr>
                                        <th class="cell" scope="row">Bắt đầu vào lúc</th>
                                        <td class="cell">Monday, 2 August 2021, 1:08 AM</td>
                                    </tr>
                                    <tr>
                                        <th class="cell" scope="row">State</th>
                                        <td class="cell">Finished</td>
                                    </tr>
                                    <tr>
                                        <th class="cell" scope="row">Kết thúc lúc</th>
                                        <td class="cell">Monday, 2 August 2021, 1:10 AM</td>
                                    </tr>
                                    <tr>
                                        <th class="cell" scope="row">Thời gian thực hiện</th>
                                        <td class="cell">2 phút 1 giây</td>
                                    </tr>
                                    <tr>
                                        <th class="cell" scope="row">Điểm</th>
                                        <td class="cell"><b>2,00</b> out of 10,00 (<b>20</b>%)</td>
                                    </tr>
                                    </tbody>
                                </table>

                                <div id="quiz-timer-wrapper" hidden class="mb-2">
                                    <div id="quiz-timer" class="quiz-timer-inner py-1 px-2 ml-auto" role="timer" aria-atomic="true" aria-relevant="text">
                                        Thời gian còn lại <span id="quiz-time-left">16:45</span>
                                    </div>
                                    <div class="box py-3 quizaccessnotices" style="visibility: hidden;">
                                        <h3>You can preview this quiz, but if this were a real attempt, you would be blocked because:</h3>
                                        <p>Xin lỗi, trắc nghiệm không thể vào được</p>
                                    </div>
                                </div>

                                <div class="TestQuestionWraper">
                                    <%int ques_id = 1;%>
                                    <c:forEach var="ques" items="${requestScope.mlistQuestion}">
                                        <div id="ques_id${ques.question.id}" class="que multichoice deferredfeedback notyetanswered">
                                            <c:if test="${ques.question.type!=-1}">
                                                <div class="info"><h3 class="no">Câu hỏi <span class="qno"><%out.print(ques_id);%></span></h3>
                                                    <div class="state">Chưa trả lời</div>
                                                    <div class="grade">Đạt điểm 1,00</div>
                                                    <div class="questionflag editable" aria-atomic="true" aria-relevant="text" aria-live="assertive">
                                                        <input type="hidden" name="${ques.question.id}:flagged" value="0" />
                                                        <input type="checkbox" id="${ques.question.id}:flaggedcheckbox" name="${ques.question.id}:flagged" value="1" onclick="onflagged('${ques.question.id}:flaggedcheckbox','quiznavbutton_<%out.print(ques_id);%>','${ques.question.id}:flaggedimg')" />
                                                        <input type="hidden" value="" class="questionflagpostdata" />
                                                        <label id="${ques.question.id}:flaggedlabel" for="${ques.question.id}:flaggedcheckbox">
                                                            <img src="${pageContext.request.contextPath}/icon/unflagged.svg" alt="Không gắn cờ" class="questionflagimage" id="${ques.question.id}:flaggedimg" />
                                                            <span>Đặt cờ</span>
                                                        </label>
                                                    </div>
                                                </div>
                                            </c:if>
                                            <div class="content">
                                                <div class="formulation clearfix">
                                                    <h4 class="accesshide">Đoạn văn câu hỏi</h4>
                                                    <input type="hidden" name="${ques.question.id}:sequencecheck" value="1" />
                                                    <div class="qtext"><p id="move_to_this_${ques.question.id}"><c:out escapeXml="false" value="${ques.question.content}"/></p></div>
                                                    <div class="ablock">
                                                        <div class="answer">
                                                            <%char ch = 'a';%>
                                                            <c:forEach var="ans" items="${ques.answer}">
                                                                <div class="r0">
                                                                    <input type="radio" name="${ques.question.id}answer" value="${ans.id}"
                                                                           id="${ques.question.id}answer${ans.id}" aria-labelledby="${ques.question.id}answer${ans.id}_label"
                                                                           onclick="setClearchoice('${ques.question.id}','quiznavbutton_<%out.print(ques_id);%>')"/>
                                                                    <div class="d-flex w-100" id="${ans.id}answer0_label" data-region="answer-label">
                                                                        <span class="answernumber"><%out.print(ch);%>. </span>
                                                                        <div class="flex-fill ml-1">${ans.content}</div></div>
                                                                        <i class="icon fa fa-check text-success fa-fw " title="Đúng" aria-label="Đúng" id="yui_3_17_2_1_1630314970443_143"></i>
                                                                </div>
                                                                <%ch++;%>
                                                            </c:forEach>
                                                        </div>
                                                        <div id="${ques.question.id}_clearchoice" class="qtype_multichoice_clearchoice" hidden="true">
                                                            <input type="radio" name="${ques.question.id}answer" id="${ques.question.id}answer-1" value="-1" class="sr-only" aria-hidden="true">
                                                            <label for="${ques.question.id}answer-1"><a tabindex="0" role="button" class="btn btn-link ml-3 mt-n1 mb-n1" href="#" onclick="clearchoice('${ques.question.id}','quiznavbutton_<%out.print(ques_id);%>')">Clear my choice</a></label>
                                                        </div>
                                                    </div>
                                                </div>
                                                <div class="outcome clearfix">
                                                    <h4 class="accesshide">Phản hồi</h4>
                                                    <div class="feedback">
                                                        <div class="rightanswer">The correct answer is: Tự phát, duy tâm, duy vật</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <c:if test="${ques.question.type!=-1}">
                                            <%ques_id++;%>
                                        </c:if>
                                    </c:forEach>
                                </div>

                            </div>
                        </div>
                    </div>
                </section>
                <section data-region="blocks-column" class="d-print-none" aria-label="Các khối">
                    <aside id="block-region-side-pre" class="block-region" data-blockregion="side-pre" data-droptarget="1"><a href="#sb-1" class="sr-only sr-only-focusable">Bỏ qua &lt;span id=&quot;mod_quiz_navblock_title&quot;&gt;Bảng câu hỏi&lt;/span&gt;</a>
                        <section id="mod_quiz_navblock"
                                 class=" block block_fake  card mb-3"
                                 role="navigation"
                                 data-block="_fake"
                                 aria-labelledby="instance-fakeid-6106e31164b09-header">
                            <div class="card-body p-3">

                                <h5 id="instance-fakeid-6106e31164b09-header" class="card-title d-inline">
                                    <span id="mod_quiz_navblock_title">Bảng câu hỏi</span>
                                </h5>

                                <div class="card-text content mt-3">

                                    <div class="qn_buttons clearfix multipages">
                                        <%int Qid=1;%>
                                        <c:forEach var="i" begin="1" end="${requestScope.mlistQuestion.size()}" step="1">
                                            <c:if test="${requestScope.mlistQuestion.get(i-1).question.type!=-1}">
                                                <a class="qnbutton notyetanswered free btn thispage" id="quiznavbutton_<%out.print(Qid);%>" title="Chưa trả lời" data-quiz-page="0"
                                                   href="#ques_id${requestScope.mlistQuestion.get(i-1).question.id}">
                                                    <span class="thispageholder"></span>
                                                    <span class="trafficlight" id="quiznavbutton_<%out.print(Qid);%>_img"></span>
                                                    <span class="accesshide">Question </span> <%out.print(Qid);%>
                                                    <span class="accesshide"> This page <span class="flagstate"></span></span>
                                                </a>
                                                <%Qid++;%>
                                            </c:if>
                                        </c:forEach>
                                    </div>
                                    <div class="othernav">
                                        <div class="singlebutton">
                                            <form method="post" action="#" >
                                                <input type="hidden" name="cmid" value="374677">
                                                <input type="hidden" name="sesskey" value="5ddeH4Atnk">
                                                <input type="hidden" name="forcenew" value="1">
                                                <button type="submit" class="btn btn-secondary"
                                                        id="single_button6106e311627ef3"
                                                        title="">Nộp và kết thúc bài làm</button>
                                            </form>
                                        </div>
                                    </div>
                                    <div class="footer"></div>

                                </div>
                            </div>
                        </section>
                        <span id="sb-1"></span></aside>
                </section>
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
</html>
