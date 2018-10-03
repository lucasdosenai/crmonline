package crmonline.MBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import crmonline.DAO.AgendaDAO;
import crmonline.Entidade.Agenda;

@ViewScoped
@ManagedBean
public class AgendaMB {

	AgendaDAO aDao;
	Agenda agenda;
	
	public AgendaMB() {
		aDao = new AgendaDAO();
		agenda = new Agenda();
		
	}
	
	public String agendar() {
		if(aDao.inserir(agenda)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Visita Agendada!"));
			return "pags/agendamentos?faces-redirect=true";
		}else {
			return null;
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
	
	
	
	
	
	
}
