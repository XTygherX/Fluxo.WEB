package model;

import java.sql.Date;

public class JavaBeansUser {
	private String nome;
	private Boolean visualizacao;
	private Boolean edicao;
	private Boolean adicao;
	private Boolean adicaoUsuario;
	private Boolean ativo;
	private String historico;
	private String senha;
	private Date dataSession;
	
	public JavaBeansUser() {
		super();
	}
	
	
	
	public JavaBeansUser(String nome, Boolean ativo, String senha) {
		super();
		this.nome = nome;
		this.ativo = ativo;
		this.senha = senha;
	}



	public JavaBeansUser(String nome, Boolean visualizacao, Boolean edicao, Boolean adicao,
				Boolean adicaoUsuario, Boolean ativo, String historico) {
		super();
		this.nome = nome;
		this.visualizacao = visualizacao;
		this.edicao = edicao;
		this.adicao = adicao;
		this.adicaoUsuario = adicaoUsuario;
		this.ativo = ativo;
		this.historico = historico;
	}
	
	public JavaBeansUser(String nome, String senha) {
		super();
		this.nome = nome;
		this.senha = senha;
	}
	
	
	public Date getDataSession() {
		return dataSession;
	}

	public void setDataSession(Date dataSession) {
		this.dataSession = dataSession;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public Boolean getVisualizacao() {
		return visualizacao;
	}
	public void setVisualizacao(Boolean visualizacao) {
		this.visualizacao = visualizacao;
	}
	public Boolean getEdicao() {
		return edicao;
	}
	public void setEdicao(Boolean edicao) {
		this.edicao = edicao;
	}
	public Boolean getAdicao() {
		return adicao;
	}
	public void setAdicao(Boolean adicao) {
		this.adicao = adicao;
	}
	public Boolean getAdicaoUsuario() {
		return adicaoUsuario;
	}
	public void setAdicaoUsuario(Boolean adicaoUsuario) {
		this.adicaoUsuario = adicaoUsuario;
	}
	public Boolean getAtivo() {
		return ativo;
	}
	public void setAtivo(Boolean ativo) {
		this.ativo = ativo;
	}
	public String getHistorico() {
		return historico;
	}
	public void setHistorico(String historico) {
		this.historico = historico;
	}

}
