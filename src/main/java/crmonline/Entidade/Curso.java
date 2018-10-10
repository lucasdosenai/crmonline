package crmonline.Entidade;

public class Curso {
	private Integer id;
	private String nome;
	private Integer VAGAS_TOTAL;
	private String DESCRI;
	private String DURACAO;
	private ClasseGenerica CATEGORIA;
	
	public Curso() {
		CATEGORIA = new ClasseGenerica();
	}

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

	public Integer getVAGAS_TOTAL() {
		return VAGAS_TOTAL;
	}

	public void setVAGAS_TOTAL(Integer vAGAS_TOTAL) {
		VAGAS_TOTAL = vAGAS_TOTAL;
	}

	public String getDESCRI() {
		return DESCRI;
	}

	public void setDESCRI(String dESCRI) {
		DESCRI = dESCRI;
	}

	public String getDURACAO() {
		return DURACAO;
	}

	public void setDURACAO(String dURACAO) {
		DURACAO = dURACAO;
	}

	public ClasseGenerica getCATEGORIA() {
		return CATEGORIA;
	}

	public void setCATEGORIA(ClasseGenerica cATEGORIA) {
		CATEGORIA = cATEGORIA;
	}

}
