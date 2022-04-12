function selecionarLinha(mEvent){
	var elemento = null;
	var desmarcarLinhas = null;
	desmarcarLinhas = document.getElementById('tabelaTitulos').getElementsByTagName("td");
	elemento = mEvent.srcElement || mEvent.document.getElementById('tabelaTitulos').getElementsByTagName("td")

	if (elemento != undefined){	
		for (var i = 0; i < desmarcarLinhas.length; i++){
			desmarcarLinhas[i].classList.remove("selecionado");
		}
		
		var linhas = (mEvent.srcElement || mEvent.document.getElementById('tabelaTitulos').getElementsByTagName("td"))
		linhas.classList.add("selecionado");		
	}
}	

function btnAddPDF(desc){
	
	var descClass = desc.replaceAll(" ","_");
	var linhaSelecionada = ""	
	var texto = "";
	
	linhaSelecionada = document.getElementById('tabelaTitulos').getElementsByClassName("selecionado");
	linhaValidacao = document.getElementById(descClass+"_sub")
	
	if (linhaSelecionada.length != 0 && linhaValidacao == null ){
		
		if (document.getElementById("descricao").checked){
			
			texto = texto + " - " + document.getElementsByClassName(descClass+"_descricao")[0].outerText
		}
		
		if (document.getElementById("dataInicio").checked){
			var txt = document.getElementsByClassName(descClass+"_dataInicio")[0].value;
			if (txt != ""){
				texto = texto + " - " + txt
			}
			
		}
		
		if (document.getElementById("dataTermino").checked){		
			var txt = document.getElementsByClassName(descClass+"_dataTermino")[0].value
			if (txt != ""){
				texto = texto + " - " + txt
			}
	
		}
		
		if (document.getElementById("status").checked){
			texto = texto + " - " + document.getElementsByClassName(descClass+"_statusCheck")[0].value
		}	
		if (document.getElementById("observacoes").checked){
			var txt = document.getElementsByClassName(descClass+"_observacoe")[0].value
			if (txt != ""){
				texto = texto + " - " + txt
			}
		}
	
		if (texto != ""){
			
			backGroundAdd(desc)
			titulo = linhaSelecionada[0].outerText
			titulo = titulo.replace(" ","_")
			var corpo = document.getElementById(titulo+"_titulo");
			corpo.insertAdjacentHTML("beforeend",
				"<p class='font-weight-light dadosSub' id='"+desc.replaceAll(" ","_")+"_sub'>"
				+"<i class='bx bx-chevron-right bx-tada align-middle '></i>"
				+texto
				+"</p>")
		}
	}
}

function addInputTabela(){
	var validacao = false;
	var elemento = document.getElementById("inputTitulos")
	var listaTitulos = document.getElementById("listaTitulos").getElementsByTagName("option")
	for ( var j = 0 ; j < listaTitulos.length; j++){
		if (listaTitulos[j].value != elemento.value && elemento.value != ""){
			validacao = true;
			
		}else{
			validacao = false;
		}
	}
	
	if (validacao == true){
		var tbody = document.getElementById("tabelaTitulos").getElementsByTagName('tbody')[0];
		var novaLinha = tbody.insertRow();
		var novaCelula = novaLinha.insertCell();
		var texto = document.createTextNode(elemento.value);
		novaCelula.appendChild(texto)
		
		var toContainer = $('#listaTitulos');
		toContainer.append("<option>"+elemento.value+"</option>")

		var corpo = document.getElementById("corpo");
		corpo.insertAdjacentHTML("beforeend",
			"<p class='font-weight-bold dadoTitulos' id='"+elemento.value.replaceAll(" ","_")+"_titulo'>"
			+"<i class='bx bx-chevrons-right bx-tada align-middle '></i>"
			+elemento.value
			+"</p>")
	}else if (elemento.value == ""){
		alert("Informe um titulo!")
	}else{
		alert("Este titulo ja foi adicionado!")
	}


	elemento.value = "";
}

function btnDelPDF(desc){
	descClass = desc.replaceAll(" ","_");
	linha = document.getElementById(descClass+"_sub");
	if(linha != null){
		linha.remove();
	}
	backGroundDel(desc)
}

function backGroundAdd(desc){
	descClass = desc.replaceAll(" ","_")
	var elemento = document.getElementsByClassName(descClass+"_descricao")[0]
	elemento.classList.add("table-success")
	var elemento = document.getElementsByClassName(descClass+"_statusTable")[0]
	console.log(elemento)
	elemento.classList.add("table-success")
}

function backGroundDel(desc){
	descClass = desc.replaceAll(" ","_")
	var elemento = document.getElementsByClassName(descClass+"_descricao")[0]
	
	elemento.classList.remove("table-success")
	var elemento = document.getElementsByClassName(descClass+"_statusTable")[0]
	console.log(elemento)
	elemento.classList.remove("table-success")
}

function delInputTabela(){
	var elemento = document.getElementById("inputTitulos")
	var tbody = document.getElementById("tabelaTitulos").getElementsByTagName('td');
	
	for(var i = 0; i < tbody.length ; i++){
		if(tbody[i].innerText == elemento.value){
			tbody[i].remove()
			break;
		}	
	}
	
	var listaTitulos = document.getElementById("listaTitulos").getElementsByTagName("option")
	for ( var j = 0 ; j < listaTitulos.length; j++){
		if (listaTitulos[j].value == elemento.value){
			listaTitulos[j].remove();
			break;
		}
	}
	
	var corpo = document.getElementById(elemento.value+"_titulo");
	corpo.remove();

	elemento.value = "";
	
	//var toContainer = $('#listaTitulos');
	
	//toContainer.remove("<option>"+elemento.value+"</option>")
	 
}


function criandoTabelas(tabela){
	var novaTabela = tabela.replaceAll("_"," ").charAt(0).toUpperCase() + tabela.replaceAll("_"," ").substr(1);
	novaTabela = novaTabela.replaceAll("cao","ção")
	novaTabela = novaTabela.replaceAll("coes","ções")
	novaTabela = novaTabela.replaceAll("Eletrica","Elétrica")
	var toContainer = $('.tabela');
	toContainer.append(
		"<h5 class='card-header text-capitalize bg-light text-dark card-footer text-left font-weight-bold dropdown-toggle' data-toggle='collapse' data-target='#frm"+tabela+"' aria-expanded='true' aria-controls='collapseOne'>"+novaTabela+"</h5>"
    		+"<div class='collapse' id='frm"+tabela+"'>"
    		+"<table class='table table-striped table-hover' >"
   				+"<thead>"
		    		+"<tr >	"			
						+"<th scope='col'>Descricão</th>"
						+"<th scope='col'>Check</th>"
		    		+"</tr>"
	    		+"</thead>"
	    		+"<tbody>"
				+"</tbody>"
	    	+"</table>"
		    +"</div>")
	

}

function loopInserirValoresSelect(desc,dataInicio,dataTermino,status,observacoes,tabela){
	descClass = desc.replaceAll(" ","_")
	var toContainer = $("#frm"+ tabela +"> table > tbody");
	toContainer.append(
		"<tr>"
			+"<th scope='row' class='"+descClass+"_descricao'>"+desc.replaceAll("_"," ").charAt(0).toUpperCase() + desc.replaceAll("_"," ").toLowerCase().substr(1)+"</th>"
			+"<th scope='row' class='row botoesTabela "+descClass+"_statusTable  text-center'><button onclick='btnAddPDF(\""+desc+"\")' class='align-middle btn btn-outline-secondary btn-sm' type='button'>ADD</button> <button onclick='btnDelPDF(\""+desc+"\")' class='align-middle btn btn-outline-secondary btn-sm' type='button'>DEL</button></th>")
		
	    if (dataInicio != "null"){
		    toContainer.append("<input type='hidden' class='"+descClass+"_dataInicio' value="+ dataInicio +"></th> ")
	    }else{
		    toContainer.append("<input type='hidden' class='"+descClass+"_dataInicio'>")
		}
		if (dataTermino != "null" ){
	    	toContainer.append("<input type='hidden' class='"+descClass+"_dataTermino' value="+ dataTermino +"></th> ")
	    }else{
		    toContainer.append("<input type='hidden' class='"+descClass+"_dataTermino'>")
		}
		
		if ( status == true){
			toContainer.append("<input type='hidden' class='"+descClass+"_statusCheck' value='Finalizado'>")
		}else{
			toContainer.append("<input type='hidden' class='"+descClass+"_statusCheck' value='Em andamento'>")
		}
		
		if (observacoes == null || observacoes == "null"){
			toContainer.append("<input type='hidden' class='"+descClass+"_observacoe' value='"+observacoes+"'>")
	    }else{
	
			toContainer.append("<input type='hidden' class='"+descClass+"_observacoe' value='"+observacoes+"'>")
			
		}
			
		toContainer.append("</tr>")	


}

function selectLoja(value){
	window.location.href ="./relatorio?loja="+value;

}


















