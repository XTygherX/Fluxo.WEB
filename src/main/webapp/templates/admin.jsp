<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="model.JavaBeansUser"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.JavaBeansLoja"%>
<%@ include file="templatesInclude/validarUser.jsp"%>
<%
	ArrayList<JavaBeansUser> lista = (ArrayList<JavaBeansUser>) request.getAttribute("selectUsuario");
	ArrayList<JavaBeansLoja> listaLojas = (ArrayList<JavaBeansLoja>) request.getAttribute("selectLojas");


%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<%@ include file="templatesInclude/scriptEimportsTop.jsp"%>
<link rel="stylesheet" href="css/admin.css">
<script type="text/javascript" src="js/admin.js"></script>

<title>Fluxo TI - Gerenciar</title>

</head>
<body>
	<!-- Menu lateral -->
	<div id="menulateral">
		<%@ include file="templatesInclude/menuLateral.jsp"%>
	</div>
	<!-- FORM PARA INSERSÃO -->
	<main>
		<div class="admin">
			<!-- FORM PARA INSERIR LOJA NOVA -->
			<div class="dados-loja">
				<form class="card" id="formulario-add-loja" id="frmLoja"
					name="frmLoja">
					<h5 class="card-header text-white bg-secondary action="">Nova
						Loja</h5>
					<div class="form-row">
						<div class="col-md-4 mb-3">
							<label for="numLoja">Numero da Loja</label> <input
								oninput="disableLoja(true)" type="text" list="listaLojas"
								name='numLoja' class="form-control" id="numLoja"
								placeholder="Numero Da Loja" required>
							<datalist id="listaLojas">
								<%for (int i = 0; i < listaLojas.size(); i++) {%>
								<option><%=listaLojas.get(i).getNumLoja()%></option>
								<%}%>
							</datalist>
						</div>
						<button class="btn btn-outline-secondary btn-lg" type="button"
							onclick='pesquisarLoja()'>
							<h8>Pesquisar</h8>
						</button>
						<div class="col-md-4 mb-3">
							<label for="nome-loja">Nome da Loja</label> <input type="text"
								class="form-control" id="nomeLoja" name="nomeLoja"
								placeholder="Nome da Loja" required>
						</div>
						<div class="col-md-4 mb-3">
							<label for="entrada-ti">Data Entrada TI na Obra</label> <input
								onfocusout="focusDateOut('entradaTi')" type="date"
								class="form-control" id="entradaTi" name="entradaTi" required>
						</div>
						<div class="col-md-4 mb-3">
							<label for="inauguracao">Data Inaguração</label> <input
								onfocusout="focusDateOut('entradaTi')" type="date"
								class="form-control" id="inauguracao" name="inauguracao"
								required>
						</div>
						<div class="col-md-4 mb-3">
							<label for="responsavel">Responsavel TI</label> <select
								id="responsavel" class="custom-select" name="responsavel"
								required>
								<option value=""></option>
								<%for (int i = 0; i < lista.size(); i++) {%>
								<option><%=lista.get(i).getNome()%></option>
								<%}%>
							</select>
						</div>
					</div>

					<div class="form-group form-row " value="Selecione">
						<div class="form-check align-baseline checkbox">
							<input class="form-check-input" type="checkbox" id="statusLoja"
								name="statusLoja"> <label class="form-check-label"
								for="status-loja"> Loja inaugurada </label>
						</div>
						<div class="form-check align-baseline checkbox">
							<input class="form-check-input" type="checkbox" id="visivel"
								name="visivel"> <label class="form-check-label"
								for="visivel"> Visivel no Dashboard </label>
						</div>
					</div>
					<button id="addLoja" class="btn btn-outline-secondary btn-lg"
						type="button" onclick="criarLoja()">
						<h4>Adicionar</h4>
					</button>
				</form>
			</div>
			<!-- ALTERAR NUMERO DA LOJA -->
			<div class="numeroLoja">
				<form class="card" id="frmNumLoja" name="frmNumLoja">
					<h5 class="card-header text-white bg-secondary" action="">Alterar
						Numero Loja</h5>
					<div class="form-row">
						<div class="col-md-4 mb-3">
							<label for="numAntigoLoja">Atual numero da Loja</label> <select
								id="numAntigoLoja" class="custom-select disableValidation"
								name="numAntigoLoja" placeholder="Atual Numero Da Loja" required>
								<option value=""></option>
								<%for (int i = 0; i < listaLojas.size(); i++) {%>
								<option><%=listaLojas.get(i).getNumLoja()%></option>
								<%}%>
							</select>
						</div>
						<div class="col-md-4 mb-3">
							<label for="novoNumLoja">Novo numero da Loja</label> <input
								type="text" name="novoNumLoja"
								class="form-control disableValidation" id="novoNumLoja"
								placeholder="Novo Numero Da Loja" required>
						</div>
						<button class="btn btn-outline-secondary btn-lg disableValidation"
							type="button" onclick='alterarNumLoja()'>
							<h8>Update</h8>
						</button>
					</div>
				</form>
			</div>
			<!--FORM PARA INSERIR NOVO USUARIO-->
			<div class="dados-usuario">
				<form class="card" name="frmUsuario" id="formulario-add-usuario"
					action="">
					<h5 class="card-header text-white bg-secondary ">Adicionar
						usuário</h5>
					<div class="form-row">
						<div class="col-md-4 mb-3">
							<label for="usuario">Usuário</label> <input
								oninput="disableUser(true)" type="text" list="listaUsuarios"
								name='nome' class="form-control" id="usuario"
								placeholder="Usuário" required>
							<datalist id="listaUsuarios">
								<%for (int i = 0; i < lista.size(); i++) {%>
								<option><%=lista.get(i).getNome()%></option>
								<%}%>
							</datalist>
						</div>
						<button class="btn btn-outline-secondary btn-lg" type="button"
							onclick='pesquisarUsario()'>
							<h7>Pesquisar</h7>
						</button>
					</div>

					<div class="form-group form-row card" value="Selecione">
						<h5 class="card-header text-white bg-secondary ">Permissões</h5>
						<div class="form-check align-baseline checkbox">
							<input class="form-check-input" name="userVisualizacao"
								type="checkbox" id="userVisualizacao"> <label
								class="form-check-label" for="userVisualizacao">
								Visualização dos DashBoard </label>
						</div>
						<div class="form-check align-baseline checkbox">
							<input class="form-check-input" name="userEdicao" type="checkbox"
								id="userEdicao"> <label class="form-check-label"
								for="userEdicao"> Edição dos DashBoard </label>
						</div>
						<div class="form-check align-baseline checkbox">
							<input class="form-check-input" name="userAdicao" type="checkbox"
								id="userAdicao"> <label class="form-check-label"
								for="userAdicao"> Adição de DashBoard </label>
						</div>
						<div class="form-check align-baseline checkbox">
							<input class="form-check-input" name="userAdicaoUser"
								type="checkbox" id="userAdicaoUser"> <label
								class="form-check-label" for="userAdicaoUser"> Adição de
								Usuários </label>
						</div>
						<div class="form-check align-baseline checkbox">
							<input class="form-check-input" name="userAtivo" type="checkbox"
								id="userAtivo"> <label class="form-check-label"
								for="userAtivo"> Usuario Ativo </label>
						</div>
						<button id="btnRedefinir" class="btn btn-outline-secondary btn-sw"
							type="button" onclick="redefinirSenha()">
							<h4>Redefinir senha</h4>
						</button>
					</div>
					<button id="btnADD" class="btn btn-outline-secondary btn-lg"
						type="button" onclick="criarUsuario()">
						<h4>Adicionar</h4>
					</button>
				</form>
			</div>
		</div>
	</main>

	<%@ include file="templatesInclude/scriptsFimBody.jsp"%>
	<script type="text/javascript">
	onload = function(){
		var query = location.search.slice(1);
		var partes = query.split('&');
		var data = {};
		partes.forEach(function (parte) {
		    var chaveValor = parte.split('=');
		    var chave = chaveValor[0];
		    var valor = chaveValor[1];
	    	if (valor == "UL") {
	    		Swal.fire(
   				  	'Sucesso!',
    				'LOJA ATUALIZADO COM SUCESSO!',
    				'success'
				)	    		
	  		}else if (valor == "EL"){
		  			Swal.fire(
					'Atenção!'	,
		  			'OCORREU UM ERRO AO ADICIONAR/ATUALIZAR A LOJA!',
		  			'error'
		  			)
	  		}else if (valor == "AL"){
	    		Swal.fire(
	    				'Sucesso!',
	    				'LOJA ADICIONADA COM SUCESSO!',
	    				'success'
	    				)
	  		}else if (valor == "UU") {
	  			Swal.fire(
	  					'Sucesso!',
	  					'USUÁRIO ATUALIZADO COM SUCESSO!',
	    				'success'
	    				)

	  		}else if (valor == "EU"){
	  			Swal.fire(
						'Atenção!'	,
						'OCORREU UM ERRO AO ADICIONAR/ATUALIZAR O USUÁRIO!',
			  			'warning'
			  			)

	  		}else if (valor == "AU"){
	  			Swal.fire(
	  					'Sucesso!',
	  					'USUÁRIO ADICONADO COM SUCESSO!',
	    				'success'
	    				)
	  		}else if (valor == "ES"){
	  			Swal.fire(
						'Atenção!'	,
						'OCORREU UM ERRO AO REDEFINIR A SENHA DO SUÁRIO!',
			  			'warning'
			  			)
	  		}else if (valor == "US"){
	  			Swal.fire(
	  					'Sucesso!',
	  					'SENHA REDEFINIDA COM SUCESSO'
	  					+'NOVA SENHA: Assai_Fluxo',
	    				'success'
	    				)
	  		}else if (valor == "permisaoUser"){
	  			Swal.fire(
						'Atenção!'	,
						'Você não tem permissao para editar usuários',
			  			'warning'
			  			)
	  		}
		});
		
		disableUser(true);
		disableLoja(true);
		
	}
	
	function pesquisarUsario(){
		checkFalse(true);
		disableUser(true)
		let condicaoAlerta = true;
		
		var nome = document.getElementById('usuario').value;
		if (nome != ""){
			<%for (int i = 0; i < lista.size(); i++) {%>
				if ( nome == "<%=lista.get(i).getNome().toString()%>" ) {
					document.getElementById('userVisualizacao').checked = <%=lista.get(i).getVisualizacao()%>;
					document.getElementById('userEdicao').checked = <%=lista.get(i).getEdicao()%>;
					document.getElementById('userAdicao').checked = <%=lista.get(i).getAdicao()%>;
					document.getElementById('userAdicaoUser').checked = <%=lista.get(i).getAdicaoUsuario()%>;
					document.getElementById('userAtivo').checked = <%=lista.get(i).getAtivo()%>;
					<%if ((Boolean) request.getSession().getAttribute("adicaoUser") == true){ %>
						document.getElementById('btnADD').innerText = "UPDATE";	
						disableUser(false);
						condicaoAlerta = false;
					<%}%>
				}
			<%}%>
			<%if ((Boolean) request.getSession().getAttribute("adicaoUser") == true){ %>
				if (condicaoAlerta == true){
					Swal.fire({
						icon: 'question',
						  title: 'Usuario não encontrado',
							text: 'Deseja adicionar este Usuário?',
						  showDenyButton: true,
						  confirmButtonText: 'Confirmar',
						  denyButtonText: 'Calcelar',
						}).then((result) => {
						  if (result.isConfirmed) {
								document.getElementById('btnADD').innerText = "Adicionar"
								disableUser(false);
								document.getElementById('btnRedefinir').disabled = true;
						  }
					})
					}
			<%}%>
		}else{
			disableUser(true);
		}
	}


	
	
	function criarUsuario(){
		<%if ((Boolean) request.getSession().getAttribute("adicaoUser") == true){ %>
			let nome = document.forms["frmUsuario"]['usuario'].value;
			let criarUsuario = true;
			<%for (int i = 0; i < lista.size(); i++) {%>
				if ( nome == "<%=lista.get(i).getNome().toString()%>") {
					criarUsuario = false;
				}
			<%}%>
			criarUsuarioSubmit(criarUsuario)
		<%} else {%>
		Swal.fire(
				'Atenção!'	,
				'Voce não tem permisão para Criar Usuário',
	  			'warning'
	  			)
		<%}%>
	}
	
	function pesquisarLoja(){	
		checkFalseLoja();
		disableLoja(true);
		let condicaoAlerta = true;
		var numLoja = document.getElementById('numLoja').value;
		
		if (numLoja != ""){
			<%for (int i = 0; i < listaLojas.size(); i++) {%>
				if ( numLoja == "<%=listaLojas.get(i).getNumLoja().toString()%>") {
					console.log(document.getElementById('nomeLoja'))
					document.getElementById('nomeLoja').value = "<%=listaLojas.get(i).getNomeLoja()%>";
					document.getElementById('entradaTi').value = "<%=listaLojas.get(i).getDataEntradaTi()%>";
					document.getElementById('inauguracao').value = "<%=listaLojas.get(i).getDataInaguracao()%>";
					document.getElementById('responsavel').value = "<%=listaLojas.get(i).getResponsavel()%>";
					document.getElementById('statusLoja').checked = <%=listaLojas.get(i).getStatus()%>;
					document.getElementById('visivel').checked = <%=listaLojas.get(i).getVisualizacao()%>;
					<%if ((Boolean) request.getSession().getAttribute("adicao") == true){ %>
						document.getElementById('addLoja').innerText = "UPDATE";
						condicaoAlerta = false;
						disableLoja(false)
					<%}%>
				}
			<%}%>
			<%if ((Boolean) request.getSession().getAttribute("adicao") == true){ %>
				if (condicaoAlerta == true){
					Swal.fire({
						icon: 'question',
						  title: 'Loja não encontrado',
							text: 'Deseja adicionar esta Loja?',
						  showDenyButton: true,
						  showCancelButton: false,
						  confirmButtonText: 'Confirmar',
						  denyButtonText: 'Calcelar',
						}).then((result) => {
						  if (result.isConfirmed) {
						  	document.getElementById('addLoja').innerText = "Adicionar"
							disableLoja(false);
						  }
						})
					

				}
			<%}%>
		}else{
			disableLoja(true);
		}
	}
	
	function alterarNumLoja(){
		<%if ((Boolean) request.getSession().getAttribute("adicao") == true){ %>
			var numLoja = document.getElementById('novoNumLoja').value;
			var liberacao = false;
			<%for (int i = 0; i < listaLojas.size(); i++) {%>
				if ( numLoja == "<%=listaLojas.get(i).getNumLoja().toString()%>" ) {
					liberacao = false;
					Swal.fire(
							'Atenção!'	,
							'Ja existe uma loja com este numero!',
				  			'warning'
				  			)
				}else if (document.forms["frmNumLoja"]["numAntigoLoja"].value == "" || document.forms["frmNumLoja"]["novoNumLoja"].value == ""){
					liberacao = false
					Swal.fire(
							'Atenção!'	,
							'O campos de data não podem estar vazios!',
				  			'warning'
				  			)
					i == <%=listaLojas.size()%>
				}else{
					liberacao = true
				}
			<%}%>
			if (liberacao == true){
				Swal.fire({
					icon: 'question',
					  title: 'Você esta prestes a atualizar uma Loja',
						text: 'deseja continuar?',
					  showDenyButton: true,
					  showCancelButton: false,
					  confirmButtonText: 'Confirmar',
					  denyButtonText: 'Calcelar',
					}).then((result) => {
					  	if (result.isConfirmed) {
						  	document.forms["frmNumLoja"].action = "./updateNumLoja";
							document.forms["frmNumLoja"].submit();	
						}
					})
			}
		<%}else{%>
			Swal.fire(
					'Atenção!'	,
					'Você não tem permissao para adicionar ou atualizar',
		  			'warning'
		  			)

		<%}%>
		
	}
	
	function criarLoja(){
		<%if ((Boolean) request.getSession().getAttribute("adicao") == true){ %>
			let numLoja = document.forms["frmLoja"]["numLoja"].value;
			let criarLoja = true;
			<%for (int i = 0; i < listaLojas.size(); i++) {%>
				if ( numLoja == "<%=listaLojas.get(i).getNumLoja().toString()%>") {
					criarLoja = false;
				}
			<%}%>
			if (document.getElementById('addLoja').innerText == "Adicionar"){
				if (criarLoja == true) {
					if (document.forms["frmLoja"]["entradaTi"].value == "" || document.forms["frmLoja"]["inauguracao"].value == ""){
						Swal.fire(
								'Atenção!'	,
								'O campos de data não podem estar vazios!',
					  			'warning'
					  			)
					}else{
						Swal.fire({
							icon: 'question',
							  title: 'Você esta prestes a criar uma nova Loja',
								text: 'deseja continuar?',
							  showDenyButton: true,
							  showCancelButton: false,
							  confirmButtonText: 'Confirmar',
							  denyButtonText: 'Calcelar',
							}).then((result) => {
							  	if (result.isConfirmed) {
								  	document.forms["frmLoja"].action = "./novaLoja";
									document.forms["frmLoja"].submit();
								}
							})
					}
				} else {
					Swal.fire(
							'Atenção!'	,
							'Já existe uma loja com esses dados',
				  			'warning'
				  			)

				}
			}else if (document.getElementById('addLoja').innerText == "UPDATE"){
				if (document.forms["frmLoja"]["entradaTi"].value == "" || document.forms["frmLoja"]["inauguracao"].value == ""){
					Swal.fire(
							'Atenção!'	,
							'O campos de data não podem estar vazios!',
				  			'warning'
				  			)
				}else{
					Swal.fire({
						icon: 'question',
						  title: 'Você esta prestes a atualizar uma Loja',
							text: 'deseja continuar?',
						  showDenyButton: true,
						  showCancelButton: false,
						  confirmButtonText: 'Confirmar',
						  denyButtonText: 'Calcelar',
						}).then((result) => {
						  	if (result.isConfirmed) {
								document.forms["frmLoja"].action = "./updateLoja";
								document.forms["frmLoja"].submit();	
							}
						})
				}
			}
			
		<%} else {%>
		Swal.fire(
				'Atenção!'	,
				'Você não tem permissao para adicionar ou atualizar',
	  			'warning'
	  			)
		<%}%>
	}

	<%if((Boolean) request.getSession().getAttribute("adicao") != true){ %> 
		var elemento = document.getElementsByClassName("disableValidation");
		for (var i = 0; i < elemento.length ; i++){		 		
	 		elemento[i].disabled = true;
	 	}
	<%}%>
	
	function redefinirSenha(){
		<%if((Boolean) request.getSession().getAttribute("adicaoUser") == true){ %>
			var usuario = document.getElementById("usuario").value;
			var validacao = false;
			<%for (int i = 0; i < lista.size(); i++) {%>
				if ("<%=lista.get(i).getNome()%>" == usuario){
					validacao = true;
				}
			<%}%>
			if(validacao == true){
				if(usuario != ""){
					Swal.fire({
						icon: 'question',
						  title: 'Você esta prestes a redefinir a senha de  um usuario',
							text: 'deseja continuar?',
					  showDenyButton: true,
					  showCancelButton: false,
					  confirmButtonText: 'Confirmar',
					  denyButtonText: 'Calcelar'
					}).then((result) => {
					  	if (result.isConfirmed) {
							window.location.href="./senhaPadrao?user="+usuario;		
						}
					})
					
				}else{
					document.getElementById("usuario").focus();
					Swal.fire(
							'Atenção!'	,
							'Favor selecionar o usuario!',
				  			'warning'
				  			)
				}
			}else{
				Swal.fire(
						'Atenção!'	,
						'O usuario informado não existe!',
			  			'warning'
			  			)
			}
		<%}else{%>
			Swal.fire(
					'Atenção!'	,
					'Você não tem permissao para editar usuários!',
		  			'warning'
					)
		<%}%>
	}
	
	</script>
</body>
</html>