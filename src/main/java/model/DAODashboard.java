package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;


public class DAODashboard {
	/** Modulo de conexão **/
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/expansaoassai";
	private String username = "root";
	private String password = "assai";
	
	DAOControles daoControles = new DAOControles();
	ArrayList<String> lista = daoControles.carregarListaTabelas();


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
	
	public Boolean novaDashBoard(String numLoja, Date dataInauguracao) {		
		var validacaoErro = true;
		for (String txt : lista) {	
			String dashboard = "dashboard_" + numLoja + "_" + txt;
			String create = "create table "
					+ dashboard
					+ "(descricao varchar(100) not null,"
					+ "data_inicio date,"
					+ "data_termino date,"
					+ "status_atividade boolean,"
					+ "observacao text"
					+ ")";
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(create);
				pst.execute();
				con.close();
			} catch (Exception e) {
				System.out.println(e);
				validacaoErro = false;
			}
			
			ArrayList<JavaBeansTabelaControles> dadosControles = new ArrayList<>();
			String select = "select * from controles_" + txt;
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(select);
				ResultSet rs = pst.executeQuery();
				while (rs.next()) {
					String desc = rs.getString(1);
					Integer dMenos = rs.getInt(2);
					dadosControles.add(new JavaBeansTabelaControles(desc, dMenos));
				}
				con.close();
			}catch (Exception e) {
				System.out.println(e);
			}
			
			String insert = "insert into "
					+ dashboard
					+ "(descricao,data_inicio,data_termino,status_atividade) values "
					+ "(?,null,?,null)";
			try {  
				for (int i = 0; i < dadosControles.size() ; i++) {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					Calendar c = Calendar.getInstance();
					c.setTime(sdf.parse(String.valueOf(dataInauguracao)));
					c.add(Calendar.DATE, - Integer.valueOf(dadosControles.get(i).getdMenos()));  // number of days to add
					var novaData = sdf.format(c.getTime());
					java.sql.Date dataLimite = new java.sql.Date(sdf.parse(novaData).getTime());
					
					Connection con = conectar();
					PreparedStatement pst = con.prepareStatement(insert);
					pst.setString(1, dadosControles.get(i).getDescAtual());			
					pst.setDate(2, dataLimite);
					pst.execute();
					con.close();

				}  
			}   
			catch (Exception e){  
				e.printStackTrace(); 
				validacaoErro = false;
			}  
			
		}
		
		String dashboard = "dashboard_" + numLoja + "_link ";
		String create = "create table "
				+ dashboard
				+ "(descricao varchar(100) not null,"
				+ "status_atividade int not null"
				+ ")";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.execute();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
		String insert = "insert into "
				+ dashboard
				+ "(descricao,status_atividade) values "
				+ "(?,?)";
		try {  
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();    
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(getClass().getResource("") + "LINK.xml");
			String xmlElement = "link_option";
			for (int i = 0; i < doc.getElementsByTagName(xmlElement).getLength() ; i += 2) {				
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(insert);
				pst.setString(1, doc.getElementsByTagName(xmlElement).item(i).getTextContent());			
				pst.setInt(2, Integer.parseInt(doc.getElementsByTagName(xmlElement).item(i+1).getTextContent()));
				pst.execute();
				con.close();

			}  
		}   
		catch (Exception e){  
			e.printStackTrace();
			validacaoErro = false;
		}  
		
		dashboard = "dashboard_" + numLoja + "_sistemas ";
		create = "create table "
				+ dashboard
				+ "(descricao varchar(100) not null,"
				+ "status_atividade int not null"
				+ ")";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(create);
			pst.execute();
			con.close();
		} catch (Exception e) {
			System.out.println(e);
			validacaoErro = false;
		}
		
		insert = "insert into "
				+ dashboard
				+ "(descricao,status_atividade) values "
				+ "(?,?)";
		try {  
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();    
			DocumentBuilder db = dbf.newDocumentBuilder();  
			Document doc = db.parse(getClass().getResource("") + "SISTEMAS.xml");
			String xmlElement = "sistemas_option";
			for (int i = 0; i < doc.getElementsByTagName(xmlElement).getLength() ; i += 2) {				
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(insert);
				pst.setString(1, doc.getElementsByTagName(xmlElement).item(i).getTextContent());			
				pst.setInt(2, Integer.parseInt(doc.getElementsByTagName(xmlElement).item(i+1).getTextContent()));
				pst.execute();
				con.close();

			}  
		}   
		catch (Exception e){  
			e.printStackTrace();
			validacaoErro = false;
		} 
		return validacaoErro;
		
	}
	

	public ArrayList<JavaBeansDashBoard> dadosDashBoardSistemas(String numLoja) {
		ArrayList<JavaBeansDashBoard> dadosDashBoardSistemas = new ArrayList<>();
		String select = "select * from dashboard_" + numLoja + "_sistemas";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String desc = rs.getString(1);
				Integer status = rs.getInt(2);
				dadosDashBoardSistemas.add(new JavaBeansDashBoard(desc,status));
			}
			con.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return dadosDashBoardSistemas;
	}
	public ArrayList<JavaBeansDashBoard> dadosIndex(ArrayList<JavaBeansLoja> listaLoja) {
		ArrayList<JavaBeansDashBoard> listaDashBoard = new ArrayList<>();
		for (var i = 0; i < listaLoja.size() ; i++) {
  			for (String txt: lista) {
  				String select = "select * from dashboard_" + listaLoja.get(i).getNumLoja() + "_" + txt;
  					try {
  						Connection con = conectar();
  						PreparedStatement pst = con.prepareStatement(select);
  						ResultSet rs = pst.executeQuery();
  						while (rs.next()) {
  							String desc = rs.getString(1);
  							Date dateInicio = rs.getDate(2);
  							Date dateTermino = rs.getDate(3);
  							Boolean status = rs.getBoolean(4);
  							listaDashBoard.add(new JavaBeansDashBoard(desc,dateInicio,dateTermino,status,null));
  						}
  						con.close();
  					}catch (Exception e) {
						System.out.println(e);
					}
					

  			}
  			
  		}
		return listaDashBoard;
		
	}
	public ArrayList<JavaBeansDashBoard> perctGraficoIndex(ArrayList<JavaBeansLoja> listaLoja) {
		ArrayList<JavaBeansDashBoard> percentGrafico = new ArrayList<>();
		
		for (var i = 0; i < listaLoja.size() ; i++) {
			String Loja = listaLoja.get(i).getNumLoja();		
			Double contPercentLoja = 0.00;
			Double contPercentLojaTotal = 0.00;
  			for (String txt: lista) {
  				String select = "select * from dashboard_" + listaLoja.get(i).getNumLoja() + "_" + txt;
  					try {
  						Connection con = conectar();
  						PreparedStatement pst = con.prepareStatement(select);
  						ResultSet rs = pst.executeQuery();
  						while (rs.next()) { 							
  							contPercentLojaTotal++;
  							if (rs.getBoolean(4) == true) {  								
  								contPercentLoja++;
  							}  							
  						}
  						con.close();
  					}catch (Exception e) {
						System.out.println(e);
					}
  			}  			 
			Double percentLoja = ((contPercentLoja / contPercentLojaTotal) * 100);
  			percentGrafico.add(new JavaBeansDashBoard(Loja,percentLoja));
  		}
		
		return percentGrafico;
		
	}
	public ArrayList<JavaBeansDashBoard> dadosLojaIndividual(String numLoja, String tabela) {
		ArrayList<JavaBeansDashBoard> listaDashBoard = new ArrayList<>();		
		String select = "select * from dashboard_" + numLoja + "_" + tabela;
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				String desc = rs.getString(1);
				Date dateInicio = rs.getDate(2);
				Date dateTermino = rs.getDate(3);
				Boolean status = rs.getBoolean(4);
				String observacoes = rs.getString(5);
				listaDashBoard.add(new JavaBeansDashBoard(desc,dateInicio,dateTermino,status,observacoes));
			}
			con.close();
		}catch (Exception e) {
			System.out.println(e);
		}
		return listaDashBoard;
		}
	
	public Boolean updateCard(String numLoja, String tabela, JavaBeansDashBoard updateCard) {
		String update = "update dashboard_" + numLoja + "_" + tabela + " set "
				+ "status_atividade=?"
				+ " where descricao=? ";
		System.out.println(update);
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setInt(1, updateCard.getNumero());
			pst.setString(2, updateCard.getTxt());
			pst.executeUpdate();
			pst.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}
	
	public Boolean updateDashBoard(String numLoja, String tabela, JavaBeansDashBoard dashBoard){
		String update = "update dashboard_" + numLoja + "_" + tabela + " set "
				+ "data_inicio=?,"
				+ "data_termino=?,"
				+ "status_atividade=?,"
				+ "observacao =?"
				+ " where descricao=? ";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setDate(1, dashBoard.getDataInicio());
			pst.setDate(2, dashBoard.getDataTermino());
			pst.setBoolean(3, dashBoard.getStatus());
			pst.setString(4, dashBoard.getTxt());
			pst.setString(5, dashBoard.getDesc());
			pst.executeUpdate();
			pst.close();
			return true;
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
	}

}

