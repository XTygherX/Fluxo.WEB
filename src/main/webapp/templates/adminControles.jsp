<%@page import="java.text.DecimalFormat"%>
<%@page import="model.JavaBeansTabelaControles"%>
<%@page import="model.JavaBeansDashBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.JavaBeansLoja"%>
<%@ include file="templatesInclude/validarUser.jsp"%>
<%
	ArrayList<JavaBeansLoja> listaLojas = (ArrayList<JavaBeansLoja>) request.getAttribute("listaLojas");
	ArrayList<String> tabelas = (ArrayList<String>) request.getAttribute("tabelas");
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="templatesInclude/scriptEimportsTop.jsp"%>
<link rel="stylesheet" href="css/adminControles.css">

<script type="text/javascript" src="js/gerenciarControles.js"></script>
<title>Fluxo TI - Gerenciar Controles</title>

</head>
<body>
	<!-- Menu lateral -->
	<div id="menulateral">
		<%@ include file="templatesInclude/menuLateral.jsp"%>
	</div>

	<div>
		<div class="div_Table" id="myGroup">
			<div class="card" id="Controles">
				<h5>Controles</h5>
				<div id="btnControles">
					<a type="button" onclick='addControle()'> <i
						class='bx bx-list-plus'></i>
					</a>

				</div>
			</div>

		</div>
	</div>
	<div class="card tabela">
		<h5>Gerenciar Controles</h5>
		<div class="text-dark bg-light" data-parent="#myGroup"></div>
	</div>


	<%@ include file="templatesInclude/scriptsFimBody.jsp"%>
	<script type="text/javascript">
	<%for (String txt: tabelas){%>
		criandoTabelas("<%=txt%>")
	<%}%>
	
	<%
	for (String txt:tabelas){
		ArrayList<JavaBeansTabelaControles> carragarTabela = (ArrayList<JavaBeansTabelaControles>) request.getAttribute(txt);		
		for(int i = 0; i < carragarTabela.size();i++){%>
			loopInserirValoresSelect("<%=carragarTabela.get(i).getDescAtual()%>","<%=carragarTabela.get(i).getdMenos()%>","<%=txt%>")
		<%}%>
	<%}
	%>
	
	

	</script>
</body>
</html>