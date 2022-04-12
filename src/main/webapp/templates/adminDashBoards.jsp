<%@page import="java.text.DecimalFormat"%>
<%@page import="model.JavaBeansDashBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.JavaBeansLoja"%>
<%@ include file="templatesInclude/validarUser.jsp"%>
<%
	 ArrayList<JavaBeansLoja> listaLojas = (ArrayList<JavaBeansLoja>) request.getAttribute("selectLojas");

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="templatesInclude/scriptEimportsTop.jsp"%>
<link rel="stylesheet" href="css/adminDashBoards.css">
<script type="text/javascript" src="js/adminDashBoards.js"></script>
<title>Fluxo TI - Gerenciar DashBoards</title>



</head>
<body>
	<!-- Menu lateral -->
	<div id="menulateral">
		<%@ include file="templatesInclude/menuLateral.jsp"%>
	</div>


	<div class="card div_Table" id="Controles">
		<h5 class="card-header text-white bg-secondary">Controles</h5>
		<table class="card table table-striped table-hover tabelaControles"
			id="tabelaControles">
			<thead>
				<tr>
					<th scope="col">Títulos</th>
				</tr>
			</thead>
			<tbody onclick="selecionarLinha(event)">
				<tr class="linhaControles">

				</tr>
			</tbody>
		</table>
		<div id="btnControles" class="card">
			<a type="button" onclick='addControle()'> <i
				class='bx bx-list-plus'></i>
			</a> <a type="button" id="rename" onclick="renameControne(event)"> <i
				class="bx bx-rename"></i>
			</a> <a type="button" id="del" onclick="delControle(event)"> <i
				class="bx bxs-trash"></i>
			</a>
		</div>
	</div>

	<div class='card ' id="tabela">
		<h5 class="card-header text-white bg-secondary">Gerenciar
			DashBoards</h5>
		<div class="row">
			<div class="card text-dark bg-light" id="listaNaoVinculados">
				<h5 class="card-header text-white bg-secondary">Não vinculados</h5>
				<input class="form-control mr-sm-2" id="VALOR1" type="search"
					placeholder="Pesquisar" aria-label="Pesquisar">
			</div>

			<div class="card text-dark bg-light" id="listaVinculados">
				<h5 class="card-header text-white bg-secondary">Vinculados</h5>
				<input class="form-control mr-sm-2" id="VALOR" type="search"
					placeholder="Pesquisar" aria-label="Pesquisar">
			</div>
		</div>
	</div>


	<%@ include file="templatesInclude/scriptsFimBody.jsp"%>
	<script type="text/javascript">
		

	</script>
</body>
</html>