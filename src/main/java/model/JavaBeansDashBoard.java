package model;

import java.sql.Date;

public class JavaBeansDashBoard {
	private String desc;
	private Date dataInicio;
	private Date dataTermino;
	private Boolean status;
	
	private String txt;
	private Integer numero;
	
	private Double flutuante;
	
	
	
	public JavaBeansDashBoard() {
		super();
		// TODO Auto-generated constructor stub
	}

	public JavaBeansDashBoard(String desc, Date dataInicio, Date dataTermino, Boolean status, String txt) {
		super();
		this.desc = desc;
		this.dataInicio = dataInicio;
		this.dataTermino = dataTermino;
		this.status = status;
		this.txt = txt;
	}
	
	public JavaBeansDashBoard(String txt, Integer numero) {
		super();
		this.txt = txt;
		this.numero = numero;	
	}
	
	public JavaBeansDashBoard(String txt, Double flutuante) {
		super();
		this.txt = txt;
		this.flutuante = flutuante;	
	}
	

	
	public Double getFlutuante() {
		return flutuante;
	}

	public void setFlutuante(Double flutuante) {
		this.flutuante = flutuante;
	}

	public String getTxt() {
		return txt;
	}

	public void setTxt(String txt) {
		this.txt = txt;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Date getDataInicio() {
		return dataInicio;
	}

	public void setDataInicio(Date dataInicio) {
		this.dataInicio = dataInicio;
	}

	public Date getDataTermino() {
		return dataTermino;
	}

	public void setDataTermino(Date dataTermino) {
		this.dataTermino = dataTermino;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	
	
}
