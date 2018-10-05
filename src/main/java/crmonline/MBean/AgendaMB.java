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
import crmonline.Entidade.Agenda;
import crmonline.Entidade.ClasseGenerica;
import crmonline.util.Mensagem;

@ViewScoped
@ManagedBean
public class AgendaMB {

	AgendaDAO aDao;
	Agenda agenda;

	ClasseGenericaDao classeGenericaDao;

	List<ClasseGenerica> categorias;

	public AgendaMB() {
		aDao = new AgendaDAO();
		agenda = new Agenda();
		classeGenericaDao = new ClasseGenericaDao();
		categorias = new ArrayList<>();
		try {
			categorias = classeGenericaDao.buscaCategoria();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
	}

	public String agendar() {
		try {
			if (aDao.inserir(agenda)) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Visita Agendada!"));
				return "pags/agendamentos?faces-redirect=true";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			Mensagem.make(e.toString());
		}
		return null;
	}

	public Agenda getAgenda() {
		return agenda;
	}

	public void setAgenda(Agenda agenda) {
		this.agenda = agenda;
	}

	public List<ClasseGenerica> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<ClasseGenerica> categorias) {
		this.categorias = categorias;
	}

}
