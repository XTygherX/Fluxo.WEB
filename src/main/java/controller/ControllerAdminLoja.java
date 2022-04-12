package controller;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAODashboard;
import model.DAOLoja;
import model.JavaBeansLoja;

@WebServlet()
public class ControllerAdminLoja extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ControllerAdminLoja() {
        super();
    }
    
	
    public void novaLoja(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
    	DAOLoja dao = new DAOLoja();
        DAODashboard daoDashboard = new DAODashboard();
        
        JavaBeansLoja novaLoja = new JavaBeansLoja();

    	//CRIANDO OBJETO LOJA JAVABEANS
		if (request.getParameter("numLoja") != null) {
			novaLoja.setNumLoja(request.getParameter("numLoja"));
		}
		if (request.getParameter("nomeLoja") != null) {
			novaLoja.setNomeLoja(request.getParameter("nomeLoja"));
		}
		if (request.getParameter("entradaTi") != null) {
			String dataString = request.getParameter("entradaTi"); 
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
			java.sql.Date data = new java.sql.Date(fmt.parse(dataString).getTime());
			novaLoja.setDataEntradaTi(data);
		}
		if (request.getParameter("inauguracao") != null) {
			String dataString = request.getParameter("inauguracao"); 
			DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
			java.sql.Date data = new java.sql.Date(fmt.parse(dataString).getTime());
			novaLoja.setDataInaguracao(data);
		}
		if (request.getParameter("responsavel") != null) {
			novaLoja.setResponsavel(request.getParameter("responsavel"));
		}else {
			novaLoja.setResponsavel(null);
		}
		if (request.getParameter("statusLoja") == null) {
			novaLoja.setStatus(false);
		}else {
			novaLoja.setStatus(true);
		}
		if (request.getParameter("visivel") == null) {
			novaLoja.setVisualizacao(false);
		}else {
			novaLoja.setVisualizacao(true);
		}
		String historico = "SERVIDOR TESTE";
		novaLoja.setHistorico(historico);
		
		if((dao.inserirNovaLoja(novaLoja)) && (daoDashboard.novaDashBoard(request.getParameter("numLoja"), novaLoja.getDataInaguracao()))){
			response.sendRedirect("./admin?m=AL");
		}else {
			response.sendRedirect("./admin?m=EL");
		}
		
	}
	
    public void updateNumLoja(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {
    	DAOLoja dao = new DAOLoja();

		String numLojaAntigo =  request.getParameter("numAntigoLoja");
		String numLojaNovo = request.getParameter("novoNumLoja");
				
		if(dao.updateNumLoja(numLojaAntigo, numLojaNovo)){
			response.sendRedirect("./admin?m=UL");
		}else {
			response.sendRedirect("./admin?m=EL");
		}
    }
    
    public void updateLoja(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ParseException {

    	DAOLoja dao = new DAOLoja();
        JavaBeansLoja novaLoja = new JavaBeansLoja();

    	//CRIANDO OBJETO LOJA JAVABEANS
			if (request.getParameter("numLoja") != null) {
				novaLoja.setNumLoja(request.getParameter("numLoja"));
			}
			if (request.getParameter("nomeLoja") != null) {
				novaLoja.setNomeLoja(request.getParameter("nomeLoja"));
			}
			if (request.getParameter("entradaTi") != null) {
				String dataString = request.getParameter("entradaTi"); 
				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
				java.sql.Date data = new java.sql.Date(fmt.parse(dataString).getTime());
				novaLoja.setDataEntradaTi(data);
			}
			if (request.getParameter("inauguracao") != null) {
				String dataString = request.getParameter("inauguracao"); 
				DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd"); 
				java.sql.Date data = new java.sql.Date(fmt.parse(dataString).getTime());
				novaLoja.setDataInaguracao(data);
			}
			if (request.getParameter("responsavel") != null) {
				novaLoja.setResponsavel(request.getParameter("responsavel"));
			}else {
				novaLoja.setResponsavel(null);
			}
			if (request.getParameter("statusLoja") == null) {
				novaLoja.setStatus(false);
			}else {
				novaLoja.setStatus(true);
			}
			if (request.getParameter("visivel") == null) {
				novaLoja.setVisualizacao(false);
			}else {
				novaLoja.setVisualizacao(true);
			}
			String historico = "SERVIDOR TESTE";
			novaLoja.setHistorico(historico);
						
			if(dao.updateLoja(novaLoja, request.getParameter("custId"))){
				response.sendRedirect("./admin?m=UL");
			}else {
				response.sendRedirect("./admin?m=EL");
			}
			
	}
}
