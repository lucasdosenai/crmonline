package crmonline.Entidade;

import java.util.Date;

import crmonline.MBean.LoginMB;

public class Agenda {
	public Agenda() {
		
	}
	private Integer codigo;
	private String nome;
	private String atendente;
	private Date data;
	private String hora;
	private Integer id_visitante =1 ;
	private Integer estadovisita = 0;
	private String classificacao;
	private String observacao;
	private Integer id_cliente;
	private Cliente cliente;
	private Curso cursoObj;
	private Integer curso;

	
	public Curso getCursoObj() {
		return cursoObj;
	}

	public void setCursoObj(Curso cursoObj) {
		this.cursoObj = cursoObj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getAtendente() {
		return atendente;
	}

	public void setAtendente(String atendente) {
		this.atendente = atendente;
	}
	
	public Date getData() {
		return data;
	}

	public void setData(Date date) {
		this.data = date;
	}/*
	
	public String getData() {
		return data;
	}

	public void setData(String string) {
		this.data = string;
	}
*/
	public String getHora() {
		return hora;
	}

	public void setHora(String hora) {
		this.hora = hora;
	}

	public Integer getId_visitante() {
		return id_visitante;
	}

	public void setId_visitante(Integer id_visitante) {
		this.id_visitante = id_visitante;
	}

	public Integer getEstadovisita() {
		return estadovisita;
	}

	public void setEstadovisita(Integer estadovisita) {
		this.estadovisita = estadovisita;
	}

	public String getClassificacao() {
		return classificacao;
	}

	public void setClassificacao(String classificacao) {
		this.classificacao = classificacao;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Integer getId_cliente() {
		return id_cliente;
	}

	public void setId_cliente(Integer id_cliente) {
		this.id_cliente = id_cliente;
	}

	public Integer getCurso() {
		return curso;
	}

	public void setCurso(Integer curso) {
		this.curso = curso;
	}

	public void setData(String string) {
		// TODO Auto-generated method stub
		
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
