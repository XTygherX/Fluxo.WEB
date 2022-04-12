package CASCAS;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;  
public class TESTE {
	public void main(String argv[]){  
		/** Modulo de conexão **/
		 String driver = "com.mysql.cj.jdbc.Driver";
		 String url = "jdbc:mysql://localhost:3306/expansaoassai";
		 String username = "root";
		 String password = "assai";
		String[] lista = {"solicitacao","controle_solicitacao","configuracoes","eletrica",
				"cabeamento_dados","cripagem" , "instalacoes","validacoes"};

		for (String tabela: lista) {
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
					
					Class.forName(driver);
					Connection con = DriverManager.getConnection(url, username, password);
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
}


