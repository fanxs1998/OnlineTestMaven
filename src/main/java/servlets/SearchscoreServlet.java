package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ScoreDAO;

/**
 * Servlet implementation class SearchscoreServlet
 */
@WebServlet("/SearchscoreServlet")
public class SearchscoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchscoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
		    	String referer = request.getHeader("referer");
		    	if(referer==null || !referer.endsWith("scorelist.jsp")){
		        	request.getSession().setAttribute("err", "禁止非法盗链！");
		        	response.sendRedirect("scorelist.jsp");
		        }else {
				HttpSession session=request.getSession();
				String name=request.getParameter("name");
				String score1=request.getParameter("score1");
				String score2=request.getParameter("score2");
				String flag=request.getParameter("flag");
				if(name==null||name.equals("")) {
					name="";
				}
				if(score1==null||score1.equals("")) {
					score1="0";
				}
				if(score2==null||score2.equals("")) {
					score2="100";
				}
				if(flag==null||flag.equals("")) {
					flag="1";
				}
				session.setAttribute("score_list", new ScoreDAO().getScoreByCondition(name,Double.parseDouble(score1),Double.parseDouble(score2),Integer.parseInt(flag)));
				response.sendRedirect("scorelist.jsp");
		    }
    	}
}