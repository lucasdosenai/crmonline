package crmonline.MBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import crmonline.DAO.AgendaDAO;
import crmonline.Entidade.Agenda;

@ManagedBean
@ViewScoped
public class RelatorioMB {
	
	private List<Agenda> visitaRealizada;
	AgendaDAO aDao;
	
	public RelatorioMB() {
		aDao = new AgendaDAO();
		visitaRealizada = aDao.listarAgenda("0");
	}
	
	public String converteDate(Date date) {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		return s.format(date);
	}
	
	public List<Agenda> getVisitaRealizada() {
		return visitaRealizada;
	}

	public void setVisitaRealizada(List<Agenda> visitaRealizada) {
		this.visitaRealizada = visitaRealizada;
	}

	public AgendaDAO getaDao() {
		return aDao;
	}

	public void setaDao(AgendaDAO aDao) {
		this.aDao = aDao;
	}
	
}
