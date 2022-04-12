package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;


public class DAOUser {
	/** Modulo de conexão **/
	private String driver = "com.mysql.cj.jdbc.Driver";
	private String url = "jdbc:mysql://localhost:3306/expansaoassai";
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
	public Boolean inserirNovoUsuario(JavaBeansUser usuario) {
		String create = "insert into usuario_permissao (nome,senha,visualizacao,edicao,adicao,adicao_usuario,ativo,historico) values (?,?,?,?,?,?,?,?)";	
		try {
			// Abrir conexao com BD
			Connection con = conectar();
			// Preparando a query para execulção no BD
			PreparedStatement pst = con.prepareStatement(create);
			// Substituind os parametros? pela variaveis/objeto do JavaBeans
			pst.setString(1, usuario.getNome());
			pst.setString(2, usuario.getSenha());
			pst.setBoolean(3, usuario.getVisualizacao());
			pst.setBoolean(4, usuario.getEdicao());
			pst.setBoolean(5, usuario.getAdicao());
			pst.setBoolean(6, usuario.getAdicaoUsuario());
			pst.setBoolean(7, usuario.getAtivo());
			pst.setString(8, usuario.getHistorico());
			// execultando a Query
			pst.executeUpdate();
			con.close();
			return true;
		} catch (Exception e) {
			return false;
		}
		
	}
	
	//CRUD Listar usuarios
	public ArrayList<JavaBeansUser> listarUsuarios(){
		//objeto para acessar a classe java beans
		ArrayList<JavaBeansUser> usuario = new ArrayList<>();
		String read = "select nome,visualizacao,edicao,adicao,adicao_usuario,ativo,historico from usuario_permissao";
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
	
	public JavaBeansUser dadosUserValidacao(String valor){
		//objeto para acessar a classe java beans
		JavaBeansUser usuario = new JavaBeansUser() ;
		String read = "select nome,ativo,senha from usuario_permissao where nome=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(read);
			pst.setString(1, valor);
			ResultSet rs = pst.executeQuery();
			rs.next();
			usuario.setNome(rs.getString(1));
			usuario.setAtivo(rs.getBoolean(2));
			usuario.setSenha(rs.getString(3));
			con.close();
			return usuario;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}	
	}
	
	//Crud UPDATE USER
	public Boolean updateUser(JavaBeansUser usuario) {
			String update = "update usuario_permissao  set nome=?,visualizacao=?,edicao=?,adicao=?,adicao_usuario=?,ativo=?,historico=? where nome=?";	
			try {
				Connection con = conectar();
				PreparedStatement pst = con.prepareStatement(update);
				pst.setString(1, usuario.getNome());
				pst.setBoolean(2, usuario.getVisualizacao());
				pst.setBoolean(3, usuario.getEdicao());
				pst.setBoolean(4, usuario.getAdicao());
				pst.setBoolean(5, usuario.getAdicaoUsuario());
				pst.setBoolean(6, usuario.getAtivo());
				pst.setString(7, usuario.getHistorico());
				pst.setString(8, usuario.getNome());
				// execultando a Query
				pst.executeUpdate();
				con.close();
				return true;
			} catch (Exception e) {
				System.out.println(e);
				return false;
			}
			
		}
	
	public Boolean trocarSenha(String user, String pw) {
		String update = "update usuario_permissao set senha=? where nome=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);			
			pst.setString(1, pw);
			pst.setString(2, user);
			pst.executeUpdate();
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


	}
	
	public Boolean redefinirSenha(String user) {
		String update = "update usuario_permissao set senha=? where nome=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(update);
			pst.setString(1, Base64.getEncoder().encodeToString("Assai_Fluxo".getBytes()));
			pst.setString(2, user);
			pst.executeUpdate();
			con.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}


	}
	
	public JavaBeansUser validarUser(JavaBeansUser validacao){
		String select = "select nome,senha from usuario_permissao where nome=? and senha=?";
		try {
			Connection con = conectar();
			PreparedStatement pst = con.prepareStatement(select);
			pst.setString(1, validacao.getNome());
			pst.setString(2, validacao.getSenha().toString());
			ResultSet rs = pst.executeQuery();
			while(rs.next()) {
				if(validacao.getNome().equals(rs.getString(1)) && validacao.getSenha().equals(rs.getString(2))) {				
					con.close();
					ArrayList<JavaBeansUser> listaUser =  listarUsuarios();
					for (int i = 0; i < listaUser.size(); i++) {
						if (listaUser.get(i).getNome().equals(validacao.getNome())) {
							return listaUser.get(i);
						}
					}
				}
			}
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
