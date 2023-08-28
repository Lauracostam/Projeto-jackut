package br.ufal.ic.p2.jackut;

public class User {
	private String login;
	private String senha;
	private String nome;
	private String descricao;
	private String estadoCivil;
	private String aniversario;
	private String filhos;
	private String idiomas;
	private String cidadeNatal;
	private String estilo;
	private String fumo;
	private String bebo;
	private String moro;
	
	public User(String login, String password, String name) {
		setLogin(login);
		setSenha(password);
		setName(name);
	}
	
	public String getNome() {
		return nome;
	}
	public void setName(String name) {
		this.nome = name;
	}
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getAniversario() {
		return aniversario;
	}

	public void setAniversario(String aniversario) {
		this.aniversario = aniversario;
	}

	public String getFilhos() {
		return filhos;
	}

	public void setFilhos(String filhos) {
		this.filhos = filhos;
	}

	public String getIdiomas() {
		return idiomas;
	}

	public void setIdiomas(String idiomas) {
		this.idiomas = idiomas;
	}

	public String getCidadeNatal() {
		return cidadeNatal;
	}

	public void setCidadeNatal(String cidadeNatal) {
		this.cidadeNatal = cidadeNatal;
	}

	public String getEstilo() {
		return estilo;
	}

	public void setEstilo(String estilo) {
		this.estilo = estilo;
	}

	public String getFumo() {
		return fumo;
	}

	public void setFumo(String fumo) {
		this.fumo = fumo;
	}

	public String getBebo() {
		return bebo;
	}

	public void setBebo(String bebo) {
		this.bebo = bebo;
	}

	public String getMoro() {
		return moro;
	}

	public void setMoro(String moro) {
		this.moro = moro;
	}
	

}
