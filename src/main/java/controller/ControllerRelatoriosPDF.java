package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOControles;
import model.DAODashboard;
import model.DAOLoja;


@WebServlet("/ControllerRelatoriosPDF")
public class ControllerRelatoriosPDF extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public ControllerRelatoriosPDF() {

    }

	public void carregarDadosRelatorios(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		DAOControles daoControles = new DAOControles();
    	ArrayList<String> lista = daoControles.carregarListaTabelas();
    	
    	DAODashboard daoDashBoard = new DAODashboard();
       	
    	String numLoja = request.getParameter("loja");
    	for (String tabela:lista) {
    		request.setAttribute(tabela , daoDashBoard.dadosLojaIndividual(numLoja, tabela));
    	}	
    	DAOLoja daoListaLojas = new DAOLoja();
    	request.setAttribute("listaLojas", daoListaLojas.ListaLojas());
    	request.setAttribute("tabelas", lista);
    	request.setAttribute("percentLojas", request.getParameter("loja"));
    	request.setAttribute("dadosDashBoardSistemas", daoDashBoard.dadosDashBoardSistemas(request.getParameter("loja")));
  		RequestDispatcher rd = request.getRequestDispatcher("templates/gerarRelatorios.jsp");
  		rd.forward(request, response);
  		
	}
}
