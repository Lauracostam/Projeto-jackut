package br.ufal.ic.p2.jackut;

import java.util.ArrayList;

public class Facade {
	
	public static void zerarSistema() {
		ConexaoMySQL.cleanTable();
		ConexaoMySQL.cleanSessao();
		ConexaoMySQL.cleanAmigos();
		ConexaoMySQL.cleanRecados();
	}
	
	public static void encerrarSistema() {
		System.out.println();
	}
	
	public static String getAtributoUsuario(String login, String atributo) throws Exception {
		if (ConexaoMySQL.checkUser(login, "login")){
			String result = ConexaoMySQL.getUserInfo(login, atributo);
			if(result == null) {
				throw new Exception("Atributo não preenchido.");
			}
			return result;
		}
		else {
			throw new Exception("Usuário não cadastrado.");
		}
	}
	
	public static void criarUsuario(String login, String senha, String nome) throws Exception {
		User usuario = new User(login, senha, nome);
		if(login == null || nome == null) {
			throw new Exception("Login inválido.");
		}
		else if(senha == null) {
			throw new Exception("Senha inválida.");
		}
		else {
			if (ConexaoMySQL.checkUser(login, "login")){
				throw new Exception("Conta com esse nome já existe.");	
			}
			else {
				ConexaoMySQL.insertUser(usuario);	
			}
		}
			
	}
	
	
	
	public static String abrirSessao(String login, String senha) throws Exception{
		String idString = null;
		if(login == null || senha == null) {
			throw new Exception("Login ou senha inválidos.");
		}
		else {
			if (ConexaoMySQL.Login(login, senha)) {
				idString = ConexaoMySQL.criarSessao(login);
			}
			else {
				throw new Exception("Login ou senha inválidos.");
			}
		}		
		return idString;
	}
	
	public static void editarPerfil(String id, String atributo, String valor) throws Exception{
		String loginAtual = ConexaoMySQL.retornaLogin(id);
		if(id == null) {
			throw new Exception("Usuário não cadastrado.");
		}
		if (ConexaoMySQL.checkUser(loginAtual, "login")){
		    ConexaoMySQL.updateInfo(loginAtual, atributo, valor);
		}
		else {
			throw new Exception("Usuário não cadastrado.");
		}
	}
	public static void adicionarAmigo(String id, String loginAdicionado) throws Exception {
		String loginAtual = ConexaoMySQL.retornaLogin(id);
		if(loginAtual != null) {
			if(loginAtual.equals(loginAdicionado)) {
				throw new Exception("Usuário não pode adicionar a si mesmo como amigo.");
			}
			if(ConexaoMySQL.checkUser(loginAtual, "login") && ConexaoMySQL.checkUser(loginAdicionado, "login")) {
				ArrayList<Boolean> res = ConexaoMySQL.checkAmigo(loginAtual, loginAdicionado);
				if(res.get(0) == true && res.get(1) == false) {
					throw new Exception("Usuário já está adicionado como amigo, esperando aceitação do convite.");
				}
				else if(ehAmigo(loginAtual, loginAdicionado)) {
					throw new Exception("Usuário já está adicionado como amigo.");
				}
				else {
					ConexaoMySQL.adicionarAmigos(loginAtual, loginAdicionado);					
				}
			}
			else {
				throw new Exception("Usuário não cadastrado.");
			}
		}
		else {
			throw new Exception("Usuário não cadastrado.");
		}
	}
	public static boolean ehAmigo(String loginUsuario, String loginAdicionado) {
		ArrayList<Boolean> res = ConexaoMySQL.checkAmigo(loginUsuario, loginAdicionado);
		if(res.get(0) == true && res.get(1) == true) {
			return true;
		}
		return false;
	}
	public static String getAmigos(String login) {
		String listaAmigos = "{";
		ArrayList<String> res = ConexaoMySQL.listaAmigo(login);
		for(int i = 0; i < res.size(); i++) {
			if(ehAmigo(login, res.get(i))){
				listaAmigos+=res.get(i);
				if(i != res.size()-1) {
					listaAmigos += ",";
				}
			}
		}
		listaAmigos += "}";
		return listaAmigos;
	}
	public static void enviarRecado(String id, String destinatario, String recado) throws Exception {
		String loginAtual = ConexaoMySQL.retornaLogin(id);
		if(ConexaoMySQL.checkUser(loginAtual, "login") && ConexaoMySQL.checkUser(destinatario, "login")){
			if(loginAtual.equals(destinatario)) {
				throw new Exception("Usuário não pode enviar recado para si mesmo.");
			}
			ConexaoMySQL.inserirRecado(loginAtual, destinatario, recado);
		}
		else {
			throw new Exception("Usuário não cadastrado.");
		}
	}
	public static String lerRecado(String id) throws Exception {
		String loginAtual = ConexaoMySQL.retornaLogin(id);
		String recado = null;
		recado = ConexaoMySQL.lerRecados(loginAtual);
		if(recado == null) {
			throw new Exception("Não há recados.");
		}
		return recado;
	}
}
