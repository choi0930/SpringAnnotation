<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%
    request.setCharacterEncoding("utf-8");
    %>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <c:set var="contextPath" value="${pageContext.request.contextPath }"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
	.text{
		text-align: right;
	}
</style>
</head>
<body>
<div id="modForm">
	<form  method ="post" action="${contextPath }/member/modMember.do">
	<h1 align="center">회원정보 수정</h1>
		<table align = "center">
			<tr>
				<td width="200" class="text" >아이디</td>
				<td width="400"><input type="text" value="${memberVO.id }" disabled>
					<input type="hidden" name="id" value="${memberVO.id }"></td>
			</tr>
			<tr>
				<td width="200" class="text">비밀번호</td>
				<td width="400"><input type="password" name="pwd" value="${memberVO.pwd }"></td>
			</tr>
			<tr>
				<td width="200" class="text">이름</td>
				<td width="400"><input type="text" name="name" value="${memberVO.name}"></td>
			</tr>
			<tr>
				<td width="200" class="text">이메일</td>
				<td width="400"><input type="text" name="email" value="${memberVO.email }"></td>
			</tr>
			<tr>
				<td width="200"></td>
				<td width="400"><input type="submit" value="수정하기">
				<input type="reset" value="다시입력"></td>
			</tr>
		</table>
	</form>
	</div>
</body>
</html>