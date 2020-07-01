<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"  %>

<%@page import="java.sql.Connection" %>
<%@page import="javax.sql.DataSource" %>
<%@page import="javax.naming.InitialContext" %>
<%@page import="javax.naming.Context" %>

<!DOCTYPE html>
<html><head><meta charset="UTF-8">
<title>DBCP 커넥션 풀 예제 </title>
</head>
<body>

<%
		try{
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			DataSource ds = (DataSource)envCtx.lookup("team2:Book");

			Connection conn = ds.getConnection();
			
			out.print("DBCP Connection .....<br><br>");
			
			conn.close();
		}catch(Exception e){
			e.printStackTrace();
		}
%>
</body>
</html>