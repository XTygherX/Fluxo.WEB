package controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOControles;
import model.DAODashboard;
import model.DAOLoja;
import model.JavaBeansDashBoard;

@WebServlet()
public class ControllerLoja extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ControllerLoja() {
        super();
        // TODO Auto-generated constructor stub
    }
    public void carregarLoja(HttpServletRequest request, HttpServletResponse response, String txt)throws ServletException, IOException {
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
  		RequestDispatcher rd = request.getRequestDispatcher("templates/loja.jsp");
  		rd.forward(request, response);
  		
	}
    
    protected void updateCard(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {	
    	String numLoja = request.getParameter("num_loja");
    	String tabela = request.getParameter("tabela");

    	Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			DAODashboard daoDashBoard = new DAODashboard();
			JavaBeansDashBoard updateCard = new JavaBeansDashBoard();
			String parametros = parameterNames.nextElement();
			String[] linha = parametros.split("_");
			updateCard.setTxt(linha[1]);
			updateCard.setNumero(Integer.valueOf(request.getParameter(parametros)));
			if (parametros.equals("num_loja")) {
				break;
			}
			daoDashBoard.updateCard(numLoja, tabela, updateCard);
		}
		if ( tabela.equals("link")) {
			response.sendRedirect("./loja?loja="+numLoja+"&status=LA");
		} else if ( tabela.equals("sistemas")) {
			response.sendRedirect("./loja?loja="+numLoja+"&status=SA");
		}
	}
    
    protected void updateControles(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException, ParseException {
    	String numLoja = request.getParameter("num_loja");
    	String tabela = request.getParameter("tabela");
    	Enumeration<String> parameterNames = request.getParameterNames();
    	Boolean validacaoErro = true;
    	while (parameterNames.hasMoreElements()) {
        	JavaBeansDashBoard updateDashBoard = new JavaBeansDashBoard();
    		DAODashboard daoDashboard = new DAODashboard();    		
			String parametros = parameterNames.nextElement();
			
			String[] linha = parametros.split("_");
			if(parametros.equals("num_loja")) {
				break;
			}
			//DESCRICAO PARA O UPDATE
			updateDashBoard.setDesc(linha[0]);	
			
			//DATA INICIO
			String dataInicio = linha[0]+"_dataInicio";
			if (request.getParameter(dataInicio) != "") {
				updateDashBoard.setDataInicio(formatDate(request.getParameter(dataInicio)));
			}else { 
				updateDashBoard.setDataInicio(null);
				
			}
			parameterNames.nextElement();
			
			//DATA TERMINO
			String dataTermino = linha[0]+"_dataTermino";
			if (request.getParameter(dataTermino) != "") {
				updateDashBoard.setDataTermino(formatDate(request.getParameter(dataTermino)));
			}else { 
				updateDashBoard.setDataTermino(null);
			}
			parameterNames.nextElement();
			
			
			//DENIFININDO STATUS
			String status = linha[0]+"_status";
			if (request.getParameter(status) == null) {
				updateDashBoard.setStatus(false);
			}else {
				updateDashBoard.setStatus(true);
				parameterNames.nextElement();
			}
			
			String observacoes = linha[0]+"_observacoes";
			if (request.getParameter(observacoes) != "") {
				updateDashBoard.setTxt(request.getParameter(observacoes));
			}else {
				updateDashBoard.setTxt("");
				
			}
	    	if (daoDashboard.updateDashBoard(numLoja, tabela, updateDashBoard)) {
	    		validacaoErro = true;
	    	}else {
	    		validacaoErro = false;
	    	}
    	}
    	if (validacaoErro == true) {
    		response.sendRedirect("./loja?loja="+numLoja+"&status=CA");
    	}else {
    		response.sendRedirect("./loja?loja="+numLoja+"&status=ERRO");
    	}
    	
	}
    public Date formatDate( String parametro) throws ParseException{
    	String dataString = parametro; 
		DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
		Date data = new java.sql.Date(fmt.parse(dataString).getTime());
		return data;
	}
}
