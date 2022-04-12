package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAODashboard;
import model.DAOLoja;
import model.JavaBeansDashBoard;
import model.JavaBeansLoja;

/**
 * Servlet implementation class ControllerMain
 */
@WebServlet()
public class ControllerMain extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ControllerMain() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    protected void carregarMain(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {   
  		DAOLoja daoLoja = new DAOLoja();
  		DAODashboard daoDashboard = new DAODashboard();

  		ArrayList<JavaBeansLoja> selectLojas = daoLoja.ListaLojas();
  		request.setAttribute("selectLojas", selectLojas);
  		

  		ArrayList<JavaBeansDashBoard> dadosDashBoard = daoDashboard.dadosIndex(selectLojas); 
  		request.setAttribute("dadosDashBoard", dadosDashBoard);
  		

  		ArrayList<JavaBeansDashBoard> percentLojas = daoDashboard.perctGraficoIndex(selectLojas);
  		request.setAttribute("percentLojas", percentLojas);
  		
  	
  		RequestDispatcher rd = request.getRequestDispatcher("templates/index.jsp");
  		rd.forward(request, response);
	}
}
