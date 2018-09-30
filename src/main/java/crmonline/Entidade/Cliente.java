package crmonline.Entidade;

public class Cliente {
	
	private Integer codigo;
	private String  nome;
	private Integer numeroFuncionario;
	private Integer cnjp;
	private String  logradouro;
	private String  cidade;
	
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

	public Integer getNumeroFuncionario() {
		return numeroFuncionario;
	}

	public void setNumeroFuncionario(Integer numeroFuncionario) {
		this.numeroFuncionario = numeroFuncionario;
	}

	public Integer getCnjp() {
		return cnjp;
	}

	public void setCnjp(Integer cnjp) {
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
}
