<%@page import="org.apache.catalina.connector.Response"%>
<%@page import="javax.swing.text.Document"%>
<%@page import="controller.ControllerLogin"%>
<%@page import="model.JavaBeansDashBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.JavaBeansLoja"%>
<%@ include file="templatesInclude/validarUser.jsp"%>
<%
	ArrayList<JavaBeansLoja> listaLojas = (ArrayList<JavaBeansLoja>) request.getAttribute("listaLojas");
	ArrayList<String> tabelas = (ArrayList<String>) request.getAttribute("tabelas");
	String alerta = String.valueOf(request.getAttribute("alertUser"));


%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="templatesInclude/scriptEimportsTop.jsp"%>
<script type="text/javascript" src="js/relatorioControles.js"></script>

<link rel="stylesheet" href="css/relatorio.css">
<title>Fluxo TI - Relatórios</title>

</head>
<body>
	<!-- Menu lateral -->
	<div id="menulateral">
		<%@ include file="templatesInclude/menuLateral.jsp"%>
	</div>


	<div class="card  cardPDF embed-responsive embed-responsive-4by3">
		<div class="iFrame embed-responsive-item" id="iFrame">
			<div class="card cardA">
				<div class="card retanguloCantoInferior">
					<p class="font-weight-bold text-center">Expansão - TI</p>
				</div>
				<div class="imgLogo">
					<img src="img/Logo_assai.png">
				</div>
				<div class="print">
					<button class="btn" type="button" onclick="print()">
						<i class="bx bxs-printer bx-tada"></i>
					</button>
				</div>
				<div class="cabecalho">
					<%

			    	if (request.getParameter("loja") == null) {

			    		response.sendRedirect("./loja?loja="+ listaLojas.get(0).getNumLoja());
					}
			    %>
					<%for(int i = 0; i < listaLojas.size(); i++){
						if (request.getParameter("loja").equals(listaLojas.get(i).getNumLoja())){%>
					<p class="font-weight-bold dadoTitulos">
						<i class="bx bx-chevrons-right bx-tada align-middle "></i><%=listaLojas.get(i).getNumLoja()%>
						-
						<%=listaLojas.get(i).getNomeLoja()%></p>
					<p class="font-weight-light dadosSub">
						<i class="bx bx-chevron-right bx-tada align-middle "></i>Data de
						inauguração:
						<%=listaLojas.get(i).getDataInaguracao()%></p>
					<%}
					}%>
				</div>
				<div class="corpo" id="corpo"></div>
			</div>
		</div>
	</div>

	<div class="card titulos">
		<div class="titulosPDF">
			<label for="inputTitulos">Escreva o Título</label> <input type="text"
				list="listaTitulos" name='nome' id="inputTitulos"
				placeholder="Titulo">
			<datalist id="listaTitulos">
				<option></option>
			</datalist>
			<div class="row botoes">
				<button class="btn btn-outline-secondary btn-sm" type="button"
					onclick="addInputTabela()">Adicionar</button>
				<button class="btn btn-outline-secondary btn-sm" type="button"
					onclick="delInputTabela()">Remover</button>
			</div>
			<table class="card table table-striped table-hover tabelaTitulos"
				id="tabelaTitulos">
				<thead>
					<tr>
						<th scope="col">Títulos</th>
					</tr>
				</thead>
				<tbody onclick="selecionarLinha(event)">
					<tr>

					</tr>
				</tbody>
			</table>
		</div>
	</div>

	<div class="card controle">
		<h5 class="card-header text-white bg-secondary">Controles</h5>
		<label for="numLoja"><h6>Loja:</h6></label> <select id="numLoja"
			class="custom-select" name="numLoja" onchange=selectLoja(this.value)>
			<option><%=request.getParameter("loja")%></option>
			<%for (int i = 0; i < listaLojas.size(); i++) {%>
			<option value="<%=listaLojas.get(i).getNumLoja()%>"><%=listaLojas.get(i).getNumLoja()%>
				-
				<%=listaLojas.get(i).getNomeLoja()%></option>
			<%}%>
		</select> <label for="groupInputRelatorio"><h6>Capos para gerar o
				PDF:</h6></label>
		<div class="form-group card-text form-row" id="groupInputRelatorio">
			<div class="form-check  checkbox">
				<input class="form-check-input" type="checkbox" id="descricao"
					name="descricao"> <label class="form-check-label"
					for="descricao">Descrição</label>
			</div>
			<div class="form-check checkbox">
				<input class="form-check-input" type="checkbox" id="dataInicio"
					name="dataInicio"> <label class="form-check-label"
					for="dataInicio">Data Início</label>
			</div>
			<div class="form-check checkbox">
				<input class="form-check-input" type="checkbox" id="dataTermino"
					name="dataTermino"> <label class="form-check-label"
					for="dataTermino">Data Término</label>
			</div>
			<div class="form-check checkbox">
				<input class="form-check-input" type="checkbox" id="status"
					name="status"> <label class="form-check-label" for="status">Status</label>
			</div>
			<div class="form-check checkbox">
				<input class="form-check-input" type="checkbox" id="observacoes"
					name="observacoes"> <label class="form-check-label"
					for="observacoes">Observações</label>
			</div>
		</div>
		<div class="tabela"></div>
	</div>

	<%@ include file="templatesInclude/scriptsFimBody.jsp"%>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jspdf/1.4.1/jspdf.debug.js"integrity="sha384-THVO/sM0mFD9h7dfSndI6TS0PgAGavwKvB5hAxRRvc0o9cPLohB0wb/PTA7LdUHs" crossorigin="anonymous"></script>
	<script type="text/javascript">

		
		<%for (String txt: tabelas){%>
		criandoTabelas("<%=txt%>") 
		<%}%>
		
		<%
		int contadorTotalTarefas = 0;
		for (String txt:tabelas){		
			ArrayList<JavaBeansDashBoard> carragarTabela = (ArrayList<JavaBeansDashBoard>) request.getAttribute(txt);
			contadorTotalTarefas += carragarTabela.size();
			for(int i = 0; i < carragarTabela.size();i++){%>	
				loopInserirValoresSelect("<%=carragarTabela.get(i).getDesc()%>","<%=carragarTabela.get(i).getDataInicio()%>","<%=carragarTabela.get(i).getDataTermino()%>",<%=carragarTabela.get(i).getStatus()%>,"<%=carragarTabela.get(i).getTxt()%>","<%=txt%>")		
		<%}
		}
		%>
    </script>
</body>
</html>