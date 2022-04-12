package controller;

import java.io.IOException;
import java.util.Base64;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.DAOUser;
import model.JavaBeansUser;


@WebServlet()
public class ControllerLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public ControllerLogin() {
        super();
    }
    

	public void validarUsuario(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		DAOUser daoUser = new DAOUser();
		JavaBeansUser validarUser = new JavaBeansUser();
		
			if (request.getParameter("usuario") != "") {
				validarUser.setNome(request.getParameter("usuario"));
				validarUser.setSenha(request.getParameter("pw"));
				JavaBeansUser usuario = daoUser.validarUser(validarUser);
				if (usuario != null && usuario.getAtivo() == true) {				
					JavaBeansUser dadosValidacaoSenha = daoUser.dadosUserValidacao(request.getParameter("usuario"));					
					byte[] senhaEmBytes = Base64.getDecoder().decode(dadosValidacaoSenha.getSenha());
					String senhaString = new String(senhaEmBytes);					
					if (senhaString.equals("Assai_Fluxo")){
						HttpSession session = request.getSession();
						session.setAttribute("pwTrocar", true);
						request.setAttribute("usuario", request.getParameter("usuario"));
						RequestDispatcher rd = request.getRequestDispatcher("templates/templatesValidacao/redefinirSenha.jsp");
						rd.forward(request, response);
					}else {
						request.setAttribute("usuario", usuario);
						RequestDispatcher rd = request.getRequestDispatcher("templates/templatesValidacao/logar.jsp");
						rd.forward(request, response);
					}
				}else if(usuario != null && usuario.getAtivo() == false){
					response.sendRedirect("./login?erro=UD");
				
				}else {
					response.sendRedirect("./login?erro=PW");
				}
			}
		}
	
	public void trocarSenha(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		DAOUser daoUser = new DAOUser();
		HttpSession session = request.getSession();
		Boolean validar = (Boolean) session.getAttribute("pwTrocar");
		if (validar == true) {
			if (request.getParameter("pwV").equals(request.getParameter("pwC"))) {
				String user = (String) request.getParameter("usuario");
				String pw = (String) request.getParameter("pwV");
				Boolean usuario = daoUser.trocarSenha(user,pw);
				if (usuario == true) {
					response.sendRedirect("./login?erro=SA");
				}else {
					response.sendRedirect("./login?erro=SE");
				}
			}
		}else {
			response.sendRedirect("./login?erro=PT");
		}
	}

}
