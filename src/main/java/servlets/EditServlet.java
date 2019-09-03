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
 * Servlet implementation class EditServlet
 */
@WebServlet("/EditServlet")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditServlet() {
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
			HttpSession session=request.getSession();
			String id=request.getParameter("id");
			Stu s=new UserDAO().getStuById(Integer.parseInt(id));
			if(s!=null) {
				session.setAttribute("stu", s);
				response.sendRedirect("edit.jsp");
			}
		}
    }

}