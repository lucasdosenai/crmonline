package crmonline.MBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import crmonline.DAO.ClasseGenericaDao;
import crmonline.DAO.CursoDAO;
import crmonline.Entidade.Curso;

@ManagedBean
@SessionScoped
public class CursoMB {
    Curso curso;
	List<Curso> cursos;
	ClasseGenericaDao gDAO;
	CursoDAO cDAO;
	
	public CursoMB () {
		
		cursos = new ArrayList<>();
		curso = new Curso();
		gDAO = new ClasseGenericaDao();
		cDAO = new CursoDAO();
		
		try {
			cursos = gDAO.listarCurso();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void salvarCurso() {
		
		try {
			if(cDAO.salvarCurso(curso)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Curso Inserido!"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
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

	public ClasseGenericaDao getgDAO() {
		return gDAO;
	}

	public void setgDAO(ClasseGenericaDao gDAO) {
		this.gDAO = gDAO;
	}
	
	
}
	
	
	
	
	
	
	
	
	
	

