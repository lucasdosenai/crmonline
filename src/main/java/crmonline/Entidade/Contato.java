package crmonline.Entidade;

public class Contato {
	private String nome;
	private String telefone;
	private String celular;
	private String email;
	private ClasseGenerica cliente = new ClasseGenerica();
	private ClasseGenerica cargo = new ClasseGenerica();
	private Integer id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ClasseGenerica getCliente() {
		return cliente;
	}

	public void setCliente(ClasseGenerica cliente) {
		this.cliente = cliente;
	}

	public ClasseGenerica getCargo() {
		return cargo;
	}

	public void setCargo(ClasseGenerica cargo) {
		this.cargo = cargo;
	}

}
