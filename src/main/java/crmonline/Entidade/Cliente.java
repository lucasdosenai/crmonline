package crmonline.Entidade;

public class Cliente {

	public Cliente() {
		categoria = new ClasseGenerica();
	}

	private Integer codigo;
	private String nome;
	private String numeroFuncionario;
	private String cnjp;
	private String telefone;
	private String email;
	private String logradouro;
	private String cidade;
	private Integer statu;
	private ClasseGenerica categoria;

	
	public Integer getStatu() {
		return statu;
	}

	public void setStatu(Integer statu) {
		this.statu = statu;
	}

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

	public ClasseGenerica getCategoria() {
		return categoria;
	}

	public void setCategoria(ClasseGenerica categoria) {
		this.categoria = categoria;
	}

}
