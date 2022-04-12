/*function addInputTabela(){

	var validacao = false;
	var elemento = document.getElementById("inputControles");
	var listaConstroles = document.getElementById("listaControles").getElementsByTagName("option");
	for (var j = 0; j < listaConstroles.length ; j++){
		if (listaConstroles[j].value != elemento.value && elemento.value != ""){
			validacao = true;
		}else{
			validacao = false;	
		
		} 
	}
	if (validacao == true){
		var tbody = document.getElementById("tabelaControles").getElementsByTagName('tbody')[0];
		var novaLinha = tbody.insertRow();
		var novaCelula = novaLinha.insertCell();
		var texto = document.createTextNode(elemento.value);
		novaCelula.appendChild(texto);
		tbody = document.getElementById("tabelaControles").getElementsByTagName('td')
		tbody[tbody.length - 1].insertAdjacentHTML("beforeend",'<a type="button" onclick="renameControne()"><i class="bx bx-rename"></i></a>')
		tbody[tbody.length - 1].insertAdjacentHTML("beforeend",'<a type="button" onclick="delControle()"><i class="bx bxs-trash"></i></a>')
				
		var toContainer = $('#listaControles');
		toContainer.append('<option>'+elemento.value+'</option>');
	}
}
*/
function addControle(){
	const {value} = Swal.fire({
	  	title: 'Deseja adicionar um controle?',
	  	input: 'text',
	  	inputLabel: 'Informe o titulo do controle',
		icon: 'question',
	  	showCancelButton: true,
		inputValidator: (value) => {
			if (value != undefined && value != "") {
				var tabela = document.getElementById("tabelaControles").getElementsByTagName("td")
				if (tabela.length == 0){
					var tbody = document.getElementById("tabelaControles").getElementsByTagName('tbody')[0];
					var novaLinha = tbody.insertRow();
					var novaCelula = novaLinha.insertCell();
					var texto = document.createTextNode(value);
					novaCelula.appendChild(texto);		
				}else{
					var validacao = false;
					for (var j = 0 ; j < tabela.length; j++){
						console.log(tabela[j].innerText, value)
						if (tabela[j].innerText == value){
							validacao = false
							break
						}else if (tabela[j].innerText != value){
							validacao = true
						}
					}
					if (validacao == true){
						var tbody = document.getElementById("tabelaControles").getElementsByTagName('tbody')[0];
						var novaLinha = tbody.insertRow();
						var novaCelula = novaLinha.insertCell();
						var texto = document.createTextNode(value);
						novaCelula.appendChild(texto);
						tbody = document.getElementById("tabelaControles").getElementsByTagName('td')
					}else{
						Swal.fire('Este titulo ja existe!')
					}
				}
			}
		}
	})
}

function delControle(){
	var elemento = document.getElementsByClassName("selecionado")
	if (elemento.length > 0){
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
							elemento = document.getElementsByClassName("selecionado")
							elemento[0].remove();
						    Swal.fire(
						      'Deletedado!',
						      'O controle e atividades foram exluidos.',
						      'success'
						    )
						  }
						})
			}
		})
		
	}else{
		Swal.fire({
			icon: 'warning',
			text: 'Selecione um titulo de controle para poder excluir!'
		})
	}
}

function selecionarLinha(mEvent){
	console.log(mEvent)
	var elemento = null;
	var linhaControle = null;
	linhaControle = document.getElementById("tabelaControles").getElementsByTagName("td")
	elemento = mEvent.srcElement || mEvent.document.getElementById("tabelaControles").getElementsByTagName("td")
	console.log(elemento)
	if(elemento != undefined){
		for (var i = 0; i < linhaControle.length; i++){
			linhaControle[i].classList.remove("selecionado")
		}
		elemento.classList.add("selecionado")
	}
}

function renameControne() {
			var xmlhttp = new XMLHttpRequest();		
			xmlhttp.onreadystatechange = function(){
				console.log(xmlhttp.status, xmlhttp.readState)
				if (xmlhttp.readState == 4 || xmlhttp.status == 200){
					console.log("2");
					document.getElementById("VALOR").value = xmlhttp.responseText;
				}
					
			}
			
			
			xmlhttp.open("GET", "adminDashBoards?nome="+ document.getElementById("VALOR1").value);
			xmlhttp.send()
	
 	/* $(document).ready(function () {

            $('#rename').on('click', function () {

            var nome = $('#')
            var peso = "90"
            var altura = "1,77"

                $.ajax({
                    url: 'adminDashBoards',
                    method: 'get',
                    dataType: 'json',
                    data: {
                        nome: nome,
                        peso: peso,
                        altura: altura
                    },
                    success: function (data, textStatus, jqXHR) {
                        alert(data);
                    },
                    statusCode: {
                        400: function() {
                            alert("Chamada inválida!");
                        }
                    }
                });

            });

        });*/



	}
	
function loadXMLDoc(){
			var xmlhttp;
			if (windows.XMLHttpRequest){
				//codigo para IE7+,firefox,Chrome,opera e safari
				xmlhttp = new XMLHttpRequest();
			}else{
				xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
			}
			
			xmlhttp.onreadystatechange = function(){
				if (xmlhttp.readState == 4){
					if(xmlhttp.status == 200){
						alert(xmlhttp.responseText);
					
					}else{
						alert("Sucesso")
					}
					
					
				}
			}
			
			xmlhttp.open("GET", "carregarAdminDashBoard");
			xmlhttp.setRequesteHader("Content-type", "application/x-www-form-urlencoded");
			xmlhttp.send("nome='teste'")
			
		}