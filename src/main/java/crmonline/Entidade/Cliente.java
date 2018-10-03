package crmonline.Entidade;

import crmonline.MBean.ClienteMB;

public class Cliente {
	
	private Integer codigo;
	private String  nome;
	private String numeroFuncionario;
	private String  cnjp;
	private String 	telefone;
	private String  email;
	private String  logradouro;
	private String  cidade;
	private String categoriaNome = new ClienteMB().categoriaCliente(getCod_categoria().toString());
	private Integer cod_categoria;
	
	
	
	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNumeroFuncionario() {
		return numeroFuncionario;
	}

	public void setNumeroFuncionario(String numeroFuncionario) {
		this.numeroFuncionario = numeroFuncionario;
	}

	public String getCnjp() {
		return cnjp;
	}

	public void setCnjp(String cnjp) {
		this.cnjp = cnjp;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public Integer getCod_categoria() {
		return cod_categoria;
	}

	public void setCod_categoria(Integer cod_categoria) {
		this.cod_categoria = cod_categoria;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCategoriaNome() {
		return categoriaNome;
	}

	public void setCategoriaNome(String categoriaNome) {
		this.categoriaNome = categoriaNome;
	}
	
}
