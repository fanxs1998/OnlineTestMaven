package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.Stu;
import dao.UserDAO;

/**
 * Servlet implementation class RegServlet
 */
@WebServlet("/RegServlet")
public class RegServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RegServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String referer = request.getHeader("referer");
			if (referer == null || !referer.endsWith(".jsp")) {
				request.getSession().setAttribute("err", "禁止非法盗链！");
				response.sendRedirect("register.jsp");
			} else {

				String s = request.getParameter("id");
				String name = request.getParameter("name");
				String password = request.getParameter("password");
				if (!s.equals("") && !name.equals("") && !password.equals("")) {
					int id = Integer.parseInt(request.getParameter("id").trim());//去掉前后的空格
					Stu stu = new Stu();
					stu.setId(id);
					stu.setName(name);
					stu.setPassword(password);
					UserDAO userdao = new UserDAO();
					if (userdao.isIdExists(id)) {
						request.getSession().setAttribute("err", "用户名已存在");
						response.sendRedirect("register.jsp");
					} else {
						boolean flag = userdao.addStu(stu);
						if (flag) {
							request.getSession().setAttribute("id", id);
							response.sendRedirect("test.jsp");
						} else {
							request.getSession().setAttribute("err", "注册失败");
							response.sendRedirect("register.jsp");
						}
					}
				} else {
					request.getSession().setAttribute("err", "请填写完整信息");
					response.sendRedirect("register.jsp");
				}
			}
		} catch (Exception e) {
			request.getSession().setAttribute("err", "请检查学号格式");
			response.sendRedirect("register.jsp");
		} finally {

		}
	}

}
