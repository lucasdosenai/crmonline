package crmonline.MBean;

import java.sql.SQLException;
import java.util.ArrayList;
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

	public AgendaMB() {
		aDao = new AgendaDAO();
		agenda = new Agenda();
		classeGenericaDao = new ClasseGenericaDao();
		categorias = new ArrayList<>();
		curso = new Curso();
		cursos = new ArrayList<>();
		try {
			categorias = classeGenericaDao.buscaCategoria();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
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

	public void salvarCurso() {

		try {
			if (cDao.salvarCurso(curso)) {
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

}
