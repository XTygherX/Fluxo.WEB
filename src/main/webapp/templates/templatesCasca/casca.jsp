<%@page import="java.text.DecimalFormat"%>
<%@page import="model.JavaBeansDashBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.JavaBeansLoja"%>
<%@ include file="../templatesInclude/validarUser.jsp"%>
<%
	 ArrayList<JavaBeansLoja> listaLojas = (ArrayList<JavaBeansLoja>) request.getAttribute("selectLojas");

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="../templatesInclude/scriptEimportsTop.jsp"%>
<link rel="stylesheet" href="css/dashboardIndex.css">


<title>Fluxo TI - Expansão TI</title>

</head>
<body>
	<!-- Menu lateral -->
	<div id="menulateral">
		<%@ include file="../templatesInclude/menuLateral.jsp"%>
	</div>



	<%@ include file="../templatesInclude/scriptsFimBody.jsp"%>
	<script type="text/javascript">	
	</script>
</body>
</html>