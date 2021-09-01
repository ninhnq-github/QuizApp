<%--
  Created by IntelliJ IDEA.
  User: ninhn
  Date: 8/31/2021
  Time: 2:12 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>
<% if (session.getAttribute("authentication")!=null &&
        ((String)session.getAttribute("authentication")).equals("valid"))
    response.sendRedirect(pageContext.getServletContext().getContextPath()+"/Home");%>
<html>
<head>
    <title>ĐĂNG NHẬP</title>
    <!-- custom-theme -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="keywords" content="Elegant Login Form Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
    <script type="application/x-javascript"> addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false);
    function hideURLbar(){ window.scrollTo(0,1); } </script>
    <!-- //custom-theme  -->
    <link rel="stylesheet" href="${pageContext.request.contextPath}/stylesheet/style.css">
    <!-- font-awesome icons -->
    <link href="${pageContext.request.contextPath}/stylesheet/font-awesome.css" rel="stylesheet">
    <!-- //font-awesome icons -->
    <link href="//fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
</head>
<body>
    <div class="login-form w3_form">
        <!--  Title-->
        <div class="login-title w3_title">
            <h1>PHẦN MỀM TRẮC NGHIỆM ONLINE</h1>
        </div>
        <div class="login w3_login">
            <h2 class="login-header w3_header">Đăng nhập</h2>
            <div class="w3l_grid">
                <form class="login-container" action="${pageContext.request.contextPath}/Login" method="post">
                    <input type="text" placeholder="Tên đăng nhập" Name="account" required="" >
                    <input type="password" placeholder="Mật khẩu" Name="password" required="">
                    <c:if test="${requestScope.status!=null}">
                        <span class="login-error-status" >${requestScope.status}</span>
                    </c:if>
                    <input type="submit" value="Đăng nhập">
                </form>
                <div class="second-section w3_section">
                    <div class="bottom-header w3_bottom">
                        <h3>OR</h3>
                    </div>
                    <div class="social-links w3_social">
                        <ul>
                            <!-- facebook -->
                            <li> <a class="facebook" href="#" target="blank"><i class="fa fa-facebook"></i></a></li>
                            <!-- twitter -->
                            <li> <a class="twitter" href="#" target="blank"><i class="fa fa-twitter"></i></a></li>
                            <!-- google plus -->
                            <li> <a class="googleplus" href="#" target="blank"><i class="fa fa-google-plus"></i></a></li>
                        </ul>
                    </div>
                </div>
                <div class="bottom-text w3_bottom_text">
                    <p>Chưa có tài khoản?<a href="#">Đăng kí ngay!</a></p>
                    <h4> <a href="#">Bạn quên mật khẩu?</a></h4>
                </div>
            </div>
        </div>
    </div>
    <div class="footer-w3l">
        <p class="agile"> &copy; 2017 Elegant Login Form . All Rights Reserved | Design by <a href="http://w3layouts.com">W3layouts</a></p>
    </div>
</body>
</html>
