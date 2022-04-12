function graficoSimples(solicitacao, controle, configuracao, instalacao , validacao, loja) {
    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        animationDuration: 2000,
        exportEnabled: true,
        height: 300,
        width: 935,
        animationDuration: 3000,
        title: {
            text: (loja)
        },
        axisY:{
            minimum: 0,
            maximum: 100
        },
        data: [{
            type: "bar",
            indexLabelFontSize: 20,
            indexLabel: "{y}%",
            dataPoints: [
              { label: "SOLICITAÇÃO", y: parseInt(solicitacao.toFixed(0)) },
              { label: "CONTROLE DE SOLICITAÇÃO", y: parseInt(controle.toFixed(0))},
              { label: "CONFIGURAÇÃO", y: parseInt(configuracao.toFixed(0)) },
              { label: "INSTALÇÃO", y: parseInt(instalacao.toFixed(0)) },
              { label: "VALIDAÇÃO", y: parseInt(validacao.toFixed(0)) }
            ]
        }]
    });
    chart.render();

    var xSnapDistance = 3; //chart.axisX[0].convertPixelToValue(chart.get("dataPointWidth")) / 2;
    var ySnapDistance = 3;

    var xValue, yValue;

    var mouseDown = false;
    var selected = null;
    var changeCursor = false;

    var timerId = null;

    function getPosition(e) {
        var parentOffset = $("#chartContainer > .canvasjs-chart-container").offset();
        var relX = e.pageX - parentOffset.left;
        var relY = e.pageY - parentOffset.top;
        xValue = Math.round(chart.axisX[0].convertPixelToValue(relX));
        yValue = Math.round(chart.axisY[0].convertPixelToValue(relY));
    }

    function searchDataPoint() {
        var dps = chart.data[0].dataPoints;
        for(var i = 0; i < dps.length; i++ ) {
            if( (xValue >= dps[i].x - xSnapDistance && xValue <= dps[i].x + xSnapDistance) && (yValue >= dps[i].y - ySnapDistance && yValue <= dps[i].y + ySnapDistance) )
            {
                if(mouseDown) {
                    selected = i;
                    break;
                } else {
                    changeCursor = true;
                    break;
                }
            } else {
                selected = null;
                changeCursor = false;
            }
        }
    }

    jQuery("#chartContainer > .canvasjs-chart-container").on({
        mousedown: function(e) {
            mouseDown = true;
            getPosition(e);
            searchDataPoint();
        },
        mousemove: function(e) {
            getPosition(e);
            if(mouseDown) {
                clearTimeout(timerId);
                timerId = setTimeout(function(){
                    if(selected != null) {
                        chart.data[0].dataPoints[selected].y = yValue;
                        chart.render();
                    }
                }, 0);
            }
            else {
                searchDataPoint();
                if(changeCursor) {
                    chart.data[0].set("cursor", "n-resize");
                } else {
                    chart.data[0].set("cursor", "default");
                }
            }
        },
        mouseup: function(e) {
            if(selected != null) {
                chart.data[0].dataPoints[selected].y = yValue;
                chart.render();
                mouseDown = false;
            }
        }
    });

    }



function graficoPercent(percent){
	console.log(percent)
	var animationDuration = 420; 
	var chart = JSC.chart( 
	  'andamentoObra', 
	  { 
	    debug: true, 
	    type: 'gauge ', 
	    legend_visible: false, 
	    animation_duration: animationDuration, 
	    xAxis: { 
	      scale: { 
	        // Helps position the marker on top of the y Axis. 
	        range: [0, 1] 
	      } 
	    }, 
	    palette: { 
	      pointValue: '%yValue', 
	      ranges: [ 
	        { value: [0, 30], color: '#FF5353' }, 
	        { value: [30, 70], color: '#FFD221' }, 
	        { value: [70, 100], color: '#77E6B4' } 
	      ] 
	    }, 
	    yAxis: { 
	      defaultTick: { 
	        // Pads around the gauge 
	        padding: 13, 
	        label_visible: false
	      }, 
	      line: { 
	        width: 15, 
	        // Gaps occur at tick intervals defined below. 
	        breaks_gap: 0.03, 
	        color: 'smartPalette'
	      }, 
	      scale: { range: [0, 100], interval: 2 } 
	    }, 
	  
	    defaultSeries: { 
	      opacity: 1, 
	      mouseTracking_enabled: false, 
	      shape: { 
	        label: { 
	          text: percent+"%", 
	          style: { fontSize: 40 }, 
	          align: 'center', 
	          verticalAlign: 'middle'
	        } 
	      } 
	    }, 
	    series: [ 
	      { 
	        type: 'marker', 
	        defaultPoint: { 
	          marker: { 
	            outline: { 
	              width: 10, 
	              color: 'currentColor'
	            }, 
	            fill: 'white', 
	            type: 'circle', 
	            size: 30 
	          } 
	        }, 
	        points: [{ y:  parseInt(percent)}] 
	      } 
	    ], 	
	  } 
	); 	
}

function graficoControles(tabela){
var nomeGrafico = tabela.replaceAll("_"," ").charAt(0).toUpperCase() + tabela.replaceAll("_"," ").substr(1)
nomeGrafico = nomeGrafico.replaceAll("cao","ção")
nomeGrafico = nomeGrafico.replaceAll("coes","ções")
nomeGrafico = nomeGrafico.replaceAll("Eletrica","Elétrica")
var completo = document.getElementById("frm" + tabela).getElementsByClassName("table-success").length / 5;
var andamento = (document.getElementById("frm" + tabela).getElementsByClassName("table-warning").length + document.getElementById("frm" + tabela).getElementsByClassName("table-normal").length) / 5;
var vencido = document.getElementById("frm"+ tabela).getElementsByClassName("table-danger").length / 5;	
	
var chartDom = document.getElementById('grafico'+tabela);
var myChart = echarts.init(chartDom);
var option;

option = {
  title: {
    text: nomeGrafico,
    left: 'center'
  },
  tooltip: {
    trigger: 'item'
  },
  legend: {
    orient: 'vertical',
    left: 'left'
  },
  series: [
    {
      name: 'Status',
      type: 'pie',
      radius: '50%',
      data: [
        { value: completo, name: 'Completo' },
        { value: andamento, name: 'Andamento' },
        { value: vencido, name: 'Vencidos' },
		],
		emphasis: {
			itemStyle: {
				shadowBlur: 10,
				shadowOffsetX: 0,
				shadowColor: 'rgba(0, 0, 0, 0.5)'
			}
		}
			}
		]
	};

	option && myChart.setOption(option);

}

function graficoControles2(tabela) {
	var containner = "grafico" + tabela
	var nomeGrafico = tabela.replaceAll("_", " ").charAt(0).toUpperCase() + tabela.replaceAll("_", " ").substr(1)
	nomeGrafico = nomeGrafico.replaceAll("cao", "ção")
	nomeGrafico = nomeGrafico.replaceAll("coes","ções")
	nomeGrafico = nomeGrafico.replaceAll("Eletrica","Elétrica")
	var completo = document.getElementById("frm" + tabela).getElementsByClassName("table-success").length / 5;
	var andamento = (document.getElementById("frm" + tabela).getElementsByClassName("table-warning").length + document.getElementById("frm" + tabela).getElementsByClassName("table-normal").length) / 5;
	var vencido = document.getElementById("frm"+ tabela).getElementsByClassName("table-danger").length / 5;
	var chart = new CanvasJS.Chart(containner,
    {
        title:{
        text: nomeGrafico
      },

      data: [
      {
       indexLabelPlacement: "outside",
       type: "doughnut",
       dataPoints: [
       {  y: completo, indexLabel: "Completo" , exploded: true ,color: "#66ff66"},
       {  y: andamento, indexLabel: "Andamento" ,exploded: true, color: "#ffff66"},
       {  y: vencido, indexLabel: "Vencido" ,exploded: true, color: "#ff3333"}


       ]
     }
     ]
   });

    chart.render();
  }
	
