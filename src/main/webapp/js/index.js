function loopCriarCards(loja,porcentagem,numLoja,dataInauguracao,dataRecebimento){
	var nomeLoja = loja.replaceAll(" ","_").replaceAll("(","_").replaceAll(")","_")
	var cardBox = $('#cards_loja')
	cardBox.append("<div class='cards' id='card_"+nomeLoja+"' onClick='direcionar("+numLoja+")'></div>")
	var card = $('#card_'+nomeLoja)
	card.append("<div class='card_nome_loja' id='card_nome_"+nomeLoja+"'>"+numLoja+" - "+loja+"</div>"
			+"<div class='card_p'>"
				+"<div class='card_porcentagem' id='card_porcentagem_"+nomeLoja+"'>% Geral Obra:"+porcentagem.toFixed(0)+"%</div>"
				+"<div class='card_completas' id='card_completas_"+nomeLoja+"'>Concluidas:"+30/200+"%</div>"
				+"<div class='card_pendentes' id='card_pendentes_"+nomeLoja+"'>Pendente:"+170+"%</div>"
				+"<div class='card_vencidas' id='card_vencidas_"+nomeLoja+"'>Pendente:"+50+"%</div>"			
			+"</div>"
			+"<div class='card_datas' id='card_datas_"+nomeLoja+"'>"
				+"<div class='card_data_recebimento' id='card_recebimento_"+nomeLoja+"'>Inicio Recebimento: "+dataRecebimento.toLocaleDateString()+"</div>"
				+"<div class='card_data_inauguracao' id='card_inauguracao_"+nomeLoja+"'>Data Inaguração: "+dataInauguracao.toLocaleDateString()+"</div>"
			+"</div>")
			
	//carregar_porcentagem(porcentagem,"card_porcentagem_"+nomeLoja)
}


function carregar_porcentagem(porcentagem, id){
		var chartDom = document.getElementById(id);
		var myChart = echarts.init(chartDom);
		var option;

		option = {
		  series: [
		    {
		      type: 'gauge',
		      startAngle: 180,
		      endAngle: 0,
		      min: 0,
		      max: 1,
		      splitNumber: 8,
		      axisLine: {
		        lineStyle: {
		          width: 6,
		          color: [
		            [0.25, '#FF6E76'],
		            [0.5, '#FDDD60'],
		            [0.75, '#58D9F9'],
		            [1, '#7CFFB2']
		          ]
		        }
		      },
		      pointer: {
		        icon: 'path://M12.8,0.7l12,40.1H0.7L12.8,0.7z',
		        length: '12%',
		        width: 20,
		        offsetCenter: [0, '-60%'],
		        itemStyle: {
		          color: 'auto'
		        }
		      },
		      axisTick: {
		        length: 12,
		        lineStyle: {
		          color: 'auto',
		          width: 2
		        }
		      },
		      splitLine: {
		        length: 20,
		        lineStyle: {
		          color: 'auto',
		          width: 5
		        }
		      },
		      axisLabel: {
		        color: '#464646',
		        fontSize: 20,
		        distance: -60,
		        formatter: function (value) {
		          if (value === 0.875) {
		            return 'A';
		          } else if (value === 0.625) {
		            return 'B';
		          } else if (value === 0.375) {
		            return 'C';
		          } else if (value === 0.125) {
		            return 'D';
		          }
		          return '';
		        }
		      },
		      title: {
		        offsetCenter: [0, '-20%'],
		        fontSize: 30
		      },
		      detail: {
		        fontSize: 50,
		        offsetCenter: [0, '0%'],
		        valueAnimation: true,
		        formatter: function (value) {
		          return Math.round(value*100) + '%';
		        },
		        color: 'auto'
		      },
		      data: [
		        {
		          value: parseFloat(porcentagem/100)
		        }
		      ]
		    }
		  ]
		};

		option && myChart.setOption(option);

	}
	
	
	
	function direcionar(loja) {
	    
        //window.location.href = ('loja?loja='+loja);	

	}
