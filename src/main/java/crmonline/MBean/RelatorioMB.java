package crmonline.MBean;

import java.sql.SQLException;
import java.text.ParseException;
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
	String buscaDataAtualizandoALista;
	
	public RelatorioMB() {
		aDao = new AgendaDAO();
		rDao = new RelatorioDAO();
		visitaRealizada = aDao.listarAgenda("0");
	}
	public void listaPorData() throws SQLException, ParseException {
		visitaRealizada = rDao.listaAgendaKeyDown(buscaDataAtualizandoALista);
	}
	
	public String converteDate(Date date) {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		return s.format(date);
	}
	
	public String mostraCliente(Integer codigo) {
		String nomeDoCliente = "";
		String cod = codigo.toString();
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

	public String getBuscaDataAtualizandoALista() {
		return buscaDataAtualizandoALista;
	}

	public void setBuscaDataAtualizandoALista(String buscaDataAtualizandoALista) {
		this.buscaDataAtualizandoALista = buscaDataAtualizandoALista;
	}
}
