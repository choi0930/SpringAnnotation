<%@ page language="java" contentType="text/html; charset=UTF-8"
    isELIgnored="false"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath }" /> 
    <%
    request.setCharacterEncoding("utf-8");
    %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>로그인</title>
</head>
<body>
	<form method="post" action="${contextPath }/test/login4.do">
		아이디<input type="text" name="userID"><br>
		이름<input type="text" name="userName"><br>
		<input type="hidden" name="email" value="test@test.com">
		<input type="submit" value="로그인">
		<input type="reset" value="다시입력">
	</form>
</body>
</html>