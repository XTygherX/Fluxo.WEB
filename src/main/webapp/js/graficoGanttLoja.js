

function carregarGrafico(
					//ENTRADA TI - INAUGURACAO
				dataEntradaTI, dataInauguracao, FinalLojapercent,
				//ENVIO DE EQUIPAMENTOS
				envioEquipamentos,
				//INICIO RECEBIMENTO
				startRecebimento,
				//LINK
				link,
				//Terminais de consulta
				TC,
				//Antenas
				AP,
				//BALANCAS
				BAL,
				//CAFETERIA
				cafeteria,
				//AREA DE VENDA
				areaVenda,
				//CHECKOUT
				CK,
				//FINANCEIRO
				FIN,
				//SETOR GERENCIA
				gerencia,
				//BALCOES DE ATENDIMENTO E FISCAIS
				balcoes,
				//FRENTE DE LOJA
				FL,
				//ADMINISTRATIVO
				ADM,
				//RH
				RH,
				//ENTRADA FUNCIONARIOS
				entrada,
				//REFEITORIO
				refeitorio,
				//CRIPAGEM
				rede,
				//INAUGURAÇÃO
				inauguracao ){
	var lista = [];
	lista.push(FinalLojapercent);
	lista.push(envioEquipamentos[2]);
	lista.push(startRecebimento[2]);
	lista.push(link[2]);
	lista.push(TC[2]);
	lista.push(AP[2]);
	lista.push(BAL[2]);
	lista.push(cafeteria[2]);
	lista.push(areaVenda[2]);
	lista.push(CK[2]);
	lista.push(FIN[2]);
	lista.push(gerencia[2]);
	lista.push(balcoes[2]);
	lista.push(FL[2]);
	lista.push(ADM[2]);
	lista.push(RH[2]);
	lista.push(entrada[2]);
	lista.push(refeitorio[2]);
	lista.push(rede[2]);
	lista.push(inauguracao[2]);
	
    google.charts.load('current', {'packages':['gantt'],'language': 'pt-br'});
    google.charts.setOnLoadCallback(drawChart);

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
        ['TI', 'Entrada TI Obra', 'TI',
        new Date(dataEntradaTI), new Date(dataInauguracao), null, FinalLojapercent, null],

        ['Entrega_equipamentos', 'Envio de Equipamento', 'equip',
         new Date(envioEquipamentos[0]), new Date(envioEquipamentos[1]), null, parseInt(envioEquipamentos[2].toFixed(0)), null],
        ['Link', 'Link', 'equip',
         new Date(link[0]), new Date(link[1]), null, link[2], null],

        ['Solicitacoes', 'Start Recebimento', 'equip',
         new Date(startRecebimento[0]), new Date(startRecebimento[1]), null,parseInt(startRecebimento[2].toFixed(0)), null],

        ['Area_venda', 'Area de vendas ', 'Loja',
        new Date(areaVenda[0]), new Date(areaVenda[1]), null,parseInt(areaVenda[2].toFixed(0)), null],
        ['tc', 'Terminais de consulta', 'Loja',
        new Date(TC[0]), new Date(TC[1]), null,parseInt(TC[2].toFixed(0)), 'Area_venda'],
        ['antenas', 'Antenas', 'Loja',
        new Date(AP[0]), new Date(AP[1]), null,parseInt(AP[2].toFixed(0)), 'Area_venda'],
        ['balancas', 'Balanças', 'Loja',
        new Date(BAL[0]), new Date(BAL[1]), null,parseInt(BAL[2].toFixed(0)), 'Area_venda'],
        ['Cafeteria', 'Cafeteria', 'Loja',
        new Date(cafeteria[0]), new Date(cafeteria[1]), null,parseInt(cafeteria[2].toFixed(0)), 'Area_venda'],

        ['Frente_Loja', 'Frente de Loja ', 'Frente_Loja',
        new Date(FL[0]), new Date(FL[1]), null,parseInt(FL[2].toFixed(0)), null],
        ['pdv', 'Instalaçao PDV', 'Frente_Loja',
        new Date(CK[0]), new Date(CK[1]), null,parseInt(CK[2].toFixed(0)), 'Frente_Loja'],
        ['ger_tele', 'Gerencia/Tele Vendas', 'Frente_Loja',
        new Date(gerencia[0]), new Date(gerencia[1]), null,parseInt(gerencia[2].toFixed(0)), 'Frente_Loja'],
        ['fin', 'Financeiro', 'Frente_Loja',
        new Date(FIN[0]), new Date(FIN[1]), null,parseInt(FIN[2].toFixed(0)), 'Frente_Loja'],
        ['Cadastro', 'Cadastro/Fiscais', 'Frente_Loja',
        new Date(balcoes[0]), new Date(balcoes[1]), null,parseInt(balcoes[2].toFixed(0)), 'Frente_Loja'],

        ['Administrativo', 'Administrativo', 'Administrativo',
        new Date(ADM[0]), new Date(ADM[1]), null,parseInt(ADM[2].toFixed(0)), null],
        ['Rh', 'RH', 'Administrativo',
        new Date(RH[0]), new Date(RH[1]), null,parseInt(RH[2].toFixed(0)), 'Administrativo'],
        ['Entrada', 'Entrada Funcionarios', 'Administrativo',
        new Date(entrada[0]), new Date(entrada[1]), null,parseInt(entrada[2].toFixed(0)) , 'Administrativo'],
        ['Refeitorio', 'Refeitorio', 'Administrativo',
        new Date(refeitorio[0]), new Date(refeitorio[1]), null,parseInt(refeitorio[2].toFixed(0)) , 'Administrativo'],

        ['CL', 'Certificação Rede', 'CL',
        new Date(rede[0]), new Date(rede[1]), null, rede[2],'Solicitacoes,Area_venda,Frente_Loja'],
        ['Inauguracao', 'Inauguração', 'Inauguracao',
        new Date(inauguracao[0]), new Date(inauguracao[1]), null,parseInt(inauguracao[2].toFixed(0)) , 'Solicitacoes,Area_venda,Frente_Loja,CL']
      ]);

      var options = {
        height:(data.getNumberOfRows() * 45),
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
		var i = 0;
        var toContainer = $('#chart_div > div > div');
        $( "#chart_div g:eq(5) rect" ).each(function() {
             toContainer.append("<div class='font-weight-bold' style='top:"+(parseInt($(this).attr('y'))-3)+"px; left: "+$(this).attr('x')+"px;  height: "+$(this).attr('height')+"px;text-align: center;position:absolute;line-height:2' >"+lista[i]+"%</div>");
        i++
		});
    }
    }
}


function validarECriarData(inicio, termino){
		var rtermino = new Date(termino);
			rtermino.setDate(rtermino.getDate() - 0);
			rtermino = rtermino.toDateString();
			rtermino = rtermino.replace(new RegExp("/","g"), "-");
		
		if (inicio != 'null'){
			var rinicio = new Date(inicio);
			rinicio.setDate(rinicio.getDate() - 0);
			rinicio = rinicio.toDateString();
			rinicio = rinicio.replace(new RegExp("/","g"), "-"); 
		}else {					
			var rinicio = new Date(rtermino);
			rinicio.setDate(rinicio.getDate() - 10);
			rinicio = rinicio.toDateString();
			rinicio = rinicio.replace(new RegExp("/","g"), "-"); 
		}
		return [rinicio, rtermino];			
}

function valorMaiorEntreData(dataAnterior, inicio, termino){
	if (dataAnterior[0] == "" || dataAnterior[1] == ""){
		dataAnterior = validarECriarData(inicio, termino);
	}
	
	var dataAtual = validarECriarData(dataAnterior[0],dataAnterior[1])
	var dataParametros = validarECriarData(inicio, termino);
	if (new Date(dataAtual[0]) > new Date(dataParametros[0])){
		dataAtual[0] = dataParametros[0];
	}

	if (new Date(dataAtual[1]) < new Date(dataParametros[1])){
		dataAtual[1] = dataParametros[1];

	}
	
	return [dataAtual[0],dataAtual[1]];
}

function valdiarPercentPorBool(bool){
	if (bool == true){
		return 100;
	}else
		return 0;
	
}

function definirPercent(data){
	return (data[0]/data[1])*100;
}
function definirCont(data,bool){
	if (bool == "true"){
		data[0]++
	}
	data[1]++
	return [data[0],data[1]]
}


