<%@page import="model.JavaBeansUser"%>
<%@page import="javax.swing.JTextPane"%>
<%
if (session.getAttribute("nome") == null) {
	response.sendRedirect("./login");
}
%>
