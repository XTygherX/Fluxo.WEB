function criandoTabelas(tabela, loja){
	var novaTabela = tabela.replaceAll("_"," ").charAt(0).toUpperCase() + tabela.replaceAll("_"," ").substr(1);
	novaTabela = novaTabela.replaceAll("cao","ção")
	novaTabela = novaTabela.replaceAll("coes","ções")
	novaTabela = novaTabela.replaceAll("Eletrica","Elétrica")
	var toContainer = $('.frameCentral');
	toContainer.append("<form class='card collapse shadow-lg show' data-parent='#myGroup2' id='frm" + tabela +"' name='frm" + tabela+"'>"
	+"<h3 class='card-header bg-light text-dark card-footer text-left font-weight-bold'>" + novaTabela
	+"<div class='btnControlles row'>"
	+"<button onclick='setDataInicio(\""+tabela+"\")' class='btn btn-outline-secondary disableValidation "+tabela+"_btnSetDataInicio' type='button'>Definir data de Inicio</button>"
	+"<button onclick='statusCheck(\""+tabela+"\")' class='btn btn-outline-secondary "+tabela+"_BtnCheckStatus disableValidation' type='button'>Marcar todos</button>"
	+"</div>"
	+"</h3>"
	+"<table class='table table-striped table-hover' id='minhaTabela" + tabela +"' >"
	+"<thead>"
	+"<tr>"
	+"<th scope='col'>Descrição</th>"
	+"<th scope='col'>Data Início</th>"
	+"<th scope='col'>Data fim</th>"
	+"<th scope='col'>Status</th>"
	+"<th scope='col'></th>"
	+"</tr>"
	+"</thead>"
	+"<tbody>"
	+"</tbody>"
	+"</table>"
	+"<button class='btn btn-outline-secondary btn-lg btnSalvar disableValidation' type='button' onclick='updateTabela(\"frm"+tabela+"\")'>SALVAR</button>"

	+"<div class='card shadow-lg graficoControle' id='grafico" + tabela +"'></div>"
	+"</form>"	
	)	
	toContainer = $("#frm"+ tabela);
	toContainer.append("<input type='hidden' name='num_loja' value='"+loja+"'>")
	toContainer.append("<input type='hidden' name='tabela' value='"+tabela+"'>")
	toContainer = $('#Controles')
	toContainer.append("<li onclick='' class='listaControles'  data-toggle='collapse' data-target='#frm"+ tabela + "' aria-expanded='false' aria-controls='#frm "+ tabela + "'><a href='#'>" +  novaTabela +"</a></li>")
	
}



function loopInserirValoresSelect(desc,dataInicio,dataTermino,status,observacoes,tabela){
	descClass = desc.replaceAll(" ","_")+"_"+tabela.replaceAll(" ","_")
	var toContainer = $('#frm' + tabela + '> table > tbody');
	toContainer.append("<tr>")
	toContainer = $('#frm' + tabela + '> table > tbody > tr')
	var cont = toContainer.length -1
	toContainer[cont].classList.add(tabela+cont+"_TR")
	toContainer = $('.'+tabela+cont+'_TR')
	toContainer.append("<td class='"+descClass+"_BG' aria-label='Status'><span class='input-group-text'>" + desc.replaceAll("_"," ").charAt(0).toUpperCase() + desc.replaceAll("_"," ").toLowerCase().substr(1) + "</span></td> ")
    if (dataInicio != "null"){
		toContainer.append("<td class='"+descClass+"_BG'><input onfocusout='focusDateOut(\""+descClass+"\")' name='"+desc+"_dataInicio' type='date' class='form-control "+descClass+"_dataInicio "+tabela+"_setDateInicio disableValidation' value="+ dataInicio +" type='hidden'></td>")
	}else{
		toContainer.append("<td class='"+descClass+"_BG'><input onfocusout='focusDateOut(\""+descClass+"\")' name='"+desc+"_dataInicio' type='date' class='form-control "+descClass+"_dataInicio "+tabela+"_setDateInicio disableValidation' type='hidden'></td>" )
	}
	if (dataTermino != "null" ){
    	toContainer.append("<td class='"+descClass+"_BG'><input onfocusout='focusDateOut(\""+descClass+"\")' name='"+desc+"_dataTermino'  type='date' class='form-control "+descClass+"_dataTermino "+tabela+"_dataTermino disableValidation' value=" + dataTermino + "></td> ")
    }else{
	    toContainer.append("<td class='"+descClass+"_BG'><input onfocusout='focusDateOut(\""+descClass+"\")' name='"+desc+"_dataTermino'  type='date' class='form-control "+descClass+"_dataTermino "+tabela+"_dataTermino disableValidation'></td> ")
	}
	
	if ( status == true){
		toContainer.append("<td class='text-center "+descClass+"_BG cx-check'  aria-label='Status'><input class='"+tabela+"_statusCheck text-center align-middle disableValidation' name='"+desc+"_status' type='checkbox' checked></td>")
	}else{
		toContainer.append("<td class='text-center "+descClass+"_BG cx-check'  aria-label='Status'><input class='"+tabela+"_statusCheck text-center align-middle disableValidation' name='"+desc+"_status' type='checkbox'></td>")
	}
	
	toContainer.append("<td class='text-center "+descClass+"_BG obs' scope='row'><button  class='btn btn-link' data-toggle='collapse' data-target='#"+descClass+"_observacoes' aria-controls='"+desc+"_observacoes' type='button'><img src=\"img/message-alt-detail-regular-24.png\"</img></button></td>")

	if (observacoes == null || observacoes == "null"){
		toContainer.append("<div class='form-floating  collapse observacoes' id='"+descClass+"_observacoes'><textarea class='form-control observacoesTxt disableValidation' name='"+desc+"_observacoes' placeholder='Observações' value=''></textarea></div>")
    }else{

		toContainer.append("<div class='form-floating  collapse observacoes' id='"+descClass+"_observacoes'><textarea class='form-control observacoesTxt disableValidation' name='"+desc+"_observacoes' placeholder='Observações'>"+observacoes+"</textarea></div>")
		
	}
		
	toContainer.append("</tr>")	
	
	backGround(descClass,dataTermino,status);
	

}


function updateTabela(tabela){
	Swal.fire({
			icon: 'question',
			title: 'Atualizar Controle',
			text: 'Deseja atualizar esta controle?',
			showDenyButton: true,
			confirmButtonText: 'Confirmar',
			denyButtonText: 'Calcelar',
		}).then((result) => {
		  if (result.isConfirmed) {
				document.forms[tabela].action = "./updateControles";
				document.forms[tabela].submit();
		  }
	})

				



}

function selectLoja(value){
	window.location.href = "./loja?loja="+value;

}


function criandoLI(desc,option,id){
	var name = id.replace("#","")
	var toContainer = $(id);
	if (option == 1){
		toContainer.append("<li class=''><span>"+desc+":</span><select class='form-select disableValidation' name='"+ name +"_"+desc+"'><option value='1' selected>Não Iniciado</option><option value='2'>Andamento</option><option value='3'>Finalizado</option></select></li>")
	}else if (option == 2){
		toContainer.append("<li class=''><span>"+desc+":</span><select class='form-select disableValidation' name='"+ name +"_"+desc+"'><option value='1'>Não Iniciado</option><option value='2'selected>Andamento</option><option value='3'>Finalizado</option></select></li>")
	}else if (option == 3){
		toContainer.append("<li class=''><span>"+desc+":</span><select class='form-select disableValidation' name='"+ name +"_"+desc+"'><option value='1'>Não Iniciado</option><option value='2'>Andamento</option><option value='3'selected>Finalizado</option></select></li>")
	}
}

function cardAddBtnCard(loja,id){
	var name = id.replace("#","")
	var toContainer = $(id)
	toContainer.append("<input type='hidden' name='num_loja' value='"+loja+"'>")
	toContainer.append("<input type='hidden' name='tabela' value='"+name+"'>")
	toContainer.append("<button type='button' class='btn btn-outline-secondary btn-salvar disableValidation' onclick='updateCard(\"frm"+name+"\")'>Salvar</button>") 
}

function updateCard(card){
	Swal.fire({
		icon: 'question',
 	 	title: 'Update Lista',
		text: 'Deseja atualizar esta Lista?',
	  	showDenyButton: true,
	  	confirmButtonText: 'Confirmar',
	  	denyButtonText: 'Calcelar',
		}).then((result) => {
		  if (result.isConfirmed) {
			document.forms[card].action = "./updatecard";
			document.forms[card].submit();
  		}
	})

}

function focusDateOut(desc) {
	var descClass = desc.replaceAll(" ","_")
	var coluna = (document.getElementsByClassName(descClass + "_dataInicio"))
	var comparacao = (document.getElementsByClassName(descClass + "_dataTermino"))
	if (coluna[0].value > comparacao[0].value){
				Swal.fire(
				'Atenção!'	,
				'A data de inicio não pode ser superior a data de término!',
	  			'warning'
	  			)
		document.getElementsByClassName(desc + "_dataInicio")[0].value = ""
		document.getElementsByClassName(desc + "_dataInicio")[0].focus()		
	}
	
}

function setDataInicio(tabela){
	var btnSetDataInicio = document.getElementsByClassName(tabela+"_btnSetDataInicio");
	var setDataInicio = document.getElementsByClassName(tabela+"_setDateInicio");
	
	if (btnSetDataInicio[0].innerHTML == "Definir data de Inicio"){
		for (var i=0; i < setDataInicio.length ; i++){
			desc = setDataInicio[i].name.split("_")
			descClass = desc[0].replaceAll(" ","_")
			var dataTermino = document.getElementsByClassName(descClass+"_dataTermino");
			var data = new Date(dataTermino[0].value);
				data.setDate(data.getDate() - 10);
				data = data.toISOString().slice(0,10);
				setDataInicio[i].value = data;
		}		
	}
	
}

function statusCheck(tabela){
	var btnChecksStatus = document.getElementsByClassName(tabela+"_BtnCheckStatus");
	var status = document.getElementsByClassName(tabela+ "_statusCheck")

	if (btnChecksStatus[0].innerHTML == "Marcar todos"){
		for(var i = 0; i < status.length ; i++){
			status[i].checked = true
			
		}
		btnChecksStatus[0].innerHTML = "Desmarcar todos"
	}else{
		for(var i = 0; i < status.length ; i++){
			status[i].checked = false			
		}
		btnChecksStatus[0].innerHTML = "Marcar todos"
	}
}

function backGround(desc,dataTermino,status){
	var elemento = document.getElementsByClassName(desc+"_BG")
	var dataFIM = new Date(dataTermino);
	dataFIM.setDate(dataFIM.getDate() + 1)
	
	var dataFimMenor = new Date(dataTermino);
	dataFimMenor.setDate(dataFimMenor.getDate() - 7)
	
	var dataAtual = new Date();
	dataAtual.setDate(dataAtual.getDate())
	for (var i = 0; i < elemento.length; i++){	
		if (status == true){
			elemento[i].classList.add("table-success")
		
		}else if (dataAtual.getTime() >= dataFimMenor.getTime() && dataAtual.getTime() <= dataFIM.getTime() && status == false){
			elemento[i].classList.add("table-warning")
			
		}else if (dataAtual.getTime() > dataFIM.getTime() && status == false){
			elemento[i].classList.add("table-danger")	
		
		}else{
			elemento[i].classList.add("table-normal")	
		}	
	}
	
    
}

