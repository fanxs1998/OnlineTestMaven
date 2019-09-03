package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Stu;
import dao.UserDAO;

/**
 * Servlet implementation class SaveStuServlet
 */
@WebServlet("/SaveStuServlet")
public class SaveStuServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SaveStuServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String referer = request.getHeader("referer");
		if (referer == null || !referer.endsWith("edit.jsp")) {
			request.getSession().setAttribute("err", "禁止非法盗链！");
			response.sendRedirect("loginadmin.jsp");
		} else {
			HttpSession session = request.getSession();
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String password = request.getParameter("password");
			Stu stu = new Stu(id, name, password);
			if (password != null && !password.equals("") && name != null && !name.equals("")) {
				boolean flag = new UserDAO().UpdateStu(stu);
				if (flag) {
					response.sendRedirect("StudentsServlet");
				} else {
					session.setAttribute("err", "保存失败，请重新修改");
					session.setAttribute("stu", stu);
					response.sendRedirect("edit.jsp");
				}
			} else {
				session.setAttribute("err", "保存失败，请重新修改");
				session.setAttribute("stu", stu);
				response.sendRedirect("edit.jsp");
			}
		}
	}

}
