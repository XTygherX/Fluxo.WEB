<%@page import="java.util.ArrayList"%>
<%@page import="model.JavaBeansUser"%>
<%@page import="controller.ControllerLogin"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%
JavaBeansUser user = (JavaBeansUser) request.getAttribute("usuario");
if (user != null){
	 session.setAttribute("nome", user.getNome());
	 session.setAttribute("adicao", user.getAdicao());
	 session.setAttribute("edicao", user.getEdicao());
	 session.setAttribute("adicaoUser", user.getAdicaoUsuario());
	 session.setAttribute("visualizacao", user.getVisualizacao());
	 session.setAttribute("ativo", user.getAtivo());
	 response.sendRedirect("./main");
	 
}else{
	response.sendRedirect("./login?erro=erro");
}

%>