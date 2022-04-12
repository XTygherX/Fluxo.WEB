<%@page import="model.JavaBeansUser"%>
<%
session.invalidate();
response.sendRedirect("./login");
%>