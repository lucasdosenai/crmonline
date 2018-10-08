package crmonline.Entidade;

public class Curso {
	private ClasseGenerica curso;
	private Integer VAGAS_TOTAL;
	private String DESCRI;
	private String DURACAO;
	private ClasseGenerica CATEGORIA;
	
	
	public ClasseGenerica getCurso() {
		return curso;
	}
	public void setCurso(ClasseGenerica curso) {
		this.curso = curso;
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
