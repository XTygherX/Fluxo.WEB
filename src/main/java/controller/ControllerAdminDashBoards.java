package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ControllerAdminDashBoards extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerAdminDashBoards() {
        super();
    }

	protected void createTabelaControles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Enumeration<String> parameterNames = request.getParameterNames();
		while(parameterNames.hasMoreElements()) {
			//System.out.print(parameterNames.nextElement());
		}
		
	}
	
	protected void carregarAdminDashBoard(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        if(request.getParameter("nome") != null) {
            String text = "ASDA";
        	//System.out.println(request.getParameter("nome"));
            response.setContentType("text/plain");  // Set content type of the response so that jQuery knows what it can expect.
            response.setCharacterEncoding("UTF-8"); // You want world domination, huh?
            response.getWriter().write(text);       // Write response body.
			
        }else {
		
			RequestDispatcher rd = request.getRequestDispatcher("templates/adminDashBoards.jsp");
	    	rd.forward(request, response);
        }
	}
}
