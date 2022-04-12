<%@page import="java.text.DecimalFormat"%>
<%@page import="model.JavaBeansDashBoard"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
<link rel="stylesheet" href="css/dashboardIndex.css">


<title>Fluxo TI - Expansão TI</title>

</head>
<body>
	<!-- Menu lateral -->
	<div id="menulateral">
		<%@ include file="templatesInclude/menuLateral.jsp"%>
	</div>



	<%@ include file="templatesInclude/scriptsFimBody.jsp"%>
	<script type="text/javascript">	

	function carregarGrafico(){
	    google.charts.load('current', {'packages':['gantt'],'language': 'pt-br'});
	    google.charts.setOnLoadCallback(drawChart);
	    var lista = []
	    
	    function percentLoja(numLoja){
	    	
	    	<% for (int f = 0; f < 0/*percentLojas.size()*/ ; f++){
	    	Integer excluir = 0;
	    	%>
				if (<%=excluir /*percentLojas.get(f).getTxt()*/%> == (numLoja)){
					var percent = <%=excluir/*percentLojas.get(f).getFlutuante()*/%>
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