<%@page import="javax.swing.text.Document"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="model.JavaBeansUser"%>
<%
if (session.getAttribute("nome") != null){
	response.sendRedirect("./main");
}
%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css"
	integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="css/login.css">
<link rel="stylesheet" href="css/styleAll.css">
<script src="js/jsExternos/sweetalert2.all.min.js"></script>

<title>Fluxo Login</title>
</head>
<body>
	<div class="container">
		<div class="stars"></div>
		<div class="stars2"></div>
	</div>
	<div class="logo">
		<img src="img/logo.png">
	</div>
	<form action="" id="frmRedefinir">
		<div class="card frmLogin bg-light shadow-lg">
			<h4>Alterar Senha</h4>
			<input type="hidden" name="usuario"
				value="<%=request.getAttribute("usuario")%>">
			<div class="form-group">

				<label for="pwC">Novo Password</label> <input type="password"
					class="form-control" id="pwC" name="pwC" placeholder="Password"
					value="Rede@2021">
			</div>
			<div class="form-group">
				<label for="pwV">Confirme Password</label> <input type="password"
					class="form-control" id="pwV" name="pwV" placeholder="Password"
					value="Rede@2021">
			</div>
			<button onclick="redefinir()" type="button" class="btn btn-primary">Redefinir</button>
		</div>
	</form>
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
	<script type="text/javascript">
	
	
	function redefinir(){

		if(document.forms["frmRedefinir"]["pwV"].value === document.forms["frmRedefinir"]["pwC"].value ){
			document.forms["frmRedefinir"]["pwV"].value = btoa(document.forms["frmRedefinir"]["pwV"].value);
			document.forms["frmRedefinir"]["pwC"].value = btoa(document.forms["frmRedefinir"]["pwC"].value);
			document.forms["frmRedefinir"].action = "./redefinirSenha";
			document.forms["frmRedefinir"].submit();
		}else{
			Swal.fire({
			  	icon: 'error',
		 		 title: "Senhas não coincidem!"
			  })	
		}
		

	}
	</script>
</body>
</html>