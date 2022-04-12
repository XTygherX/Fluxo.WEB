<%@page import="java.sql.Date"%>
<%@page import="java.text.DecimalFormat"%>
<%@page import="model.JavaBeansDashBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.JavaBeansLoja"%>
<%@ include file="templatesInclude/validarUser.jsp"%>
<%
	ArrayList<JavaBeansLoja> listaLojas = (ArrayList<JavaBeansLoja>) request.getAttribute("selectLojas");
	ArrayList<JavaBeansDashBoard> dadosDashBoard = (ArrayList<JavaBeansDashBoard>) request.getAttribute("dadosDashBoard");
	ArrayList<JavaBeansDashBoard> percentLojas = (ArrayList<JavaBeansDashBoard>) request.getAttribute("percentLojas");

%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="templatesInclude/scriptEimportsTop.jsp"%>
<link rel="stylesheet" href="css/dashboardIndex.css">
<script src="https://code.jscharting.com/latest/jscharting.js"></script>
<script type="text/javascript" src="js/jsExternos/echarts.js"></script>
<script type="text/javascript" src="js/index.js"></script>

<title>Fluxo TI - Expansão TI</title>

</head>
<body>

	<!-- Menu lateral -->
	<div id="menulateral">
		<%@ include file="templatesInclude/menuLateral.jsp"%>
	</div>
	<!-- cards do TOPO -->

	<div class="cardBox">
		<div class="cards">
			<div>
				<div class="data" id="obrasAndamento"></div>
				<div class="cardName">Obras em andamento</div>
			</div>
			<div class="iconBx">
				<ion-icon name="construct-outline"></ion-icon>
			</div>
		</div>
		<div class="cards">
			<div>
				<div class="data" id="obrasFinalizadas"></div>
				<div class="cardName">Obras finalizadas</div>
			</div>
			<div class="iconBx">
				<i class='bx bxs-buildings'></i>
			</div>
		</div>
		<div class="cards">
			<div>
				<div class="data" id="obrasDisponiveis"></div>
				<div class="cardName">Total de Obras</div>
			</div>
			<div class="iconBx">
				<ion-icon name="construct-outline"></ion-icon>
			</div>
		</div>
		<div class="cards">
			<div>
				<div class="data" id="obrasIndisponiveis"></div>
				<div class="cardName">Obras a iniciar</div>
			</div>
			<div class="iconBx">
				<ion-icon name="hourglass-outline"></ion-icon>
			</div>
		</div>

	</div>

	<!-- cardLaterais -->
	<div class="cardBoxLateral">
		<div class="cards">
			<div>
				<div class="data" id="percentObra"></div>
				<div class="cardName">% Geral das Obras</div>
			</div>
			<div class="iconBx">
				<ion-icon name="construct-outline"></ion-icon>
			</div>
		</div>
		<div class="cards">
			<div>
				<div class="data" id="percentLink"></div>
				<div class="cardName">% Links Instalados</div>
			</div>
			<div class="iconBx">
				<ion-icon name="construct-outline"></ion-icon>
			</div>
		</div>
		<div class="cards">
			<div>
				<div class="data" id="percentEnxoval"></div>
				<div class="cardName">% Enxovais feitos</div>
			</div>
			<div class="iconBx">
				<ion-icon name="construct-outline"></ion-icon>
			</div>
		</div>
	</div>

	<!--  graficoCentral -->
	<div id="chart_div" class="cards" onclick="myFuction(event)"></div>
	
	<div id="cards_loja" class="cardBoxLojas" >
		<!--  
		<div class="cards">
			<div class="card_nome_loja">Petrolina</div>
			<div class="card_p">
				<div id="card_porcentagem" class="card_porcentagem">%</div>
			</div>
			<div class="card_datas" id="card_datas">
				<div class="card_data_recebimento">Inicio Recebimento</div>
				<div class="card_data_inauguracao">Data Inaguração</div>
			</div>
		</div>
		-->
	</div>
 	
	<%@ include file="templatesInclude/scriptsFimBody.jsp"%>
	<script type="text/javascript">
	onload = function(){
		<%if(request.getParameter("erro") != null){
			if (request.getParameter("erro").equals("permisaoLoja")){%>
				alert('Você não tem permissao para acessar Lojas');
			<%}else if(request.getParameter("erro").equals("permisaoAdmin")){%>
				alert('Você não tem permissao para acessar o Gerenciar');
			<%}
		}%>
		var contObra = 0;
		var contLink = 0;
		var contLinkTotal = 0;
		var contLinkVozTotal = 0;
		var contLinkVoz = 0;
		var contEnxoval = 0;
		var contEnxovalTotal = 0;		
		var contLojasAndamento = 0;
		var contLojasFinalizadas = 0;
		var lojasDisponiveis = 0;
		var lojasIndisponiveis = 0;
		<%for( int i = 0 ; i < listaLojas.size(); i++){
			if (listaLojas.get(i).getStatus() == false){%>
				contLojasAndamento++;
			<%}else{%>
				contLojasFinalizadas++;				
			<%}
			if (listaLojas.get(i).getVisualizacao() == true){%>
				lojasDisponiveis++;
			<%}else if (listaLojas.get(i).getVisualizacao() == false && listaLojas.get(i).getStatus() == false){%>
				lojasIndisponiveis++
			<%}
			
		}%>
		
		document.getElementById("obrasAndamento").innerHTML = contLojasAndamento;
		document.getElementById("obrasFinalizadas").innerHTML = contLojasFinalizadas;
		document.getElementById("obrasDisponiveis").innerHTML = lojasDisponiveis;
		document.getElementById("obrasIndisponiveis").innerHTML = lojasIndisponiveis;
		
	}	
	
	
	function porcentagem_Loja(num_loja){
		
		<%for(int p = 0; p < percentLojas.size(); p++){%>
			if(<%=percentLojas.get(p).getTxt()%> == num_loja){
					var porcentagem = <%=percentLojas.get(p).getFlutuante()%>
					return porcentagem
				}
		<%}%>
	}
	var listaPercent = []
	<%for (int i = 0; i < listaLojas.size(); i++) {
		if (listaLojas.get(i).getVisualizacao() == true){%>
			var numLoja = '<%=listaLojas.get(i).getNumLoja()%>'
			var dataInauguracao = new Date('<%=listaLojas.get(i).getDataInaguracao()%>')
			var dataRecebimento = new Date('<%=listaLojas.get(i).getDataEntradaTi()%>')
			var porcentagem = porcentagem_Loja('<%=listaLojas.get(i).getNumLoja()%>')
			listaPercent.push(porcentagem)
			loopCriarCards('<%=listaLojas.get(i).getNomeLoja()%>', porcentagem , numLoja,dataInauguracao,dataRecebimento)	
		<%}
	}%>
	
	var percentObra = 0;
    for (var i = 0; i < listaPercent.length; i++){
    	percentObra += listaPercent[i]
    	console.log(percentObra);
    }
    percentObra = percentObra / listaPercent.length
    document.getElementById("percentObra").innerHTML = percentObra.toFixed(0)+ "%"
	
    //carregarGrafico();
    
    function carregarGrafico(){
	    google.charts.load('current', {'packages':['gantt'],'language': 'pt-br'});
	    google.charts.setOnLoadCallback(drawChart);
	    var lista = []
	    
	    function percentLoja(numLoja){
	    	<% for (int f = 0; f < percentLojas.size() ; f++){%>
				if (<%=percentLojas.get(f).getTxt()%> == (numLoja)){
					var percent = <%=percentLojas.get(f).getFlutuante()%>
					percent = percent.toFixed(0)
					return parseInt(percent)
				}
			<%}%>
	    }
	    
	    function drawChart() {
	      var data = new google.visualization.DataTable();
	      data.addColumn('string', 'Task ID');
	      data.addColumn('string', 'Task Name');
	      data.addColumn('string', 'Resource');
	      data.addColumn('date', 'Start Date');
	      data.addColumn('date', 'End Date');
	      data.addColumn('number', 'Duration');
	      data.addColumn('number', 'Percent Complete');
	      data.addColumn('string', 'Dependencies');
			
	      data.addRows([
				<%for (int i = 0; i < listaLojas.size(); i++) {
					if (listaLojas.get(i).getVisualizacao() == true){%>
						['<%=listaLojas.get(i).getNumLoja()%>',
						'<%=listaLojas.get(i).getNomeLoja()%>',
						'<%=listaLojas.get(i).getNumLoja()%>',
						new Date('<%=String.valueOf(listaLojas.get(i).getDataEntradaTi())%>'),
						new Date('<%=String.valueOf(listaLojas.get(i).getDataInaguracao())%>'), 
						null,
						percentLoja(<%=listaLojas.get(i).getNumLoja()%>),
						null],
					<%}
				}%>
	      ]);
	      var options = {
	        height: (data.getNumberOfRows() * 45 + 45),
	        gantt: {
	            criticalPathEnabled: false,
	            percentEnabled: true,
	            sortTasks: false,
	            }
	      };

	    var chart = new google.visualization.Gantt(document.getElementById('chart_div'));

	    chart.draw(data, options);
	    google.visualization.events.addListener(chart, 'ready', afterDraw);

	    function afterDraw(){
	        var listaPercent = []
	        var toContainer = $('#chart_div > div > div');
	        <%for (int i = 0; i < listaLojas.size(); i++) {
				if (listaLojas.get(i).getVisualizacao() == true){%>
					listaPercent.push(percentLoja(<%=listaLojas.get(i).getNumLoja()%>))
				<%}
			}%>
	        var cont = 0
	        $( "#chart_div g:eq(5) rect" ).each(function() {	        	
	             toContainer.append("<div class='font-weight-bold' style='top:"+(parseInt($(this).attr('y'))-3)+"px; left: "+$(this).attr('x')+"px;  height: "+$(this).attr('height')+"px;text-align: center;position:absolute;line-height:2' >"+ listaPercent[cont] +"%</div>");
	        	cont++
	        });
	        var percentObra = 0;
	        for (var i = 0; i < listaPercent.length; i++){
	        	percentObra += listaPercent[i]
	        	console.log(percentObra);
	        }
	        percentObra = percentObra / listaPercent.length
	        document.getElementById("percentObra").innerHTML = percentObra.toFixed(0)+ "%" ;
	    }
	    
	    }

	}
	function mouseEventHandler(mEvent) {
	    var click = null;
	    var loja = '';
	    click = mEvent.srcElement || mEvent.document.getElementById('chart_div');
	    loja = click.innerHTML ;
	    <%for (int i = 0; i < listaLojas.size(); i++) {%>
		    if (loja == '<%=listaLojas.get(i).getNomeLoja()%>') {
		        window.location.href = ('loja?loja='+<%=listaLojas.get(i).getNumLoja()%>);	
		    }
		<%}%>
	}

	function myFuction(e) {
	    onclick = mouseEventHandler;
	}
    
	</script>
</body>
</html>