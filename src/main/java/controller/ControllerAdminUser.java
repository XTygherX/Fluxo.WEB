package controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOUser;
import model.JavaBeansUser;

@WebServlet(urlPatterns = { "/TESTE22"})
public class ControllerAdminUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	
	public ControllerAdminUser() {
		super();
	}
	
	
	
	//CRIAR USUARIO ATRAVES DO JAVAVEANS
	protected void NovoUsuario(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		JavaBeansUser novoUsuario = new JavaBeansUser();

		DAOUser dao = new DAOUser(); 
		//inserindo as Variaveis na JavaBeans		
		//NOME
		if (request.getParameter("nome") != null) {
			novoUsuario.setNome(request.getParameter("nome"));
		}
		//visualização
		if (request.getParameter("userVisualizacao") == null) {
			novoUsuario.setVisualizacao(false);
		}else {
			novoUsuario.setVisualizacao(true);
		}		
		//EDIÇÃO
		if (request.getParameter("userEdicao") == null) {
			novoUsuario.setEdicao(false);
		}else {
			novoUsuario.setEdicao(true);
		}
		//ADIÇÃO
		if (request.getParameter("userAdicao") == null) {
			novoUsuario.setAdicao(false);
		}else {
			novoUsuario.setAdicao(true);
		}
		//ADICÃO DE USER
		if (request.getParameter("userAdicaoUser") == null) {
			novoUsuario.setAdicaoUsuario(false);
		}else {
			novoUsuario.setAdicaoUsuario(true);
		}
		//Status do usuario
		if (request.getParameter("userAtivo") == null) {
			novoUsuario.setAtivo(false);
		}else {
			novoUsuario.setAtivo(true);
		}
		//HOSTICO
		String senhaCodificada = Base64.getEncoder().encodeToString("Assai_Fluxo".getBytes());
		novoUsuario.setSenha(senhaCodificada);
		String historico = "SERVIDOR TESTE";
		novoUsuario.setHistorico(historico);
		
		//INVOCA O OBJETO novoUsuario
		
		if(dao.inserirNovoUsuario(novoUsuario)) {
			response.sendRedirect("./admin?m=UU");
		}else {
			response.sendRedirect("./admin?m=EU");
		}
		
	}
	protected void redefinirSenha(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		
		DAOUser dao = new DAOUser();
		if (!request.getParameter("user").equals("")) {
			String user = request.getParameter("user");
			if(dao.redefinirSenha(user)) {
				response.sendRedirect("./admin?m=US");
			}else {
				response.sendRedirect("./admin?m=ES");
			}
		}else {
			response.sendRedirect("./admin?m=erro");
		}
		
		

	}
	
	//UPDATE USUARIO ATRAVES DO JAVAVEANS
	protected void updateUser(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		JavaBeansUser novoUsuario = new JavaBeansUser();

		DAOUser dao = new DAOUser(); 
		//inserindo as Variaveis na JavaBeans		
		//NOME
		if (request.getParameter("nome") != null) {
			novoUsuario.setNome(request.getParameter("nome"));
		}
		//visualização
		if (request.getParameter("userVisualizacao") == null) {
			novoUsuario.setVisualizacao(false);
		}else {
			novoUsuario.setVisualizacao(true);
		}		
		//EDIÇÃO
		if (request.getParameter("userEdicao") == null) {
			novoUsuario.setEdicao(false);
		}else {
			novoUsuario.setEdicao(true);
		}
		//ADIÇÃO
		if (request.getParameter("userAdicao") == null) {
			novoUsuario.setAdicao(false);
		}else {
			novoUsuario.setAdicao(true);
		}
		//ADICÃO DE USER
		if (request.getParameter("userAdicaoUser") == null) {
			novoUsuario.setAdicaoUsuario(false);
		}else {
			novoUsuario.setAdicaoUsuario(true);
		}
		//Status do usuario
		if (request.getParameter("userAtivo") == null) {
			novoUsuario.setAtivo(false);
		}else {
			novoUsuario.setAtivo(true);
		}
		//HOSTICO
		String historico = "SERVIDOR TESTE";
		novoUsuario.setHistorico(historico);
		
		
		if(dao.updateUser(novoUsuario)) {
			response.sendRedirect("./admin?m=AU");
		}else {
			response.sendRedirect("./admin?m=EU");
		}
	}
	
}
