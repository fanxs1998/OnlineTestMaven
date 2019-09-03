package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import beans.Score;
import dao.ScoreDAO;

/**
 * Servlet implementation class AddscoreServlet
 */
@WebServlet("/AddscoreServlet")
public class AddscoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddscoreServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
		    	
    	String referer = request.getHeader("referer");
        if(referer==null || !referer.endsWith("test.jsp")){
        	    request.getSession().setAttribute("err", "禁止非法盗链！");
		    	response.sendRedirect("login.jsp");
				
		    }else {
		    	double score=0;
		    	String q1=request.getParameter("q1");
		    	String q2=request.getParameter("q2");
		    	String q3=request.getParameter("q3");
		    	String[] q4=request.getParameterValues("q4");
		    	if(q1!=null&&q1.equals("北")){
		    		score+=10;
		    	}
		    	if(q2!=null&&q2.equals("中国")){
		    		score+=20;
		    	}
		    	if(q3!=null&&q3.equals("周树人")){
		    		score+=30;
		    	}
		    	if(q4!=null&&q4.length==4&&q4[0].equals("红楼梦")&&q4[1].equals("水浒传")&&q4[2].equals("三国演义")&&q4[3].equals("西游记")){
		    		score+=40;
		    	}
				HttpSession session=request.getSession();
				int id=Integer.parseInt(session.getAttribute("id").toString());
				Score sc=new Score(id,"",score);
				ScoreDAO s=new ScoreDAO();
				boolean flag=s.isIdExists(id);
				if(!flag){
					s.addScore(sc);	
					response.sendRedirect("logout.jsp");
				}else {
					request.getSession().setAttribute("err", "你已经做过练习!");
					response.sendRedirect("login.jsp");
				}
		    }
        }

}
