package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ScoreDAO;
import dao.UserDAO;

/**
 * Servlet implementation class DeleteServlet
 */
@WebServlet("/DeleteServlet")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DeleteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	String referer = request.getHeader("referer");
        if(referer==null || !referer.endsWith("control.jsp")){
        	request.getSession().setAttribute("err", "禁止非法盗链！");
        	response.sendRedirect("loginadmin.jsp");
		}else {
			String id=request.getParameter("id");
			boolean flag=new UserDAO().delStuById(Integer.parseInt(id));
			if(flag) {
				response.sendRedirect("StudentsServlet");
			}
		}
    }
}
