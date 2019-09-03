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
		String referer = request.getHeader("referer");
		if (referer == null || (!referer.endsWith("login.jsp") && !referer.endsWith("register.jsp"))) {
			request.getSession().setAttribute("err", "禁止非法盗链！");
			response.sendRedirect("login.jsp");
		}
	%>

	<%
		String u = session.getAttribute("id").toString();
		if (u != null && !u.isEmpty()) {
	%>
	StudentsNum:
	<%=session.getAttribute("id")%>
	<%
		if (u.isEmpty()) {
				out.print("cld");
				response.setHeader("refresh", "3;url='index.jsp'");
			} else {
	%>

	<h3>Test</h3>

	<form action="AddscoreServlet"
		onsubmit="return confirm('此测试只能做一次,确认提交?')">
		第1题：南的反义词(10分) <input type="text" name="q1" /> <br>
		<br> 第2题：毛泽东是哪国人(20分) <br> <input type="radio" value="中国"
			name="q2"> 中国 <input type="radio" value="美国" name="q2">
		美国 <input type="radio" value="俄罗斯" name="q2"> 俄罗斯 <br>
		<br> 第3题：鲁迅原名(30分) <br> <input type="radio" value="鲁智深"
			name="q3"> 鲁智深 <input type="radio" value="周树人" name="q3">
		周树人 <input type="radio" value="阿玮" name="q3"> 阿玮 <br>
		<br> 第4题：四大名著有(40分) <br> <input type="checkbox" value="红楼梦"
			name="q4"> 红楼梦 <input type="checkbox" value="水浒传" name="q4">
		水浒传 <input type="checkbox" value="三国演义" name="q4"> 三国演义 <input
			type="checkbox" value="西游记" name="q4"> 西游记 <br>
		<br>
		<button type="submit">提交</button>
	</form>
	<%
		}
		} else {
			out.print("你还没有登录，3秒后自动跳往登录页面");
			response.setHeader("refresh", "3;url=login.jsp");
		}
	%>

</body>
</html>