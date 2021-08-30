<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="hello-servlet">Hello</a><br>
<%-- <form name="quiz-test-form" action="${pageContext.request.contextPath}/Quiz" method="post">
    <input name="btn-to-quiz-test-page" value="Start test" type="submit" formtarget="_blank">
    <input hidden name="QuizID" value="TEST01">
</form>
<form name="quiz-test-form" action="${pageContext.request.contextPath}/AddQuestion" method="post">
    <input name="btn-to-quiz-test-page" value="Add question" type="submit">
</form> --%>

<a href="${pageContext.request.contextPath}/Quiz?QuizID=TEST01" target="_blank">Đến trang làm bài TEST</a> <br>
<a href="${pageContext.request.contextPath}/Result?QuizID=TEST01" target="_blank">Đến trang kết quả TEST</a>
</body>
</html>