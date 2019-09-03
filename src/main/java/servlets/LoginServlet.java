package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			String referer = request.getHeader("referer");
			if (referer == null || !referer.endsWith("login.jsp")) {
				request.getSession().setAttribute("err", "禁止非法盗链！");
				response.sendRedirect("login.jsp");
			} else {

				String s = request.getParameter("id");
				int id = Integer.parseInt(s);
				String password = request.getParameter("password");
				UserDAO userdao = new UserDAO();
				boolean flag = userdao.findStu(id, password);
				if (flag) {

					request.getSession().setAttribute("id", id);
					response.sendRedirect("test.jsp");
				} else {
					request.getSession().setAttribute("err", "学号或密码错误！");
					response.sendRedirect("login.jsp");
				}

			}
		} catch (NumberFormatException e) {
			request.getSession().setAttribute("err", "学号或密码错误！");
			response.sendRedirect("login.jsp");
		}

	}

}
