package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;

/**
 * Servlet implementation class StudentsServlet
 */
@WebServlet("/StudentsServlet")
public class StudentsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public StudentsServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		try {

			String referer = request.getHeader("referer");
			System.out.print(referer);
			if (referer == null || (!referer.endsWith("loginadmin.jsp") && !referer.endsWith("edit.jsp")
					&& !referer.endsWith("control.jsp"))) {
				System.out.print(referer);
				response.sendRedirect("loginadmin.jsp");
			} else {

				HttpSession session = request.getSession();
				session.setAttribute("stu_list", new UserDAO().getAllStu());
				response.sendRedirect("control.jsp");

			}
		} catch (NumberFormatException e) {

		}

	}

}