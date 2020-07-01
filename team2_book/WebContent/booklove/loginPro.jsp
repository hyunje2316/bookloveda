<%@page import="book.model.BookDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<jsp:useBean id="vo" class="book.model.BookReviewVO"></jsp:useBean>
<jsp:setProperty property="*" name="vo"/> 

<%
	request.setCharacterEncoding("utf-8");
	String id = request.getParameter("id");
	String pwd = request.getParameter("pwd");
	
	session.setAttribute("memID", id); //setAttribute(네임값, 벨류)
	session.setAttribute("memPWD", pwd); 
	response.sendRedirect("./BookLoginAction.book");
	%>
	

	

<!DOCTYPE html>
<html>
<head><meta charset="UTF-8">
<title>가입 메세지 출력</title>
</head>
<body>
	
</body>
</html> 