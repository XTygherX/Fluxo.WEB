package model;

import java.sql.Date;

public class JavaBeansLoja {
	private String numLoja;
	private String nomeLoja;
	private Date dataEntradaTi;
	private Date dataInaguracao;
	private Boolean status;
	private Boolean visualizacao;
	private String responsavel;
	private String historico;
	
	
	public JavaBeansLoja() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public JavaBeansLoja(String numLoja, String nomeLoja, Date dataEntradaTi, Date dataInaguracao, Boolean status, Boolean visualizacao, String responsavel, String historico) {
		super();
		this.numLoja = numLoja;
		this.nomeLoja = nomeLoja;
		this.dataEntradaTi = dataEntradaTi;
		this.dataInaguracao = dataInaguracao;
		this.status = status;
		this.visualizacao = visualizacao;
		this.responsavel = responsavel;
		this.historico = historico;
	}

	public String getNumLoja() {
		return numLoja;
	}

	public void setNumLoja(String numLoja) {
		this.numLoja = numLoja;
	}

	public String getNomeLoja() {
		return nomeLoja;
	}

	public void setNomeLoja(String nomeLoja) {
		this.nomeLoja = nomeLoja;
	}

	public Date getDataEntradaTi() {
		return dataEntradaTi;
	}

	public void setDataEntradaTi(Date dataEntradaTi) {
		this.dataEntradaTi = dataEntradaTi;
	}

	public Date getDataInaguracao() {
		return dataInaguracao;
	}

	public void setDataInaguracao(Date dataInaguracao) {
		this.dataInaguracao = dataInaguracao;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public Boolean getVisualizacao() {
		return visualizacao;
	}

	public void setVisualizacao(Boolean visualizacao) {
		this.visualizacao = visualizacao;
	}

	public String getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(String responsavel) {
		this.responsavel = responsavel;
	}

	public String getHistorico() {
		return historico;
	}

	public void setHistorico(String historico) {
		this.historico = historico;
	}

	
	
	

}
