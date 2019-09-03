
<%@ page language="java" import="java.util.*,beans.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%
		Stu stu = (Stu) session.getAttribute("stu");
		if (stu == null) {
			response.sendRedirect("loginadmin.jsp");
		}
	%>
	<form action="SaveStuServlet">
		学号<input type="text" name="id" value="<%=stu.getId()%>"
			readonly="readonly" style="background-color: lightgrey;"> <br />
		姓名<input type="text" name="name" value="<%=stu.getName()%>">
		<br /> 密码<input type="text" name="password"
			value="<%=stu.getPassword()%>"><br />
		<button type="submit">更改</button>
	</form>
	<%
		String err = (String) session.getAttribute("err");
		if (err != null) {
			out.print("<div style='color:red'>" + err + "</div>");
			session.removeAttribute("err");
		}
	%>


</body>
</html>