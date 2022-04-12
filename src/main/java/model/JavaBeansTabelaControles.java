package model;

public class JavaBeansTabelaControles {
	private String descNova;
	private String descAtual;
	private Integer dMenos;
	private String tabela;

	
	public JavaBeansTabelaControles() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public JavaBeansTabelaControles(String descNova, String descAtual, Integer dMenos, String tabela) {
		super();
		this.descNova = descNova;
		this.descAtual = descAtual;
		this.dMenos = dMenos;
		this.tabela = tabela;
	}

	
	public JavaBeansTabelaControles(String descAtual, Integer dMenos) {
		super();
		this.descAtual = descAtual;
		this.dMenos = dMenos;
	}

	public JavaBeansTabelaControles(String tabela) {
		super();
		this.tabela = tabela;
	}

	public String getDescNova() {
		return descNova;
	}

	public void setDescNova(String descNova) {
		this.descNova = descNova;
	}

	public String getDescAtual() {
		return descAtual;
	}

	public void setDescAtual(String descAtual) {
		this.descAtual = descAtual;
	}

	public Integer getdMenos() {
		return dMenos;
	}

	public void setdMenos(Integer dMenos) {
		this.dMenos = dMenos;
	}

	public String getTabela() {
		return tabela;
	}

	public void setTabela(String tabela) {
		this.tabela = tabela;
	}
	
	
}
