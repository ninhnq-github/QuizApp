<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 8/2/2021
  Time: 2:56 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
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
    <script src="${pageContext.request.contextPath}/js/UploadFileToServlet.js"></script>
    <script src="${pageContext.request.contextPath}/js/addquestion.js"></script>
</head>
<body id="page-mod-quiz-attempt" class="format-topics  path-mod path-mod-quiz chrome dir-ltr lang-vi yui-skin-sam yui3-skin-sam pagelayout-incourse category-81">
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
                        </div>
                    </div>
                </div>
            </header></div></div><!--//page-header-wrapper-->
    <div id="page" class="container-fluid">
        <div id="page-content" class="row">
            <div id="region-main-box" class="col-12">
                <section id="region-main">
                    <div class="card">
                        <div class="card-body">
                            <fieldset class="clearfix collapsible collapsed"  id="id_general">
                                <legend class="ftoggler">Ngân hàng câu hỏi</legend>
                                <div class="fcontainer clearfix">
                                    <div id="fitem_id_category" class="form-group row fitem">
                                        <div class="col-md-3 col-form-label d-flex pb-0 pr-md-0">
                                            <label class="d-inline word-break " for="1231-selection">Nhập danh mục</label>
                                        </div>
                                        <div class="col-md-9 form-inline align-items-start felement" data-fieldtype="questioncategory">
                                            <select class="form-control custom-select" name="category" id="1231-selection">
                                                <option value="<c:out value="${requestScope.mlistBank.get(0).id}"/>" selected>
                                                    <c:out value="${requestScope.mlistBank.get(0).name}"/>
                                                </option>
                                                <c:forEach var="ij" begin="1" end="${requestScope.mlistBank.size()-1}">
                                                    <option value="<c:out value="${requestScope.mlistBank.get(ij).id}"/>">
                                                        <c:out value="${requestScope.mlistBank.get(ij).name}"/>
                                                    </option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div id="fitem_id_quesbank_new" class="form-group row fitem">
                                        <div class="col-md-3 col-form-label d-flex pb-0 pr-md-0">
                                            <label class="d-inline word-break " for="add-new-ques-bank-checkbox">Ngân hàng câu hỏi mới</label>
                                        </div>
                                        <div class="col-md-9 form-inline align-items-center felement" data-fieldtype="questioncategory">
                                            <input onclick="check_newQuestionBank()" name="add-new-ques-bank" id="add-new-ques-bank-checkbox"  type="checkbox" style="margin-right: 20px;">
                                            <input type="text" name="textbox-name-of-new-question-bank" id="1231-textbox" disabled class="new-question-bank-text" maxlength="50"/>
                                        </div>
                                    </div>
                                    <div id="fgroup_id_categorygroup" class="form-group row fitem femptylabel" data-groupname="categorygroup">
                                        <div class="col-md-3 col-form-label d-flex pb-0 pr-md-0">
                                            <span class="ml-1 ml-md-auto d-flex align-items-center align-self-start"></span>
                                        </div>
                                        <div class="col-md-9 form-inline align-items-start felement" data-fieldtype="group">
                                            <fieldset class="w-100 m-0 p-0 border-0">
                                                <legend class="sr-only"></legend>
                                            </fieldset>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                            <fieldset class="clearfix collapsible"  id="id_importfileupload">
                                <legend class="ftoggler">Nhập câu hỏi từ tệp</legend>
                                <div class="fcontainer clearfix">
                                    <div id="fitem_id_newfile" class="form-group row  fitem   " >
                                        <div class="col-md-3 col-form-label d-flex pb-0 pr-md-0">
                                            <p id="id_newfile_label" class="mb-0 d-inline" aria-hidden="true">Nhập dữ liệu</p>
                                        </div>
                                        <div class="col-md-9 form-inline align-items-start felement" data-fieldtype="filepicker">
                                            <fieldset class="w-100 m-0 p-0 border-0" id="id_newfile_fieldset">
                                                <legend class="sr-only">Nhập dữ liệu</legend>
                                            </fieldset>
                                            <form name="FormAddNewQuestion" id="1312-form-add-new-question" enctype="multipart/form-data" method="POST" action="${pageContext.request.contextPath}/NewQuestion">
                                                <input type="file" name="FIle txt dữ liệu" accept="text/plain" onchange="UploadFileToServlet()"/>
                                                <div class="form-control-feedback invalid-feedback" id="id_error_newfile"></div>
                                                <input type="text" hidden name="questions_file_name" id="1231-filename" value=""/>
                                            </form>
                                        </div>
                                    </div>
                                    <div id="fitem_id_submitbutton" class="form-group row fitem femptylabel" >
                                        <div class="col-md-3 col-form-label d-flex pb-0 pr-md-0">
                                            <span class="ml-1 ml-md-auto d-flex align-items-center align-self-start"></span>
                                        </div>
                                        <div class="col-md-9 form-inline align-items-start felement" data-fieldtype="submit">
                                            <input type="button"
                                                   class="btn btn-primary"
                                                   name="submitbutton"
                                                   id="id_submitbutton"
                                                   value="Nhập dữ liệu"
                                                   onclick="addNewQuestion()">
                                            <div class="form-control-feedback invalid-feedback" id="id_error_submitbutton">
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </fieldset>
                        </div>
                    </div>
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