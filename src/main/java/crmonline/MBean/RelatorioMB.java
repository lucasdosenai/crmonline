package crmonline.MBean;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import crmonline.DAO.AgendaDAO;
import crmonline.DAO.RelatorioDAO;
import crmonline.Entidade.Agenda;

@ManagedBean
@ViewScoped
public class RelatorioMB {
	
	private List<Agenda> visitaRealizada;
	AgendaDAO aDao;
	RelatorioDAO rDao;
	
	public RelatorioMB() {
		aDao = new AgendaDAO();
		visitaRealizada = aDao.listarAgenda("0");
	}
	
	public String converteDate(Date date) {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		return s.format(date);
	}
	
	public String mostraCliente(Integer codigo) {
		String cod = codigo.toString();
		String nomeDoCliente;;
		return nomeDoCliente = rDao.clienteConverte(cod);
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

	public RelatorioDAO getrDao() {
		return rDao;
	}

	public void setrDao(RelatorioDAO rDao) {
		this.rDao = rDao;
	}
}
