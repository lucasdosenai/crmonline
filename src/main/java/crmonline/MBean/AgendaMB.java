package crmonline.MBean;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;

import crmonline.DAO.AgendaDAO;
import crmonline.DAO.ClasseGenericaDao;
import crmonline.DAO.CursoDAO;
import crmonline.Entidade.Agenda;
import crmonline.Entidade.AgendaFiltro;
import crmonline.Entidade.ClasseGenerica;
import crmonline.Entidade.Cliente;
import crmonline.Entidade.Contato;
import crmonline.Entidade.Curso;
import crmonline.util.Mensagem;

@ManagedBean
@ViewScoped
public class AgendaMB {
	AgendaDAO aDao;
	Agenda agenda = new Agenda();
	Agenda agendaEditar = new Agenda();
	Curso curso;
	Curso novoCurso;
	List<Curso> cursos;
	Integer codigoparabuscar, buscarcurso;
	Integer id_cursofiltro;

	Contato contato;
	List<Contato> contatos;

	ClasseGenericaDao classeGenericaDao;
	List<ClasseGenerica> categorias;
	CursoDAO cDao;
	LoginMB u;
	List<Agenda> visitas;

	String dataFinal;
	Agenda visitaSelecionadaIndex;
	Integer selectOneMenu_Ativados_e_Desativados;
	boolean btDisabled = false;
	
	AgendaFiltro aFiltro;
	public AgendaMB() {

		novoCurso = new Curso();
		cDao = new CursoDAO();
		aDao = new AgendaDAO();
		classeGenericaDao = new ClasseGenericaDao();
		categorias = new ArrayList<>();
		curso = new Curso();
		cursos = new ArrayList<>();
		contato = new Contato();
		contatos = new ArrayList<>();
		visitas = new ArrayList<>();
		visitas = aDao.listarAgenda("0");
		visitaSelecionadaIndex = new Agenda();
		aFiltro = new AgendaFiltro();
		selectOneMenu_Ativados_e_Desativados = 0;
		try {
			cursos = classeGenericaDao.listarCurso();
			categorias = classeGenericaDao.buscaCategoria();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}
	
	public void filtroAgenda() {
		try {
			visitas = aDao.listaFiltro_0_1(aDao.listaFiltro(aFiltro),selectOneMenu_Ativados_e_Desativados);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void visualizarConvertendoData() {
		dataFinal = "" + converteDate(agenda.getData()) + "";
	}

	public String agendar() throws SQLException {
		if (aDao.inserir(agenda)) {
			visitas = aDao.listarAgenda("0");
			agenda = new Agenda();
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Agora voc� tem uma nova visita agendada"));
			PrimeFaces.current().executeScript("PF('novaVisita').hide();");
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao inserir nova visita!"));
		}
		return "";
	}

	public void editaVisita() {
		if (aDao.updateVisita(agendaEditar)) {
			visitas = aDao.listarAgenda("0");
			agendaEditar = new Agenda();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alterado com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao Alterar cliente!"));
		}
	}

	public void realizaVisitaIndex() {
		try {
			if (aDao.realizaVisita(visitaSelecionadaIndex)) {
				visitas = aDao.listarAgenda("0");
				agenda = new Agenda();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Visita Realizada com Sucesso!"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		visitaSelecionadaIndex = new Agenda();
	}

	public void controlevisita() {
		if (selectOneMenu_Ativados_e_Desativados == 0) {
			btDisabled = false;
		} else {
			btDisabled = true;
		}
		visitas = new ArrayList<Agenda>();
		try {
			visitas = aDao.listaFiltro_0_1(aDao.listaFiltro(aFiltro),selectOneMenu_Ativados_e_Desativados);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void realizaVisita() {
		try {
			if (aDao.realizaVisita(agenda)) {
				visitas = aDao.listarAgenda("0");
				agenda = new Agenda();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Visita Realizada com Sucesso!"));
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
		agenda = new Agenda();
	}

	public void excluiVisita() throws SQLException {
		if (aDao.excluiVisita(agenda.getCodigo())) {
			visitas = aDao.listarAgenda("0");
			agenda = new Agenda();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluido com Sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao Excluir!"));
		}

	}

	public String converteDate(Date date) {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		return s.format(date);
	}

	public void listacliente() throws ParseException {
		if (agenda.getId_cliente() != 0)
			visitas = aDao.filtrocliente(agenda.getId_cliente());
		else
			visitas = aDao.listarAgenda("0");
	}

	public void listacurso() throws ParseException {
		if (curso.getId() != 0) {
			visitas = aDao.filtroCURSO(curso.getId());
		} else {
			visitas = aDao.listarAgenda("0");
		}
	}

	public Integer getBuscarcurso() {
		return buscarcurso;
	}

	public void setBuscarcurso(Integer buscarcurso) {
		this.buscarcurso = buscarcurso;
	}

	public Integer getCodigoparabuscar() {
		return codigoparabuscar;
	}

	public void setCodigoparabuscar(Integer codigoparabuscar) {
		this.codigoparabuscar = codigoparabuscar;
	}

	public Curso getNovoCurso() {
		return novoCurso;
	}

	public void setNovoCurso(Curso novoCurso) {
		this.novoCurso = novoCurso;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public Integer getSelectOneMenu_Ativados_e_Desativados() {
		return selectOneMenu_Ativados_e_Desativados;
	}

	public void setSelectOneMenu_Ativados_e_Desativados(Integer selectOneMenu_Ativados_e_Desativados) {
		this.selectOneMenu_Ativados_e_Desativados = selectOneMenu_Ativados_e_Desativados;
	}

	public Integer getId_cusofiltro() {
		return id_cursofiltro;
	}

	public void setId_cusofiltro(Integer id_cusofiltro) {
		this.id_cursofiltro = id_cusofiltro;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public AgendaDAO getaDao() {
		return aDao;
	}

	public void setaDao(AgendaDAO aDao) {
		this.aDao = aDao;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public AgendaFiltro getaFiltro() {
		return aFiltro;
	}

	public void setaFiltro(AgendaFiltro aFiltro) {
		this.aFiltro = aFiltro;
	}

	public ClasseGenericaDao getClasseGenericaDao() {
		return classeGenericaDao;
	}

	public void setClasseGenericaDao(ClasseGenericaDao classeGenericaDao) {
		this.classeGenericaDao = classeGenericaDao;
	}

	public List<ClasseGenerica> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<ClasseGenerica> categorias) {
		this.categorias = categorias;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public List<Curso> getCursos() {
		return cursos;
	}

	public void setCursos(List<Curso> cursos) {
		this.cursos = cursos;
	}

	public CursoDAO getcDao() {
		return cDao;
	}

	public void setcDao(CursoDAO cDao) {
		this.cDao = cDao;
	}

	public List<Agenda> getVisitas() {
		return visitas;
	}

	public void setVisitas(List<Agenda> visitas) {
		this.visitas = visitas;
	}

	public void salvarCurso() {
		try {
			if (cDao.salvarCurso(novoCurso)) {
				cursos = classeGenericaDao.listarCurso();
				Mensagem.make("Curso " + curso.getNome() + " inserido com sucesso !");
			} else {
				Mensagem.make("Falha ao salvar curso !");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public String getDate(Date date) {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		return s.format(date);
	}

	public LoginMB getU() {
		return u;
	}

	public void setU(LoginMB u) {
		this.u = u;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public Integer getId_cursofiltro() {
		return id_cursofiltro;
	}

	public void setId_cursofiltro(Integer id_cursofiltro) {
		this.id_cursofiltro = id_cursofiltro;
	}

	public Agenda getVisitaSelecionadaIndex() {
		return visitaSelecionadaIndex;
	}

	public void setVisitaSelecionadaIndex(Agenda visitaSelecionadaIndex) {
		this.visitaSelecionadaIndex = visitaSelecionadaIndex;
	}

	public boolean isBtDisabled() {
		return btDisabled;
	}

	public void setBtDisabled(boolean btDisabled) {
		this.btDisabled = btDisabled;
	}

	public Agenda getAgendaEditar() {
		return agendaEditar;
	}

	public void setAgendaEditar(Agenda agendaEditar) {
		this.agendaEditar = agendaEditar;
	}

}
