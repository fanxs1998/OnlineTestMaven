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
		String referer = request.getHeader("referer");
		if (referer == null || (!referer.endsWith("loginadmin.jsp") && !referer.endsWith("edit.jsp")
				&& !referer.endsWith("control.jsp"))) {
			request.getSession().setAttribute("err", "禁止非法盗链！");
			response.sendRedirect("loginadmin.jsp");
		}
	%>

	<%
		List<Stu> stu_list = (List<Stu>) session.getAttribute("stu_list");
	%>
	<table border="1">
		<tr>
			<th>学号</th>
			<th>姓名</th>
			<th>密码</th>
			<th colspan="2">操作</th>
		</tr>
		<%
			if (stu_list != null) {
				for (Stu s : stu_list) {
		%>
		<tr>
			<td><%=s.getId()%></td>
			<td><%=s.getName()%></td>
			<td><%=s.getPassword()%></td>
			<td><a href="EditServlet?id=<%=s.getId()%>">编辑</a></td>
			<td><a href="DeleteServlet?id=<%=s.getId()%>"
				onclick="return confirm('确认删除?')">删除</a></td>
		</tr>
		<%
			} //end for
				session.removeAttribute("user_list");
			} //end if
		%>
	</table>

	<input type=button onclick="window.location.href='logout.jsp'"
		value="注销">
	<input type=button onclick="window.location.href='scorelist.jsp'"
		value="成绩查询">

</body>
</html>