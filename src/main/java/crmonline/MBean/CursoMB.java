package crmonline.MBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import crmonline.DAO.ClasseGenericaDao;
import crmonline.Entidade.Curso;

@ManagedBean
@SessionScoped
public class CursoMB {
    Curso curso;
	List<Curso> cursos;
	ClasseGenericaDao gDAO;
	
	public CursoMB () {
		
		cursos = new ArrayList<>();
		curso = new Curso();
		gDAO = new ClasseGenericaDao();
		
		try {
			cursos = gDAO.listarCurso();
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
	
	
	
	
	
	
	
	
	
	

