package crmonline.MBean;

import java.sql.SQLException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import crmonline.DAO.AgendaDAO;
import crmonline.Entidade.Agenda;
import crmonline.util.Mensagem;

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

}
