package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Enumeration;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

public class DAOControles {
	/** Modulo de conexão **/
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/expansaoassai";
	private String username = "root";
	private String password = "assai";
	String[] lista = {"ENTRADA_DE_TELEFONIA","RECEBIMENTO_DE_ATIVOS","INSTALACAO_DE_LINKS","CONFIGURACOES_DE_ATIVOS",
			"DEPOSITO_DOCAS","AREA_DE_VENDAS","ADMINISTRATIVO","REDE_ESTRUTURADA","VALIDACOES_FINAIS"};

	private Connection conectar() throws ClassNotFoundException {
		Connection con = null;
		try {
			Class.forName(driver);
			con = DriverManager.getConnection(url, username, password);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public Boolean novoControle(JavaBeansTabelaControles controle) {
		DAOLoja DAOlistaLoja = new DAOLoja();
		ArrayList<JavaBeansLoja> JRlistaLojas = DAOlistaLoja.ListaLojas();
		String insert = "insert into tabelas(descricao) values(?)";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(insert);
			pst.setString(1, controle.getTabela());
			pst.execute();
			
			String create = "create table  controles_"+ controle.getTabela() +"("
					+"descricao varchar(255) not null,"
					+"dMenos  integer not null"
					+")";
			
			pst = con.prepareStatement(create);
			pst.execute();
			
			
			
			for (Integer i = 0 ; i < JRlistaLojas.size(); i++) {
				create = "create table dashboard_"+JRlistaLojas.get(i).getNumLoja()+"_"+ controle.getTabela()
						+ "(descricao varchar(100) not null,"
						+ "data_inicio date,"
						+ "data_termino date,"
						+ "status_atividade boolean,"
						+ "observacao text"
						+ ")";
				

				pst = con.prepareStatement(create);
				pst.execute();
				
				
			}
			con.close();
			return true;		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		
	}
	
	public Boolean delTarefa(JavaBeansTabelaControles controle) {
		DAOLoja DAOlistaLoja = new DAOLoja();
		ArrayList<JavaBeansLoja> JBlistaLojas = DAOlistaLoja.ListaLojas();
		
		String del = "delete from controles_"+ controle.getTabela() +" where(descricao = ?)";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(del);
			pst.setString(1, controle.getDescAtual());
			pst.execute();
			
			for(Integer i = 0; i < JBlistaLojas.size(); i++) {
				del = "delete from dashboard_"+JBlistaLojas.get(i).getNumLoja()+"_"+ controle.getTabela()
				+" where(descricao = ?)";
				pst = con.prepareStatement(del);
				pst.setString(1, controle.getDescAtual());
				pst.execute();
			}
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public Boolean addTarefa(JavaBeansTabelaControles controle) {
		DAOLoja DAOListaLoja = new DAOLoja();
		ArrayList<JavaBeansLoja> JBlistaLojas = DAOListaLoja.ListaLojas();
		
		String insert = "insert into controles_"+ controle.getTabela()
				+ "(descricao,dMenos) values "
				+ "(?,?)";
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(insert);
				pst.setString(1, controle.getDescAtual());
				pst.setInt(2, controle.getdMenos());
				pst.execute();
				
				for (Integer i = 0 ; i < JBlistaLojas.size(); i++) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(String.valueOf(JBlistaLojas.get(i).getDataInaguracao())));
					c.add(Calendar.DATE, - Integer.valueOf(controle.getdMenos()));  // number of days to add
					var novaData = sdf.format(c.getTime());
					java.sql.Date dataLimite = new java.sql.Date(sdf.parse(novaData).getTime());
					
					insert = "insert into dashboard_"+JBlistaLojas.get(i).getNumLoja()+"_"+ controle.getTabela()
					+"(descricao,data_termino) values(?,?)";
					pst = con.prepareStatement(insert);
					pst.setString(1, controle.getDescAtual());
					pst.setDate(2, dataLimite);
					pst.execute();
						
				}
				con.close();
				return true;
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
	}
	public Boolean renControle(JavaBeansTabelaControles controle) {
		DAOLoja DAOListaLoja = new DAOLoja();
		ArrayList<JavaBeansLoja> JBListaLojas = DAOListaLoja.ListaLojas();
		String renameTabelas = "rename table controles_"+controle.getDescAtual()+" to controles_"+controle.getDescNova();
		String renameListaTabelas = "update tabelas set descricao=? where descricao=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(renameTabelas);
			pst.execute();
			
			pst = con.prepareStatement(renameListaTabelas);
			pst.setString(1, controle.getDescNova());
			pst.setString(2, controle.getDescAtual());
			pst.execute();
			for (Integer i = 0 ; i < JBListaLojas.size(); i++) {
				renameTabelas = "rename table dashboard_"+JBListaLojas.get(i).getNumLoja()+"_"+ controle.getDescAtual()+" to dashboard_"+JBListaLojas.get(i).getNumLoja()+"_"+ controle.getDescNova();
				pst = con.prepareStatement(renameTabelas);
				pst.execute();
				
				
			}
			
			con.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	public Boolean delControle(JavaBeansTabelaControles controle) {
		DAOLoja DAOlistaLoja = new DAOLoja();
		ArrayList<JavaBeansLoja> JBlistaLojas = DAOlistaLoja.ListaLojas();
		String del = "delete from tabelas where(descricao = ?)";
		String drop = "drop table controles_"+controle.getTabela();
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(del);
			pst.setString(1, controle.getTabela());
			pst.execute();
			
			
			pst = con.prepareStatement(drop);
			pst.execute();
			
			
			
			for (Integer i = 0 ; i < JBlistaLojas.size(); i++) {
				drop = "drop table dashboard_"+JBlistaLojas.get(i).getNumLoja()+"_"+ controle.getTabela();
				pst = con.prepareStatement(drop);
				pst.execute();
				
				
			}
			con.close();
			return true;		
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void criarControles(){
		var create = "create table tabelas(descricao varchar(255) not null)";
		
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.execute();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (String tabela: lista) {
			/* String drop="drop table controles_"+tabela;
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(drop);
				pst.execute();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			*/
			create = "create table  controles_"+tabela+"("
					+"descricao varchar(255) not null,"
					+"dMenos  integer not null"
					+")";
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.execute();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			String insert = "insert into tabelas(descricao) values(?)";
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(insert);
				pst.setString(1, tabela);
				pst.execute();
				con.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		}
		
		
		
		String select = "select * from tabelas";
		ArrayList<String> listaControles = new ArrayList<>();
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String descricao = rs.getString(1);
				listaControles.add(descricao);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		for (String tabela: listaControles) {			
			String insert = "insert into "
					+ "controles_"+tabela
					+ "(descricao,dMenos) values "
					+ "(?,?)";
					//+ "where"
					//+ "descricao=?";
			try {  
				DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();    
				DocumentBuilder db = dbf.newDocumentBuilder();  
				Document doc = db.parse(getClass().getResource("") + "CONTROLES.xml");
				String xmlElement = tabela + "_option";
				for (int i = 0; i < doc.getElementsByTagName(xmlElement).getLength() ; i += 2) {
								
					Connection con = conectar();
					PreparedStatement pst = con.prepareStatement(insert);
					pst.setString(1, doc.getElementsByTagName(xmlElement).item(i).getTextContent());			
					pst.setInt(2, Integer.parseInt(doc.getElementsByTagName(xmlElement).item(i+1).getTextContent()));
					//pst.setString(2,descAtual);
					pst.execute();
					con.close();
					
				}  
			}  
			catch (Exception e){  
				e.printStackTrace(); 

			}

			
		}

	}
	public Boolean updateTabelaControles(JavaBeansTabelaControles updateTabelaControles, String tabela) {
		DAOLoja daoLoja = new DAOLoja();
		Boolean validacao = true;
		String update = "update controles_"+tabela+" set descricao=? , dMenos=? where descricao=?";
		try {  							
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, updateTabelaControles.getDescNova().toString());			
				pst.setInt(2, (Integer) updateTabelaControles.getdMenos());
				pst.setString(3, updateTabelaControles.getDescAtual().toString());
				pst.executeUpdate();
				con.close();
				validacao = daoLoja.updateTodasDashBoard(updateTabelaControles);;
				
				
			}   
		catch (Exception e){  
			e.printStackTrace(); 
			validacao = false;
		}
		return validacao;
	}
	public ArrayList<String> carregarListaTabelas(){
		String select = "select * from tabelas";
		ArrayList<String> listaControles = new ArrayList<>();
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String descricao = rs.getString(1);
				listaControles.add(descricao);
			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listaControles;
	}
	public ArrayList<JavaBeansTabelaControles> carregarControles(String tabela){
		ArrayList<JavaBeansTabelaControles> controles = new ArrayList<>();
		String select = "select * from controles_"+tabela;
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				String descricao = rs.getString(1);
				Integer dMenos = rs.getInt(2);
				controles.add(new JavaBeansTabelaControles(descricao,dMenos));
			}
			con.close();
			return controles;
		} catch (Exception e) {
			return null;
		}
	}
	
}
