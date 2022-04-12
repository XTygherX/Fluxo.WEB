function criandoTabelas(tabela) {
	var novaTabela = tabela.replaceAll("_", " ").charAt(0).toUpperCase() + tabela.replaceAll("_", " ").substr(1);
	novaTabela = novaTabela.replaceAll("cao", "ção")
	novaTabela = novaTabela.replaceAll("coes", "ções")
	novaTabela = novaTabela.replaceAll("Eletrica", "Elétrica")
	var toContainer = $('.tabela > div');
	toContainer.append(
		"<form class='collapse shadow-lg frmTabelas' data-parent='#myGroup' id='frm" + tabela + "' name='frm" + tabela + "'>"
		+ "<h5 class='card-header bg-light text-dark card-footer text-left font-weight-bold'>" + novaTabela
		+ "<div id='btnTabelaControle'>"
		+ "<a type='button' id='rename' onclick='addTarefa(\"" + tabela + "\")'>"
		+ "<i class='bx bx-list-plus'></i>"
		+ "</a>"
		+ "<a type='button' id='rename' onclick='renameControle(\"" + tabela + "\")'>"
		+ "<i class='bx bx-rename'></i>"
		+ "</a>"
		+ "<a type='button' id='del' onclick='delControle(\"" + tabela + "\")'>"
		+ "<i class='bx bxs-trash'></i>"
		+ "</a>"
		+ "</h5>"
		+ "</div>"
		+ "<table class='table table-striped table-hover' id='minhaTabela" + tabela + "' >"
		+ "<thead>"
		+ "<tr>"
		+ "<th scope='col'>Descrição Atual</th>"
		+ "<th scope='col'>Descrição nova </th>"
		+ "<th scope='col'>Dia -</th>"
		+ "<th scope='col'></th>"
		+ "</tr>"
		+ "</thead>"
		+ "<tbody></tbody>"
		+ "</table>"
		+ "<button class='btn btn-outline-secondary btn-lg btnSalvar' type='button' onclick='updateTabela(\"frm" + tabela + "\")'>SALVAR</button>"
		+ "</form>")

	toContainer = $("#frm" + tabela);
	toContainer.append("<input type='hidden' name='tabela' value='" + tabela + "'>")

	toContainer = $('#Controles')
	toContainer.append("<button class='btn btn-outline-secondary btnControles' type='button' data-toggle='collapse' data-target='#frm" + tabela + "' aria-expanded='false' aria-controls='#frm" + tabela + "'>" + novaTabela + "</button>")

}

function loopInserirValoresSelect(desc, dMenos, tabela) {
	descClass = desc.replaceAll(" ", "_")
	var toContainer = $('#frm' + tabela + '> table > tbody');
	toContainer.append("<tr>"
		+ "<td class='rowDescAtual'>"
		+ "<input id='" + desc + "_descAtual' name='" + desc + "_descAtual' type='hidden' value='" + desc.replaceAll("_", " ") + "' >"
		+ "<span class='input-group-text'>" + desc.replaceAll("_", " ").charAt(0).toUpperCase() + desc.replaceAll("_", " ").toLowerCase().substr(1) + "</span>"
		+ "</td>"
		+ "<td class='rowDescNova'>"
		+ "<input onfocusout='focusDateOut(\"" + desc + "\")' name='" + desc + "_descNova' type='text' class='form-control " + descClass + "_descNova inputDescNova disableValidation'>"
		+ "</td>"
		+ "<td class='rowdMenos'>"
		+ "<input  name='" + desc + "_dMenos' type='text' class='form-control " + descClass + "_dMenos disableValidation dMenos' value='" + dMenos + "'>"
		+ "</td>"
		+ "<td class='rowDel'>"
		+ "<a type='button' id='del' onclick='delTarefa(\"" + tabela +"\",\""+ desc +"\")'>"
		+ "<i class='bx bxs-trash'></i>"
		+ "</a>"
		+ "</td>"
		+ "</tr>")
}

function updateTabela(tabela) {
	document.forms[tabela].action = "./updateTabelaControles";
	document.forms[tabela].submit();
}

function focusDateOut(desc) {
	var cont = 0;
	var contIndex = 0
	descClass = desc.replaceAll(" ", "_")
	var descNova = document.getElementsByClassName(descClass + "_descNova")[0].value
	var validacaodescAtual = document.getElementById(desc + "_descAtual")
	var validacaoDescNova = document.getElementsByClassName("inputDescNova");
	console.log(validacaodescAtual)
	if (descNova.length > 0) {
		if (validacaodescAtual.value == descNova) {
			Swal.fire({
				icon: 'error',
				title: 'Ja existe uma descrição com este nome!',
			})
		} else if (validacaoDescNova.length != 0) {
			for (var i = 0; i < validacaoDescNova.length; i++) {
				if (validacaoDescNova[i].value == descNova) {
					cont++
					if (cont >= 2) {
						contIndex = i;
						break;


					}
				}
			}
		}
	}
	if (cont > 1) {

		document.getElementsByClassName("inputDescNova")[contIndex].focus()
		Swal.fire({
			icon: 'error',
			title: 'Esta descrição nova ja foi utilizada!',
		})
	}
}

function addTarefa(controle) {
	Swal.fire({
		title: 'Deseja adicionar uma nova Tarefa?',
		input: 'text',
		inputLabel: 'Informe o titulo do Tarefa',
		icon: 'question',
		showCancelButton: true,
		inputValidator: (value) => {
			if (value != undefined && value != "") {
				var tabela = document.getElementById("frm" + controle).getElementsByTagName("td");
				var validacao = false;
				if (tabela.length > 0){
					for (var j = 0; j < tabela.length; j++) {
						if (tabela[j].innerText == value) {
							validacao = false
							break
						} else if (tabela[j].innerText != value) {
							validacao = true
						}
					}
				}else{
					validacao = true
				}
				if (validacao == true) {
					var request = new XMLHttpRequest();
					request.onreadystatechange = function() {
						if (request.readyState == 4 && request.status == 200) {
							if (request.responseText == "true") {
								loopInserirValoresSelect(value,"120",controle);
							} else {
								Swal.fire('Ocorreu um Erro. Contate o administrador!')
							}
						}
					}
					request.open("GET", "adminControles?fun=addTarefa&controle=" + controle+"&tarefa="+value)
					request.send();
					
				} else {
					Swal.fire('Este titulo ja existe!')
				}
				
			}
		}
	})


}

function addControle() {
	Swal.fire({
		title: 'Deseja adicionar um novo controle?',
		input: 'text',
		inputLabel: 'Informe o titulo do controle',
		icon: 'question',
		showCancelButton: true,
		inputValidator: (value) => {
			if (value != undefined && value != "") {
				var validacao = true;
				var btnControles = document.getElementsByClassName("btnControles");
				for (var cont = 0; cont < btnControles.length; cont++) {
					if (value == btnControles[cont].innerText) {
						validacao = false
						break;
					}
				}
				if (validacao == true) {
					var request = new XMLHttpRequest();
					request.onreadystatechange = function() {
						if (request.readyState == 4 && request.status == 200) {
							if (request.responseText == "true") {
								Swal.fire({
									title: 'Criado!',
									text: 'É necessario atualizar a pagina',
									icon: 'success',
									confirmButtonText: 'OK, atualizar'
								}).then((result) => {
									if (result.isConfirmed) {
										location.reload()
									}
								})
							} else {
								Swal.fire('Ocorreu um Erro. Contate o administrador!')
							}
						}
					}
					request.open("GET", "adminControles?fun=addControle&controle=" + value)
					request.send();
				} else {
					Swal.fire(
						"Alerta",
						'Já existe um controle com esta descrição',
						'warning'
					)
				}
			}
		}
	})
}

function delControle(controle) {
	Swal.fire({
		title: 'Você esta prestes a Excluir um controle!',
		text: "Isto não é reversivel",
		icon: 'question',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Sim, delete!'
	}).then((result) => {
		if (result.isConfirmed) {
			Swal.fire({
				title: "Deseja Continuar?!",
				text: 'Todas atividades relacionadas a este controle serão excluidas!',
				icon: 'warning',
				confirmButtonText: 'Sim, delete!'
			}).then((result) => {
				if (result.isConfirmed) {
					var request = new XMLHttpRequest();
					request.onreadystatechange = function() {
						if (request.readyState == 4 && request.status == 200) {
							if (request.responseText == "true") {
								Swal.fire({
									title: 'Deletedado!',
									text: 'Necessario atualizar a pagina',
									icon: 'success',
									confirmButtonText: 'OK! atualizar!'
								}).then((result) => {
									if (result.isConfirmed) {
										location.reload()
									}
								})

							} else {
								Swal.fire('Ocorreu um Erro. Contate o administrador!')
							}
						}
					}
					request.open("GET", "adminControles?fun=delControle&controle=" + controle)
					request.send();


				}
			})
		}
	})


}

function selecionarControle(mEvent) {
	console.log(mEvent)
	var elemento = null;
	var linhaControle = null;
	linhaControle = document.getElementById("tabelaControles").getElementsByTagName("td")
	elemento = mEvent.srcElement || mEvent.document.getElementById("tabelaControles").getElementsByTagName("td")
	console.log(elemento)
	if (elemento != undefined) {
		for (var i = 0; i < linhaControle.length; i++) {
			linhaControle[i].classList.remove("selecionado")
		}
		elemento.classList.add("selecionado")
	}
}

function renameControle(controle) {
	Swal.fire({
		title: 'Você esta prestes a renomear um controle!',
		input: 'text',
		inputLabel: 'Informe o titulo do controle',
		icon: 'question',
		showCancelButton: true,
		inputValidator: (value) => {
			if (value != undefined && value != "") {
				var request = new XMLHttpRequest();
				request.onreadystatechange = function() {
					if (request.readyState == 4 && request.status == 200) {
						if (request.responseText == "true") {
							Swal.fire({
								title: 'Renomeado!',
								text: 'Necessario atualizar a pagina',
								icon: 'success',
								confirmButtonText: 'OK, atualizar!'
							}).then((result) => {
								if (result.isConfirmed) {
									location.reload()
								}
							})

						} else {
							Swal.fire('Ocorreu um Erro. Contate o administrador!')
						}
					}

				}
				request.open("GET", "adminControles?fun=renControle&controle="+controle+"&novocontrole="+value)
				request.send();
			} else {
				Swal.fire(
					"Alerta",
					'Já existe um controle com esta descrição',
					'warning'
				)
			}
		}
	})
}

function delTarefa(controle,tarefa) {
	Swal.fire({
		title: 'Você esta prestes a Excluir uma tarefa!',
		text: "Isto não é reversivel",
		icon: 'question',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Sim, delete!'
	}).then((result) => {
		if (result.isConfirmed) {
			Swal.fire({
				title: "Deseja Continuar?!",
				text: 'Esta tarefa sera excluida de todas DashBoards!',
				icon: 'warning',
				confirmButtonText: 'Sim, delete!'
			}).then((result) => {
				if (result.isConfirmed) {
					var request = new XMLHttpRequest();
					request.onreadystatechange = function() {
						if (request.readyState == 4 && request.status == 200) {
							if (request.responseText == "true") {
								var linha = document.getElementById(tarefa.toLowerCase()+"_descAtual").parentElement.parentElement.rowIndex
								document.getElementById("minhaTabela"+controle.toUpperCase()).deleteRow(linha);								
							} else {
								Swal.fire('Ocorreu um Erro. Contate o administrador!')
							}
						}
					}
					request.open("GET", "adminControles?fun=delTarefa&controle=" + controle +"&tarefa="+tarefa)
					request.send();


				}
			})
		}
	})


}
