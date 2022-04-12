package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOControles;
import model.JavaBeansTabelaControles;

public class ControllerTabelaControles extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControllerTabelaControles() {
		super();
	}

	protected void updateTabelaControles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Boolean validacaoErro = false;
		String tabela = request.getParameter("tabela");
		Enumeration<String> parameterNames = request.getParameterNames();
		while (parameterNames.hasMoreElements()) {
			DAOControles daoControles = new DAOControles();
			JavaBeansTabelaControles updateTabela = new JavaBeansTabelaControles();

			String parametros = parameterNames.nextElement();

			String[] linha = parametros.split("_");

			if (parametros.equals("tabela")) {
				break;
			}

			String descNova = linha[0] + "_descNova";
			if (!request.getParameter(descNova).equals("")) {

				String descAtual = linha[0] + "_descAtual";
				if (!request.getParameter(descAtual).equals("")) {
					updateTabela.setDescAtual(request.getParameter(descAtual));
				}

				parametros = parameterNames.nextElement();

				descNova = linha[0] + "_descNova";

				if (!request.getParameter(descNova).equals("")) {
					updateTabela.setDescNova(request.getParameter(descNova));
				} else {
					updateTabela.setDescNova(updateTabela.getDescAtual());
				}

				parametros = parameterNames.nextElement();

				String dMenos = linha[0] + "_dMenos";
				if (!request.getParameter(dMenos).equals("")) {
					updateTabela.setdMenos(Integer.parseInt(request.getParameter(dMenos)));
				}

				updateTabela.setTabela(tabela);

				if (daoControles.updateTabelaControles(updateTabela, tabela)) {
					validacaoErro = true;
				} else {
					validacaoErro = false;
				}

			} else {
				parametros = parameterNames.nextElement();
				parametros = parameterNames.nextElement();
			}

		}
		if (validacaoErro == true) {
			response.sendRedirect("./adminControles?m=UC");
		} else
			response.sendRedirect("./adminControles?m=EC");
	}
	
	public void delTarefa(HttpServletRequest request, HttpServletResponse response) {
		JavaBeansTabelaControles JBdelTarefa = new JavaBeansTabelaControles();
		DAOControles DAOdelTarefa = new DAOControles();
		JBdelTarefa.setDescAtual(request.getParameter("tarefa"));
		JBdelTarefa.setTabela(request.getParameter("controle"));
		if(DAOdelTarefa.delTarefa(JBdelTarefa)) {
			String text = "true";
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write(text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void addTarefa(HttpServletRequest request, HttpServletResponse response) {
		JavaBeansTabelaControles JBaddTarefa = new JavaBeansTabelaControles();
		DAOControles DAOaddTarefa = new DAOControles();
		String controle = request.getParameter("controle");
		String tarefa = request.getParameter("tarefa");
		JBaddTarefa.setTabela(controle);
		JBaddTarefa.setDescAtual(tarefa);
		JBaddTarefa.setdMenos(120);
		if (DAOaddTarefa.addTarefa(JBaddTarefa)) {
			String text = "true";
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");

			try {
				response.getWriter().write(text);
			} catch (Exception e) {

				e.printStackTrace();
			}
		}
	
	}
	
	public void renControle(HttpServletRequest request, HttpServletResponse response) {
	JavaBeansTabelaControles JBrenControle = new JavaBeansTabelaControles();
	DAOControles DAOrenControle = new DAOControles();
	String renControle = request.getParameter("controle").toString().toUpperCase().replace(" ", "_");
	String NovorenControle = request.getParameter("novocontrole").toString().toUpperCase().replace(" ", "_");
	JBrenControle.setDescNova(NovorenControle);
	JBrenControle.setDescAtual(renControle);
		if (DAOrenControle.renControle(JBrenControle)) {
			String text = "true";
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write(text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void delControle(HttpServletRequest request, HttpServletResponse response) {
		JavaBeansTabelaControles JBdelControle = new JavaBeansTabelaControles();
		DAOControles DAOdelControle = new DAOControles();
		String deletarControle = request.getParameter("controle").toString().toUpperCase().replace(" ", "_");
		JBdelControle.setTabela(deletarControle);
		if (DAOdelControle.delControle(JBdelControle)) {
			String text = "true";
			response.setContentType("text/plain");
			response.setCharacterEncoding("UTF-8");
			try {
				response.getWriter().write(text);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	public void addControles(HttpServletRequest request, HttpServletResponse response) {
		JavaBeansTabelaControles JBnovoControle = new JavaBeansTabelaControles();
		DAOControles DAOnovoControle = new DAOControles();
		String novoControle = request.getParameter("controle").toString().toUpperCase().replace(" ", "_");
		ArrayList<String> lista = DAOnovoControle.carregarListaTabelas();
		JBnovoControle.setTabela(novoControle);
		Boolean validacao = false;
		for(String controle: lista) {
			if (novoControle.equals(controle)) {
				validacao = false;
				break;
			}else {
				validacao = true;	
			}
		}
		if (validacao == true) {
			if (DAOnovoControle.novoControle(JBnovoControle)) {
				String text = "true";
				response.setContentType("text/plain");
				response.setCharacterEncoding("UTF-8");
				try {
					response.getWriter().write(text);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}else {
			System.out.println("ERRO");
		}

	}

	public void carregarTabelasControles(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		DAOControles daoControles = new DAOControles();
		ArrayList<String> lista = daoControles.carregarListaTabelas();

		DAOControles DAOcarregarTabelaControles = new DAOControles();

		for (String tabela : lista) {
			request.setAttribute(tabela, DAOcarregarTabelaControles.carregarControles(tabela));
		}
		request.setAttribute("tabelas", lista);
		RequestDispatcher rd = request.getRequestDispatcher("templates/adminControles.jsp");
		rd.forward(request, response);

	}

}
