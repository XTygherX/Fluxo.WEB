package controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DAOControles;
import model.DAOLoja;
import model.DAOUser;
import model.JavaBeansLoja;
import model.JavaBeansUser;

/**
 * Servlet implementation class ControllerAdmin
 */
@WebServlet(urlPatterns = { "/testes","/ControllerAdmin", "/main", "/criarUsuario", "/updateUser", "/admin", "/loja", "/novaLoja", "/updateLoja", "/updateControles", "/updatecard", "/updateNumLoja", "/menuLateral", "/login", "/validarUsuario", "/sair", "/relatorio", "/adminControles", "/updateTabelaControles", "/redefinirSenha", "/senhaPadrao", "/adminDashBoards" })
public class ControllerCarregarDados extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ControllerCarregarDados() {
		super();
		// TODO Auto-generated constructor stub
	}

	// Recebe request e direciona para function
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ControllerAdminUser controllerUser = new ControllerAdminUser();
		ControllerAdminLoja controllerAdminLoja = new ControllerAdminLoja();
		ControllerLoja controllerLoja = new ControllerLoja();
		ControllerMain controllerMain = new ControllerMain();
		ControllerLogin controllerLogin = new ControllerLogin();
		ControllerRelatoriosPDF controllerPDF = new ControllerRelatoriosPDF();
		ControllerTabelaControles controllerTabelaControles = new ControllerTabelaControles();
		ControllerAdminDashBoards controllerAdminDashBoards = new ControllerAdminDashBoards();
		DAOLoja daoLoja = new DAOLoja();
		ArrayList<JavaBeansLoja> selectLojas = daoLoja.ListaLojas();

		// DIRECIONADO PARA OS METODOS
		String action = request.getServletPath();
		if (request.getSession().getAttribute("ativo") != null) {
			if (action.equals("/updateLoja")) {
				if ((Boolean) request.getSession().getAttribute("adicao") == true) {
					try {
						controllerAdminLoja.updateLoja(request, response);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					response.sendRedirect("./admin?erro=permisaoUser");
				}

			} else if (action.equals("/novaLoja")) {
				if ((Boolean) request.getSession().getAttribute("adicao") == true) {
					try {
						controllerAdminLoja.novaLoja(request, response);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					response.sendRedirect("./admin?erro=permisaoUser");
				}

			} else if (action.equals("/main")) {
				controllerMain.carregarMain(request, response);

			} else if (action.equals("/criarUsuario")) {
				if ((Boolean) request.getSession().getAttribute("adicaoUser") == true) {
					controllerUser.NovoUsuario(request, response);
				} else {
					response.sendRedirect("./admin?erro=permisaoUser");
				}

			} else if (action.equals("/admin")) {
				if ((Boolean) request.getSession().getAttribute("adicao") == true || (Boolean) request.getSession().getAttribute("adicaoUser") == true) {
					carregarAdmin(request, response, "");
				} else {
					response.sendRedirect("./main?erro=permisaoAdmin");
				}

			} else if (action.equals("/updateUser")) {
				if ((Boolean) request.getSession().getAttribute("adicaoUser") == true) {
					controllerUser.updateUser(request, response);
				} else {
					response.sendRedirect("./admin?erro=permisaoUser");
				}

			} else if (action.equals("/loja")) {
				if ((Boolean) request.getSession().getAttribute("visualizacao")) {
					Boolean validacao = true;
					for (int i = 0; i < selectLojas.size(); i++) {
						if (request.getParameter("loja") == null) {
							validacao = true;
							break;
						} else if (request.getParameter("loja").equals(selectLojas.get(i).getNumLoja())) {
							controllerLoja.carregarLoja(request, response, "");
							validacao = false;
							break;
						} else {
							validacao = true;
						}
					}
					if (validacao == true) {
						response.sendRedirect("./loja?loja=" + selectLojas.get(0).getNumLoja());
					}

				} else {
					response.sendRedirect("./main?erro=permisaoLoja");
				}

			} else if (action.equals("/updateControles")) {
				if ((Boolean) request.getSession().getAttribute("edicao") == true) {
					try {
						controllerLoja.updateControles(request, response);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					response.sendRedirect("./loja?num_loja=" + request.getParameter("num_loja") + "&erro=permisaoUser");
				}

			} else if (action.equals("/updatecard")) {
				if ((Boolean) request.getSession().getAttribute("edicao") == true) {
					controllerLoja.updateCard(request, response);
				} else {
					response.sendRedirect("./loja?loja=" + request.getParameter("num_loja") + "&erro=permisaoUser");
				}

			} else if (action.equals("/updateNumLoja")) {
				if ((Boolean) request.getSession().getAttribute("adicao") == true) {
					try {
						controllerAdminLoja.updateNumLoja(request, response);
					} catch (ParseException e) {
						e.printStackTrace();
					}
				} else {
					response.sendRedirect("./admin?erro=permisaoUser");
				}

			} else if (action.equals("/login")) {
				request.getRequestDispatcher("templates/templatesValidacao/login.jsp").forward(request, response);

			} else if (action.equals("/validarUsuario")) {
				controllerLogin.validarUsuario(request, response);

			} else if (action.equals("/sair")) {
				request.getRequestDispatcher("templates/templatesValidacao/logout.jsp").forward(request, response);

			} else if (action.equals("/relatorio")) {
				Boolean validacao = true;
				for (int i = 0; i < selectLojas.size(); i++) {
					if (request.getParameter("loja") == null) {
						validacao = true;
						break;
					} else if (request.getParameter("loja").equals(selectLojas.get(i).getNumLoja())) {
						controllerPDF.carregarDadosRelatorios(request, response);
						validacao = false;
						break;
					} else {
						validacao = true;
					}
				}
				if (validacao == true) {
					response.sendRedirect("./relatorio?loja=" + selectLojas.get(0).getNumLoja());
				}
			} else if (action.equals("/adminControles")) {

				if ((Boolean) request.getSession().getAttribute("adicao") == true || (Boolean) request.getSession().getAttribute("adicaoUser") == true) {
					if (request.getParameter("fun") == null) {
						controllerTabelaControles.carregarTabelasControles(request, response);
					} else if (request.getParameter("fun").equals("addControle")) {
						controllerTabelaControles.addControles(request, response);
					}else if (request.getParameter("fun").equals("delControle")) {
						controllerTabelaControles.delControle(request, response);
					}else if (request.getParameter("fun").equals("renControle")) {
						controllerTabelaControles.renControle(request, response);
					}else if (request.getParameter("fun").equals("addTarefa")) {
						controllerTabelaControles.addTarefa(request, response);
					}else if (request.getParameter("fun").equals("delTarefa")) {
						controllerTabelaControles.delTarefa(request, response);
					}else {
						System.out.println("ELSE FINAL");
					}

				} else {
					response.sendRedirect("./main?erro=permisaoAdmin");
				}
			} else if (action.equals("/updateTabelaControles")) {
				if ((Boolean) request.getSession().getAttribute("adicao") == true || (Boolean) request.getSession().getAttribute("adicaoUser") == true) {
					controllerTabelaControles.updateTabelaControles(request, response);
					// DAOControles daoControles = new DAOControles();
					// daoControles.criarControles();
				} else {
					response.sendRedirect("./main?erro=permisaoAdmin");
				}
			} else if (action.equals("/senhaPadrao")) {
				if ((Boolean) request.getSession().getAttribute("adicaoUser") == true) {
					controllerUser.redefinirSenha(request, response);
				} else {
					response.sendRedirect("./admin?erro=permisaoUser");
				}

			} else if (action.equals("/adminDashBoards")) {

				if ((Boolean) request.getSession().getAttribute("adicao") == true || (Boolean) request.getSession().getAttribute("adicaoUser") == true) {
					controllerAdminDashBoards.carregarAdminDashBoard(request, response);
				} else {
					response.sendRedirect("./main?erro=permisaoAdmin");
				}
			}else if (action.equals("/testes")) {
					RequestDispatcher rd = request.getRequestDispatcher("templates/TESTEAJAX.jsp");
					rd.forward(request, response);

			}

		} else if (action.equals("/validarUsuario")) {
			controllerLogin.validarUsuario(request, response);

		} else if (action.equals("/redefinirSenha")) {
			controllerLogin.trocarSenha(request, response);

		} else {
			request.getRequestDispatcher("templates/templatesValidacao/login.jsp").forward(request, response);

		}

	}

	// Criando Objeto para receber os dados do Java Beans
	protected void carregarAdmin(HttpServletRequest request, HttpServletResponse response, String txt) throws ServletException, IOException {
		DAOUser daoUser = new DAOUser();
		DAOLoja daoLoja = new DAOLoja();
		// Carregando Lista de usuarios
		ArrayList<JavaBeansUser> lista = daoUser.listarUsuarios();
		ArrayList<JavaBeansLoja> listaLoja = daoLoja.ListaLojas();
		// Encaminhando a lista para admin.jsp
		request.setAttribute("selectLojas", listaLoja);
		request.setAttribute("selectUsuario", lista);
		RequestDispatcher rd = request.getRequestDispatcher("templates/admin.jsp");
		rd.forward(request, response);
	}

}
