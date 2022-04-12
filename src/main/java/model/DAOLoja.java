package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;



public class DAOLoja {
	/** Modulo de conexão **/
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/expansaoassai";
	private String username = "root";
	private String password = "assai";
	String[] lista = {"solicitacao","controle_solicitacao","configuracoes","eletrica",
			"cabeamento_dados","cripagem" , "instalacoes","validacoes"};
	
	private Connection conectar() throws ClassNotFoundException{
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	//crud SELECT lojas
	public ArrayList<JavaBeansLoja> ListaLojas(){
		ArrayList<JavaBeansLoja> lojas = new ArrayList<>();
		String read = "select * from lojas";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String numLoja = rs.getString(1);
				String nomeLoja = rs.getString(2);
				Date dataEntradaTi = rs.getDate(3);
				Date dataInaguracao = rs.getDate(4);
				Boolean status = rs.getBoolean(5);
				Boolean visualizacao = rs.getBoolean(6);
				String responsavel = rs.getString(7);
				String historico = rs.getString(8);
				lojas.add(new JavaBeansLoja(numLoja,nomeLoja,dataEntradaTi,dataInaguracao,status,visualizacao,responsavel,historico));
			}
			con.close();
			return lojas;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
		
	}

	public Boolean updateTodasDashBoard(JavaBeansTabelaControles updateControles) {
		Boolean validacao = false;
		ArrayList<JavaBeansLoja> listaLojas = ListaLojas();
		for (int i = 0 ; i < listaLojas.size(); i++) {
			String tabela = updateControles.getTabela();
			String numLoja = listaLojas.get(i).getNumLoja();
			String update = "update dashboard_"+numLoja+"_"+tabela 
					+" set descricao=? "
					+"where descricao=?";
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, updateControles.getDescNova());
				pst.setString(2, updateControles.getDescAtual());
				pst.executeUpdate();
				con.close();
				validacao = true;
				
			} catch (Exception e) {
				e.printStackTrace();
				validacao = false;
			}
		}
		return validacao;
	}
	
	//CRUD NOVA LOJA
	public Boolean inserirNovaLoja(JavaBeansLoja loja) {
		String create = "insert into lojas (num_loja,nome_loja,data_entrada_ti,data_inauguracao,status_inauguracao,visializacao,responsavel,historico) values (?,?,?,?,?,?,?,?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.setString(1, loja.getNumLoja());
			pst.setString(2, loja.getNomeLoja());			
			pst.setDate(3, loja.getDataEntradaTi());
			pst.setDate(4, loja.getDataInaguracao());
			pst.setBoolean(5, loja.getStatus());
			pst.setBoolean(6, loja.getVisualizacao());
			pst.setString(7, loja.getResponsavel());
			pst.setString(8, loja.getHistorico());
			pst.execute();
			con.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	public Boolean updateNumLoja(String numLoja, String numLojaNovo) {
		//IMPLEMENTACAO FUTURA - ATUALIZAR NOME DAS TABELAS
		Boolean validacaoErro = true;
		for (String txt: lista) {
			 String dashboard = "dashboard_" + numLoja + "_" + txt; 
			 String renameDashBoard = "dashboard_" + numLojaNovo + "_" + txt; 
			 String renameTable = "rename table " + dashboard + " to " + renameDashBoard; 
			 try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(renameTable);
				pst.executeUpdate();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
				validacaoErro = false;
			}
		}
		String dashboard = "dashboard_" + numLoja + "_link ";
		String renameDashBoard = "dashboard_" + numLojaNovo + "_link ";
		String renameTable = "rename table " + dashboard + " to " + renameDashBoard; 
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(renameTable);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			validacaoErro = false;
		}
		dashboard = "dashboard_" + numLoja + "_sistemas ";
		renameDashBoard = "dashboard_" + numLojaNovo + "_sistemas ";
		renameTable = "rename table " + dashboard + " to " + renameDashBoard; 
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(renameTable);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			validacaoErro = false;
		}
		
		String update = "update lojas set num_loja="+ numLojaNovo+" where num_loja="+numLoja;
		try {
			Connection con = conectar();		
			PreparedStatement pst = con.prepareStatement(update);
			pst.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
			validacaoErro = false;
		}
		return validacaoErro;
	}
	
	public Boolean updateLoja(JavaBeansLoja loja, String numLojaAntigo) {
		String update = "update lojas set num_loja=?,nome_loja=?,data_entrada_ti=?,data_inauguracao=?,status_inauguracao=?,visializacao=?,responsavel=?,historico=? where num_loja=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, loja.getNumLoja());
			pst.setString(2, loja.getNomeLoja());			
			pst.setDate(3, loja.getDataEntradaTi());
			pst.setDate(4, loja.getDataInaguracao());
			pst.setBoolean(5, loja.getStatus());
			pst.setBoolean(6, loja.getVisualizacao());
			pst.setString(7, loja.getResponsavel());
			pst.setString(8, loja.getHistorico());
			pst.setString(9, loja.getNumLoja());
			pst.executeUpdate();
			con.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}

	}
}
