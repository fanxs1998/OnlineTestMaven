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
		if (referer == null || (!referer.endsWith("control.jsp") && !referer.endsWith("scorelist.jsp"))) {
			request.getSession().setAttribute("err", "禁止非法盗链！");
			response.sendRedirect("loginadmin.jsp");
		}
	%>
	<form action="SearchscoreServlet">
		按姓名查询<input type="text" name="name" /><br /> 按成绩区间查询<input
			type="text" onkeyup="value=value.replace(/[^\d]/g,'')" size="5"
			name="score1" />--<input type="text"
			onkeyup="value=value.replace(/[^\d]/g,'')" size="5" name="score2" />
		<button type="submit">查询</button>

		<%
			List<Score> score_list = (List<Score>) session.getAttribute("score_list");
		%>
		<table border="1">
			<tr>
				<th>学号</th>
				<th>姓名</th>
				<th>成绩 <a href="SearchscoreServlet?flag=0">升序 </a> <a
					href="SearchscoreServlet">降序</a></th>
			</tr>
			<%
				if (score_list != null) {
					for (Score s : score_list) {
			%>
			<tr>
				<td><%=s.getStu_id()%></td>
				<td><%=s.getStu_name()%></td>
				<td><%=s.getScore()%></td>
			</tr>
			<%
				} //end for
					session.removeAttribute("user_list");
				} //end if
			%>
		</table>
</body>
</html>