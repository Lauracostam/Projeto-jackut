package br.ufal.ic.p2.jackut;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ConexaoMySQL {
	public static String status = "Não conectou...";
	public ConexaoMySQL() {
		
	}

	public static java.sql.Connection getConexaoMySQL() {
		Connection connection = null; 
		try {
			//Carregando o JDBC Driver padr�o
			String driverName = "com.mysql.cj.jdbc.Driver";
			Class.forName(driverName);
			//Configurando a nossa conex�o com um banco de dados//
			String serverName = "localhost";    //caminho do servidor do BD
			String mydatabase = "jackut";        //nome do seu banco de dados
			String url = "jdbc:mysql://" + serverName + "/" + mydatabase;
			String username = "root";        //nome de um usu�rio de seu BD
			String password = "12345";      //sua senha de acesso
			connection = DriverManager.getConnection(url, username, password);
			//Testa sua conex�o//
			if (connection != null) {
				status = ("Conectado!");
			} else {
				status = ("N�o foi possivel realizar conex�o");
			}
			return connection;
		} catch (ClassNotFoundException e) {
			System.out.println("O driver expecificado nao foi encontrado.");
			return null;
		} catch (SQLException e) {
		//N�o conseguindo se conectar ao banco
		System.out.println("Nao foi possivel conectar ao Banco de Dados.");
		return null;
	
		}
	}
	
	public static String statusConection() {
		return status;
	}
	
	public static boolean FecharConexao() {
		try {
			ConexaoMySQL.getConexaoMySQL().close();
			return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public static java.sql.Connection ReiniciarConexao() {
		FecharConexao();
		return ConexaoMySQL.getConexaoMySQL();
	}
	
	public static void  insertUser(User usuario) throws SQLException {
	  String SQL = "INSERT INTO usuarios(login,senha,nome) "
              + "VALUES(?,?,?)";

      try (Connection conn = ConexaoMySQL.getConexaoMySQL();
              PreparedStatement pstmt = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

          pstmt.setString(1, usuario.getLogin());
          pstmt.setString(2, usuario.getSenha());
          pstmt.setString(3, usuario.getNome());

          pstmt.executeUpdate();
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      }
   }
	public static boolean checkUser(String valor, String atributo){
		boolean result = false;
		try{
			Connection conn = ConexaoMySQL.getConexaoMySQL();
			String query = "SELECT * FROM usuarios WHERE "+ atributo + " = \'" + valor +"\';";
			java.sql.Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(query);
			// iterate through the java resultset
			while (rs.next()){
				result = true;
			}
			st.close();
			return result;
		}catch (Exception e){
			System.err.println("Got an exception! ");
			System.err.println(e.getMessage());
		}
		return result;
	}
	
	public static String getUserInfo(String current_login, String atributo){
		boolean found = false;
		String result = null;
	    try
	    {
	      Connection conn = ConexaoMySQL.getConexaoMySQL();
	      
	      String query = "SELECT * FROM usuarios WHERE login = \'" + current_login +"\';";
		 
	      java.sql.Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      while (rs.next()){
	    	found = true;
	    	if (atributo.equals("login")) {
	    		result = rs.getString("login");
	    	}
	    	else if (atributo.equals("senha")) {
	    		result = rs.getString("senha");
	    	}
	    	else if (atributo.equals("nome")) {
	    		result = rs.getString("nome");
			}
	    	else if(atributo.equals("descricao")) {
	    		result = rs.getString("descricao");
	    	}
	    	else if(atributo.equals("estadoCivil")) {
	    		result = rs.getString("estadoCivil");
	    	}
	    	else if(atributo.equals("aniversario")) {
	    		result = rs.getString("aniversario");
	    	}
	    	else if(atributo.equals("filhos")) {
	    		result = rs.getString("filhos");
	    	}
	    	else if(atributo.equals("idiomas")) {
	    		result = rs.getString("idiomas");
	    	}
	    	else if(atributo.equals("cidadeNatal")) {
	    		result = rs.getString("cidadeNatal");
	    	}
	    	else if(atributo.equals("estilo")) {
	    		result = rs.getString("estilo");
	    	}
	    	else if(atributo.equals("fumo")) {
	    		result = rs.getString("fumo");
	    	}
	    	else if(atributo.equals("bebo")) {
	    		result = rs.getString("bebo");
	    	}
	    	else if(atributo.equals("moro")) {
	    		result = rs.getString("moro");
	    	}
	      }
	      st.close();
	      if (found) {
	    	  return result;
	      }
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	    return result;
	  }
	
	public static void cleanTable(){
	  String SQL = "DELETE FROM usuarios;";

      try (Connection conn = ConexaoMySQL.getConexaoMySQL();
              PreparedStatement pstmt = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

          pstmt.executeUpdate();
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      	}
	}
	public static void cleanAmigos(){
	  String SQL = "DELETE FROM amigos;";

      try (Connection conn = ConexaoMySQL.getConexaoMySQL();
              PreparedStatement pstmt = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

          pstmt.executeUpdate();
      } catch (SQLException ex) {
          System.out.println(ex.getMessage());
      	}
}
	
	public static boolean Login(String login, String senha){
	  boolean result = false;
	    try
	    {
	      Connection conn = ConexaoMySQL.getConexaoMySQL();
	      
	      String query = "SELECT * FROM usuarios WHERE login = \'" + login +"\' and senha = \'" + senha +"\';";
	      
	      java.sql.Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query);
	      
	      // iterate through the java resultset
	      
	      while (rs.next())
	      {
	    	result = true;
	      }
	      st.close();
	      return result;
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
	    return result;
  }
	public static boolean checkSessao() {
		boolean notEmpty = false;
		try
	    {
	      Connection conn = ConexaoMySQL.getConexaoMySQL();
	      
	      String query = "SELECT * FROM sessao";
	      
	      java.sql.Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query); //select
	      
	      // iterate through the java resultset
	      
	      while (rs.next()) //itera pra baixo
	      {
	    	 notEmpty = true;
	      }
	      st.close();
	      return notEmpty;
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return notEmpty;
	}
	public static int retornaId() {
		int id = 1;
		try
	    {
	      Connection conn = ConexaoMySQL.getConexaoMySQL();
	      
	      String query = "SELECT * FROM sessao ORDER BY id DESC LIMIT 1";
	      
	      java.sql.Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query); //select
	      
	      // iterate through the java resultset
	      
	      while (rs.next()) //itera pra baixo
	      {
	    	 id = Integer.valueOf(rs.getString("id"));
	      }
	      st.close();
	      return id;
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return id;
	}
	public static String retornaLogin(String id) {
		String login = null;
		try
	    {
	      Connection conn = ConexaoMySQL.getConexaoMySQL();
	      
	      String query = "SELECT loginSessao FROM sessao WHERE id = \'" + id + "\';";
	      
	      java.sql.Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query); //select
	      
	      // iterate through the java resultset
	      
	      while (rs.next()) //itera pra baixo
	      {
	    	 login = rs.getString("loginSessao");
	      }
	      st.close();
	      return login;
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return login;
		
	}
	public static void updateInfo(String login, String atributo, String valor) {
		String SQL = "UPDATE usuarios SET " + atributo + " = \'" + valor + "\' WHERE login = \'" + login + "\';";
	      try (Connection conn = ConexaoMySQL.getConexaoMySQL();
	              PreparedStatement pstmt = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

	          pstmt.executeUpdate();
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	}
	public static void cleanSessao() {
		String SQL = "DELETE FROM sessao;";

	    try (Connection conn = ConexaoMySQL.getConexaoMySQL();
	            PreparedStatement pstmt = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {
	
	        pstmt.executeUpdate();
	    } catch (SQLException ex) {
	        System.out.println(ex.getMessage());
	      }
	}
	public static void adicionarAmigos(String loginUsuario, String loginAmigo) {
		String SQL = "INSERT INTO amigos(loginUsuario,loginAmigo) "
	              + "VALUES(?,?)";

	      try (Connection conn = ConexaoMySQL.getConexaoMySQL();
	              PreparedStatement pstmt = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

	          pstmt.setString(1, loginUsuario);
	          pstmt.setString(2, loginAmigo);

	          pstmt.executeUpdate();
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	}
	public static ArrayList<Boolean> checkAmigo(String loginUsuario, String loginAmigo) {
		boolean pedidoEnviado = false;
		boolean pedidoAceito = false;
		try
	    {
	      Connection conn = ConexaoMySQL.getConexaoMySQL();
	      
	      String query = "SELECT * FROM amigos WHERE loginUsuario = \'" + loginUsuario + "\' AND loginAmigo = \'" + loginAmigo + "\';";
	      
	      java.sql.Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query); //select
	      
	      // iterate through the java resultset
	      
	      while (rs.next()) //itera pra baixo
	      {
	    	 pedidoEnviado = true;
	      }
	      String query1 = "SELECT * FROM amigos WHERE loginUsuario = \'" + loginAmigo + "\' AND loginAmigo = \'" + loginUsuario + "\';";


	      ResultSet rs1 = st.executeQuery(query1); //select
	      while (rs1.next()) //itera pra baixo
	      {
	    	 pedidoAceito = true;
	      }
	      st.close();
	      
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		ArrayList<Boolean> resultado = new ArrayList<Boolean>();
		resultado.add(pedidoEnviado);
		resultado.add(pedidoAceito);
		return resultado;
	}
	public static ArrayList<String> listaAmigo(String login){
		ArrayList<String> lista = new ArrayList<>();
		try
	    {
	      Connection conn = ConexaoMySQL.getConexaoMySQL();
	      
	      String query = "SELECT loginAmigo FROM amigos WHERE loginUsuario = \'" + login + "\';";
	      
	      java.sql.Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query); //select
	      
	      // iterate through the java resultset
	      
	      while (rs.next()) //itera pra baixo
	      {
	    	 lista.add(rs.getString("loginAmigo"));
	      }
	      st.close();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return lista;
	}
	public static void inserirRecado(String login, String destinatario, String recado) {
		String SQL = "INSERT INTO recados(login, destinatario, recado) "
	              + "VALUES(?,?,?)";

	      try (Connection conn = ConexaoMySQL.getConexaoMySQL();
	              PreparedStatement pstmt = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

	          pstmt.setString(1, login);
	          pstmt.setString(2, destinatario);
	          pstmt.setString(3, recado);

	          pstmt.executeUpdate();
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	}
	public static void cleanRecados() {
		String SQL = "DELETE FROM recados;";

	      try (Connection conn = ConexaoMySQL.getConexaoMySQL();
	              PreparedStatement pstmt = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

	          pstmt.executeUpdate();
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      	}
	}
	public static String lerRecados(String destinatario) {
		String rec = null;
		try
	    {
	      Connection conn = ConexaoMySQL.getConexaoMySQL();
	      
	      String query = "SELECT recado FROM recados WHERE destinatario = \'" + destinatario + "\' LIMIT 1;";
	      
	      java.sql.Statement st = conn.createStatement();
	      ResultSet rs = st.executeQuery(query); //select
	      
	      // iterate through the java resultset
	      
	      while (rs.next()) //itera pra baixo
	      {
	    	 rec = rs.getString("recado");
	      }
	      st.close();
	      String query2 = "DELETE FROM recados WHERE destinatario = \'" + destinatario + "\' LIMIT 1;";
	      PreparedStatement pstmt = conn.prepareStatement(query2, java.sql.Statement.RETURN_GENERATED_KEYS);
	      pstmt.executeUpdate();
	    }
	    catch (Exception e)
	    {
	      System.err.println("Got an exception! ");
	      System.err.println(e.getMessage());
	    }
		return rec;
	}
	public static String criarSessao(String login) {
		int id = 1;
		if(ConexaoMySQL.checkSessao()) {
			id = ConexaoMySQL.retornaId() + 1;
		}
		String idString = Integer.toString(id);
		String SQL = "INSERT INTO sessao(id, loginSessao) "
	              + "VALUES(?,?);";

	      try (Connection conn = ConexaoMySQL.getConexaoMySQL();
	              PreparedStatement pstmt = conn.prepareStatement(SQL, java.sql.Statement.RETURN_GENERATED_KEYS)) {

	          pstmt.setString(1, idString);
	          pstmt.setString(2, login);

	          pstmt.executeUpdate();
	      } catch (SQLException ex) {
	          System.out.println(ex.getMessage());
	      }
	      return idString;
	}
}
