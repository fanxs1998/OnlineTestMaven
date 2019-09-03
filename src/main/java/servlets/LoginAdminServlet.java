package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;

/**
 * Servlet implementation class LoginAdminServlet
 */
@WebServlet("/LoginAdminServlet")
public class LoginAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginAdminServlet() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    			try { 
    				String referer = request.getHeader("referer");
    				if(referer==null || !referer.endsWith("loginadmin.jsp")){
    		        	request.getSession().setAttribute("err", "禁止非法盗链！");
    		        	response.sendRedirect("loginadmin.jsp");
    				}else {
    				
    				String s=request.getParameter("id");
    				int id=Integer.parseInt(s);
    	    		String password=request.getParameter("password");
    	    		UserDAO userdao=new UserDAO();
    	    		boolean flag=userdao.findAdmin(id, password);
    	    		if( flag ) {  
    		    		
    		    		response.sendRedirect("StudentsServlet");
    	    		}
    	    		else{
    		    		request.getSession().setAttribute("err", "管理员登录失败");
    		    		response.sendRedirect("loginadmin.jsp");
    	    		} 
        			
    				}
    				} catch (NumberFormatException e) { 
    					request.getSession().setAttribute("err", "管理员登陆失败");
    		    		response.sendRedirect("loginadmin.jsp");
    				} 
	    		
    			
    		}  
    			

}
