function focusDateOut(desc) {
	var dataInicio = (document.getElementById(desc))
	var dataComparacao = (document.getElementById("inauguracao"))
	if (dataInicio.value > dataComparacao.value){
		dataInicio.value = ""
		dataInicio.focus()
		Swal.fire(
			'Atenção!'	,
			'A data de inicio não pode ser superior a data de término!',
			'warning'
		)	
	}
	
}

function disableLoja(bool){
	document.getElementById("nomeLoja").disabled = bool;
	document.getElementById("entradaTi").disabled = bool;
	document.getElementById("inauguracao").disabled = bool;
	document.getElementById("responsavel").disabled = bool;
	document.getElementById("statusLoja").disabled = bool;
	document.getElementById("visivel").disabled = bool;
	document.getElementById("addLoja").disabled = bool;
	if ( bool == true){
		document.getElementById('nomeLoja').value = "";
		document.getElementById('entradaTi').value = "";
		document.getElementById('inauguracao').value = "";
		document.getElementById('responsavel').value = "";
		checkFalseLoja();
	}
}
	
function checkFalseLoja(){
	document.getElementById('statusLoja').checked = false;
	document.getElementById('visivel').checked = false;
}

function checkFalse(){
	document.getElementById('userVisualizacao').checked = false;
	document.getElementById('userEdicao').checked = false;
	document.getElementById('userAdicao').checked = false;
	document.getElementById('userAdicaoUser').checked = false;
	document.getElementById('userAtivo').checked = false;
}

function disableUser(bool){
	document.getElementById('userVisualizacao').disabled = bool;
	document.getElementById('userEdicao').disabled = bool;
	document.getElementById('userAdicao').disabled = bool;
	document.getElementById('userAdicaoUser').disabled = bool;
	document.getElementById('userAtivo').disabled = bool;
	document.getElementById('btnADD').disabled = bool;
	document.getElementById('btnRedefinir').disabled = bool;
	
}

function criarUsuarioSubmit(criarUsuario){
	if (document.getElementById('btnADD').innerText == "Adicionar"){
		if (criarUsuario == true) {
			Swal.fire({
				icon: 'question',
				title: "Você esta prestes a criar um novo usuario, deseja continuar?",
				text: 'deseja continuar?',			  
				showDenyButton: true,
				showCancelButton: false,
				confirmButtonText: 'Confirmar',
				denyButtonText: 'Calcelar'
			}).then((result) => {
			  	if (result.isConfirmed) {
					document.forms["frmUsuario"].action = "./criarUsuario";
					document.forms["frmUsuario"].submit();	
				}
			})
		} else {
			alert("USUÁRIO JA EXISTE!")
		}
	}else if (document.getElementById('btnADD').innerText == "UPDATE"){
		Swal.fire({
			icon: 'question',
			  title: "Você esta prestes a atualizar um usuario!",
				text: 'deseja continuar?',
			  showDenyButton: true,
			  showCancelButton: false,
			  confirmButtonText: 'Confirmar',
			  denyButtonText: 'Calcelar'
			}).then((result) => {
			  	if (result.isConfirmed) {
					document.forms["frmUsuario"].action = "./updateUser";
					document.forms["frmUsuario"].submit();		
				}
			})

	}
}

	
