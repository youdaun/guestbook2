<%@page import="com.javaex.vo.GuestbookVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
	GuestbookVo gvo = (GuestbookVo)(request.getAttribute("gvo"));
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/guestbook2/gbc">
		비밀번호
		<input type="password" name="password" value="">
		<input type="hidden" name="no" value="<%=gvo.getNo()%>">
		<input type="hidden" name="action" value="delete">
		<button type="submit">확인</button>
	</form>
	
	<a href="./addList.jsp">메인으로 돌아가기</a>

</body>
</html>
