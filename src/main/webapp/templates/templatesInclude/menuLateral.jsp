
<div class="sidebar shadow-lg">
	<div class="logo-details">
		<i class="icon"><img src="img/logo.png"></i>
		<div class="logo_name"></div>
		<i class="bx bx-menu" id="btn"></i>
	</div>
	<ul class="nav-list">
		<li>
			<a href="./main" class="shadow-sm"> 
				<i class="bx bx-grid-alt"></i> 
				<span class="links_name">Dashboard</span>
			</a>
			<span class="tooltip">Dashboard</span>
		</li>
		<li><a href='./loja' class="shadow-sm"> <i
				class='bx bx-pie-chart-alt-2'></i> <span class='links_name'>Dashboard
					Loja</span>
		</a> <span class='tooltip'>Loja</span></li>
		<li>
			<a href="./relatorio" class="shadow-sm"> 
				<i class="bx bxs-report"></i> 
				<span class="links_name">Relatórios</span>
			</a> 
			<span class="tooltip">Relatórios</span>
		</li>
		<li class="collapse">
			<a href="#" class="shadow-sm">
				<i class="bx bx-folder"></i> <span class="links_name">Arquivos</span>
			</a>
			<span class="tooltip">Arquivos</span>
		</li>
		<li class="collapse"><a href="#" class="shadow-sm"> <i
				class="bx bxs-component"></i> <span class="links_name">Enxoval</span>
		</a> <span class="tooltip">Enxoval</span></li>
		<li><a href="./admin" class="shadow-sm"> <i
				class="bx bxs-add-to-queue"></i> <span class="links_name">
					Gerenciar Lojas/Usuários</span>
		</a> <span class="tooltip">Gerenciar Lojas/Usuários</span></li>
		<li><a href="./adminControles" class="shadow-sm"> <i
				class="bx bx-list-ul"></i> <span class="links_name">Gerenciar
					Atividades</span>
		</a> <span class="tooltip">Gerenciar Atividades</span></li>
		<li class="collapse"><a href="./adminDashBoards"
			class="shadow-sm"> <i class="bx bx-list-ul"></i> <span
				class="links_name">Gerenciar DashBoard</span>
		</a> <span class="tooltip">Gerenciar DashBoard</span></li>
		<li class="profile"><a href="./sair" class="shadow-sm"> <i
				class="bx bx-log-out" id="log_out"></i> <span class="links_name"><%=request.getSession().getAttribute("nome")%></span>
		</a> <span class="tooltip"><%=request.getSession().getAttribute("nome")%></span>
		</li>
	</ul>
</div>

