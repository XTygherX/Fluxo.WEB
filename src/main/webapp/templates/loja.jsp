<%@page import="controller.ControllerCarregarDados"%>
<%@page import="controller.ControllerLogin"%>
<%@page import="model.JavaBeansDashBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.JavaBeansLoja"%>
<%@ include file="templatesInclude/validarUser.jsp" %>
<%

	ArrayList<JavaBeansLoja> listaLojas = (ArrayList<JavaBeansLoja>) request.getAttribute("listaLojas");
	ArrayList<String> tabelas = (ArrayList<String>) request.getAttribute("tabelas");
	String alerta = String.valueOf(request.getAttribute("alertUser"));
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
	<%@ include file="templatesInclude/scriptEimportsTop.jsp" %>
    
    <link rel="stylesheet" href="css/dashboardLojaIndividual.css">
    <script type="text/javascript" src="js/enxovalSAP.js"></script>
    <script type="text/javascript" src="js/graficoGanttLoja.js"></script>
    <script type="text/javascript" src="js/grafico.js"></script>
    <script type="text/javascript" src="js/updateControle.js"></script>
    <script src="https://code.jscharting.com/latest/jscharting.js"></script>
    <script type="text/javascript" src="js/jsExternos/echarts.js"></script>
    

    

  	<title>Fluxo TI - DashBoard Loja</title>
  	
</head>
  <body>
  <!-- toContainer.append("<th class='text-center "+descClass+"_BG' scope='row'><button class='btn btn-link' data-toggle='collapse' data-target='#"+descClass+"_observacoes' aria-controls='"+desc+"_observacoes' type='button' ><svg xmlns='http://www.w3.org/2000/svg' width='24' height='24' viewBox='0 0 24 24' style='fill: rgba(0, 0, 0, 1);transform: ;msFilter:;'><path d='M5 2c-1.103 0-2 .897-2 2v12c0 1.103.897 2 2 2h3.586L12 21.414 15.414 18H19c1.103 0 2-.897 2-2V4c0-1.103-.897-2-2-2H5zm14 14h-4.414L12 18.586 9.414 16H5V4h14v12z'></path><path d='M7 7h10v2H7zm0 4h7v2H7z'></path></svg></span></button></th>") -->
    <!-- Menu lateral -->
    <div id="menulateral">
    <%@ include file="templatesInclude/menuLateral.jsp" %>
    </div>
    <!-- cards do TOPO -->
    <div class="cardBox">
   		<div class="cards">
   			<div>
   				<div class="data" id="inicioRecebimento"></div>
   				<div class="cardName">Inicio de recebimento</div>
   			</div>
   			<div class="iconBx">
   				<ion-icon name="calendar-outline"></ion-icon>
   			</div>
   		</div>
   		<div class="cards">
   			<div>
   				<div class="data" id="inauguracao"></div>
   				<div class="cardName">Data inauguração</div>
   			</div>
   			<div class="iconBx">
   				<ion-icon name="calendar-outline"></ion-icon>
   			</div>
   		</div>
   		<div class="cards">
   			<div>
   				<div class="data" id="atividadesTotal"></div>
   				<div class="cardName">Total Atividades</div>
   			</div>
   			<div class="iconBx">
   				<ion-icon name="list-outline"></ion-icon>
   			</div>
   		</div>
   		<div class="cards pendentes">
   			<div>
   				<div class="data" id="atividadesPendentes"></div>
   				<div class="cardName">Atividades Pendentes</div>
   			</div>
   			<div class="iconBx">
   				<ion-icon name="checkmark-circle-outline"></ion-icon>
   			</div>
   		</div>
   		<div class="cards vencidas">
   			<div>
   				<div class="data" id="atividadesVencidas"></div>
   				<div class="cardName">Atividades Vencidas</div>
   			</div>
   			<div class="iconBx">
   				<ion-icon name="close-circle-outline"></ion-icon>
   			</div>
   		</div>
   		<div class="cards completas">
   			<div>
   				<div class="data" id="atividadesCompletas"></div>
   				<div class="cardName">Atividades Completas</div>
   			</div>
   			<div class="iconBx">
   				<ion-icon name="checkmark-done-circle-outline"></ion-icon>
   			</div>
   		</div>
   	</div>
    
    <!-- cardLaterais -->
      <div class="cardLateral font-weight-bold ">
        <div class="card text-dark bg-light">
          <div class="card-header bg-info ">Andamento da Obra</div>
          <div class="card-body" id="andamentoObra">
          </div>
        </div>
        
        <ul id="menuSuspenso">
        	<li>
        		<select	id="numLoja" class="custom-select" name="numLoja" onchange="selectLoja(this.value)">
					<%for (int i = 0; i < listaLojas.size(); i++) {
						if(request.getParameter("loja").equals(listaLojas.get(i).getNumLoja())){%>
							<option><%=listaLojas.get(i).getNumLoja()%> - <%=listaLojas.get(i).getNomeLoja()%></option>	
						<%}
					}%>				
					<%for (int i = 0; i < listaLojas.size(); i++) {%>
						<option value="<%=listaLojas.get(i).getNumLoja()%>"><%=listaLojas.get(i).getNumLoja()%> - <%=listaLojas.get(i).getNomeLoja()%></option>
					<%}%>
				</select>
        	</li>
        	<!-- 
			<li class="dropDown">
				<a href="#"class="dropdown-toggle">Links</a>
				<ul class="hidden shadow-lg" id="link">
					
				</ul>
			</li>
			<li class="dropDown ">
				<a href="#" class="dropdown-toggle">Sistemas</a>
				<ul class="hidden shadow-lg" id="sistemas">
				
				</ul>
			</li>
			<li class="dropDown" id="myGroup">
				<a href="#" class="dropdown-toggle" data-toggle="collapse" data-target="#Graficos" aria-expanded="true" aria-controls="collapseOne"> Graficos </a>
				<ul class="hidden shadow-lg" id="Graficos" data-parent="">
					<li class="" data-toggle="collapse" data-target="#chart_div" aria-expanded="false" aria-controls="#chart_div">
                    Completo
                    </li>
                    <li class="" data-toggle="collapse" data-target="#chartContainer" aria-expanded="false" aria-controls="#chartContainer">
                    Simples
                    </li>
				</ul>
			</li> -->
			<li class="dropDown " id="myGroup2">
				<a href="#" class="dropdown-toggle" data-toggle="collapse" data-target="#Controles" aria-expanded="true" aria-controls="collapseOne">  Controles</a>
				<ul class="hidden shadow-lg" id="Controles">
				
				</ul>
			</li>
        </ul>
       </div>
            <!-- Grafico/Tabelas/UPDATE Central OCULTO -->
       <div class="frameCentral">
         <!--GRAFICO DETALHADO -->
         <div id="chart_div" class="card text-dark bg-light collapse " data-parent="#myGroup"></div>
         <!--GRAFICO SIMPLES -->
         <div id="chartContainer" class="card text-dark bg-light collapse" data-parent="#myGroup"></div>
         <!-- CONTROLES CARREGA AQUI COM JAVASCRIPT -->
       </div>
          
    

	<%@ include file="templatesInclude/scriptsFimBody.jsp" %>

	<script type="text/javascript">
	onload = function(){
		<%if(request.getParameter("erro") != null){
			if (request.getParameter("erro").equals("permisaoUser")){%>
				alert('Você não tem permissao para editar os controles!');
			<%}
		}%>
		<%if (request.getParameter("loja") == null){%>
			<%response.sendRedirect("./loja?loja="+ listaLojas.get(0).getNumLoja());%>
		<%}%>
		var query = location.search.slice(1);
		var partes = query.split('&');
		var data = {};
		partes.forEach(function (parte) {
		    var chaveValor = parte.split('=');
		    var chave = chaveValor[0];
		    var valor = chaveValor[1];
	    	if (valor == "CA") {
				Swal.fire(
  				  		'Sucesso!',
	    				"Controle Atualizado com Sucesso!",
	    				'success'
  					)	
	  		}else if (valor == "LA"){
				Swal.fire(
  				  		'Sucesso!',
	    				"Links Atualizado com Sucesso!",
	    				'success'
  					)
	  		}else if (valor == "SA"){
				Swal.fire(
  				  		'Sucesso!',
	    				"Sistemas Atualizado com Sucesso!",
	    				'success'
  					)
	  		}else if (valor == "ERRO"){
	  			Swal.fire(
						'Atenção!'	,
						'Ocorreu um erro ao atualizar!',
			  			'error'
			  			)
	  		}
		});

	     //CRIANDO TABELAS CONTROLES 
		//POPULANDO TABELAS DE CONTROLES
		
		<%for (String txt: tabelas){%>
			criandoTabelas("<%=txt%>",<%=request.getParameter("loja")%>) 
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
		
		//CRIANDO TABELAS CONTROLES 
		//POPULANDO TABELAS DE CONTROLES
		<% for (int i = 0; i< listaLojas.size(); i++){%>
				if ( <%=String.valueOf(listaLojas.get(i).getNumLoja())%> == <%=request.getParameter("loja")%>){
					var nomeLoja = "<%=listaLojas.get(i).getNomeLoja()%>";
					var	dataInauguracao = "<%=String.valueOf(listaLojas.get(i).getDataInaguracao())%>";
				}			
		<%}%>
		
		//Populando Card TOPO
		//variaveis %
		var TotalLojaPercent = [<%=contadorTotalTarefas%>,0];
		var dataInauguracao = new Date(dataInauguracao)
		var inicioRecebimento = new Date(dataInauguracao)
		var elemento
		inicioRecebimento.setDate(dataInauguracao.getDate()-45)
		dataInauguracao.setDate(dataInauguracao.getDate()+1)
		
		document.getElementById("inicioRecebimento").innerHTML = inicioRecebimento.toLocaleDateString();
		document.getElementById("inauguracao").innerHTML = dataInauguracao.toLocaleDateString();
		document.getElementById("atividadesTotal").innerHTML = <%=contadorTotalTarefas%>;
		elemento = document.getElementsByClassName("table-warning").length + document.getElementsByClassName("table-normal").length;
		document.getElementById("atividadesPendentes").innerHTML = elemento / 5;
		elemento = document.getElementsByClassName("table-success");
		TotalLojaPercent[1] = elemento.length / 5;
		document.getElementById("atividadesCompletas").innerHTML = elemento.length / 5;
		elemento = document.getElementsByClassName("table-danger");
		document.getElementById("atividadesVencidas").innerHTML = elemento.length / 5;
		graficoPercent((TotalLojaPercent[1] / TotalLojaPercent[0] * 100).toFixed(0));
		
		<%if((Boolean) request.getSession().getAttribute("edicao") != true){ %> 
			var elemento = document.getElementsByClassName("disableValidation");
			for (var i = 0; i < elemento.length ; i++){		 		
		 		elemento[i].disabled = true;
		 	}
		<%}%>
		
		<%for (String txt: tabelas){%>
			graficoControles('<%=txt%>');
		<%}%>		
	}
	</script>
  </body>
</html>