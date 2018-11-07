package crmonline.MBean;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import crmonline.DAO.AgendaDAO;
import crmonline.DAO.ClasseGenericaDao;
import crmonline.DAO.CursoDAO;
import crmonline.Entidade.Agenda;
import crmonline.Entidade.ClasseGenerica;
import crmonline.Entidade.Curso;
import crmonline.util.Mensagem;

@ManagedBean
@ViewScoped
public class AgendaMB {
	AgendaDAO aDao;
	Agenda agenda;
	Curso curso;
	List<Curso> cursos;

	ClasseGenericaDao classeGenericaDao;
	List<ClasseGenerica> categorias;
	CursoDAO cDao = new CursoDAO();
	LoginMB u;
	List<Agenda> visitas;

	public AgendaMB() {
		aDao = new AgendaDAO();
		agenda = new Agenda();
		classeGenericaDao = new ClasseGenericaDao();
		categorias = new ArrayList<>();
		curso = new Curso();
		cursos = new ArrayList<>();
		visitas = new ArrayList<>();
		visitas = aDao.listarAgenda("0");
		try {
			cursos = classeGenericaDao.listarCurso();
			categorias = classeGenericaDao.buscaCategoria();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public String agendar() throws SQLException {
		if (aDao.inserir(agenda)) {
			visitas = aDao.listarAgenda("0");
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Agora você tem uma nova visita agendada"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao inserir nova visita!"));
		}
		return "";
	}

	public void editaVisita() {
		if (aDao.updateVisita(agenda)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alterado com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao Alterar cliente!"));
		}
	}

	public void realizaVisita() {
		try {
			if (aDao.realizaVisita(agenda)) {
				visitas = aDao.listarAgenda("0");
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Visita Realizada com Sucesso!"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(e.getMessage()));
		}
	}

	public void excluiVisita() throws SQLException {
		if (aDao.excluiVisita(agenda.getCodigo())) {
			visitas = aDao.listarAgenda("0");
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluido com Sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao Excluir!"));
		}

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
			if (cDao.salvarCurso(curso)) {
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

}
