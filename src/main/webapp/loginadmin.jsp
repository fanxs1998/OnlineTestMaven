<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<%
String errMsg=(String)session.getAttribute("err");
if( errMsg!=null ) { %>
<div style="color:red;"><%=errMsg %></div>
<% session.removeAttribute("err");
} %>
<form action="LoginAdminServlet">
AdminId<input type="text" name="id"><br/>
Password<input type="password" name="password"><br/>
<input type="submit" value="登录">
<input type="reset" value="重填">
</form> 

</body>
</html>