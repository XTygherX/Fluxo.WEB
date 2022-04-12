package CASCAS;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.JavaBeansUser;

public class CASCA_DAO_PADRAO_EXEMPLOS {
	/** Modulo de conexão **/
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3307/expansaoassai";
	private String username = "root";
	private String password = "assai";

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
	//Crud Novo User
	public void inserirNovoUsuario(JavaBeansUser usuario) {
		String create = "insert into usuario_permissao (nome,visualizacao,edicao,adicao,adicao_usuario,ativo,historico) values (?,?,?,?,?,?,?)";	
		try {
			// Abrir conexao com BD
			Connection con = conectar();
			System.out.println("CONECTADO");
			// Preparando a query para execulção no BD
			System.out.println("PREPARANDO QUERY");
			PreparedStatement pst = con.prepareStatement(create);
			// Substituind os parametros? pela variaveis/objeto do JavaBeans
			System.out.println("SUBSTITUINDO PARAMENTROS");
			pst.setString(1, usuario.getNome());
			pst.setBoolean(2, usuario.getVisualizacao());
			pst.setBoolean(3, usuario.getEdicao());
			pst.setBoolean(4, usuario.getAdicao());
			pst.setBoolean(5, usuario.getAdicaoUsuario());
			pst.setBoolean(6, usuario.getAtivo());
			pst.setString(7, usuario.getHistorico());
			// execultando a Query
			System.out.println("EXECULTANDO A QUERY");
			pst.executeUpdate();
			System.out.println("FECHANDO CONEXAO");
			con.close();
		} catch (Exception e) {
			System.out.println(e);
		}
		
	}
	
	//CRUD Listar usuarios
	public ArrayList<JavaBeansUser> listarUsuarios(){
		//objeto para acessar a classe java beans
		ArrayList<JavaBeansUser> usuario = new ArrayList<>();
		String read = "select * from usuario_permissao";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			ResultSet rs = pst.executeQuery();
			//Laço para percorrer resulta do select
			while (rs.next()) {
				String nome = rs.getString(1);
				Boolean visualizacao = rs.getBoolean(2);
				Boolean edicao = rs.getBoolean(3);
				Boolean adicao = rs.getBoolean(4);
				Boolean adicaoUser = rs.getBoolean(5);
				Boolean ativo = rs.getBoolean(6);
				String historico = rs.getString(7);
				// Populando o arraylist
				usuario.add(new JavaBeansUser(nome,visualizacao,edicao,adicao,adicaoUser,ativo,historico));
			}
			con.close();
			return usuario;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	
		
	}
	
	//Crud UPDATE USER
		public void updateUser(JavaBeansUser usuario) {
			String update = "update usuario_permissao  set visualizacao=?,edicao=?,adicao=?,adicao_usuario=?,ativo=?,historico=? where nome=?";	
			try {
				// Abrir conexao com BD
				Connection con = conectar();
				System.out.println("CONECTADO");
				// Preparando a query para execulção no BD
				System.out.println("PREPARANDO QUERY");
				PreparedStatement pst = con.prepareStatement(update);
				// Substituind os parametros? pela variaveis/objeto do JavaBeans
				System.out.println("SUBSTITUINDO PARAMENTROS");
				pst.setString(0, usuario.getNome());
				pst.setBoolean(1, usuario.getVisualizacao());
				pst.setBoolean(2, usuario.getEdicao());
				pst.setBoolean(3, usuario.getAdicao());
				pst.setBoolean(4, usuario.getAdicaoUsuario());
				pst.setBoolean(5, usuario.getAtivo());
				pst.setString(6, usuario.getHistorico());
				pst.setString(7, usuario.getNome());
				// execultando a Query
				System.out.println("EXECULTANDO A QUERY");
				pst.executeUpdate();
				System.out.println("FECHANDO CONEXAO");
				con.close();
			} catch (Exception e) {
				System.out.println(e);
			}
			
		}
	
	
}
