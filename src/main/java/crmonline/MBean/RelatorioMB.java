package crmonline.MBean;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import crmonline.DAO.AgendaDAO;
import crmonline.DAO.ClienteDAO;
import crmonline.DAO.RelatorioDAO;
import crmonline.Entidade.Agenda;
import crmonline.Entidade.Cliente;

@ManagedBean
@ViewScoped
public class RelatorioMB {
	
	private List<Agenda> visitaRealizada;
	private List<Cliente> listaClientesDropDown;
	
	AgendaDAO aDao;
	ClienteDAO cDao;
	RelatorioDAO rDao;
	
	Agenda visitaSelecionada;
	
	boolean renderizaInformaDialog = false;
	
	String buscaDataAtualizandoALista;
	String dataParaBuscarNoBancoDeDados;
	
	Integer clienteSelecionadoParaBusca;
	public RelatorioMB() {
		aDao = new AgendaDAO();
		rDao = new RelatorioDAO();
		cDao = new ClienteDAO();
		
		visitaSelecionada = new Agenda();
		
		visitaRealizada = aDao.listarAgenda("1");
		listaClientesDropDown = cDao.listaCliente(1);
	}
	
	public boolean retornoRenderiza() {
		renderizaInformaDialog = !renderizaInformaDialog;
		return renderizaInformaDialog;
	}
	
	public void x() {
		System.out.println("Entrou no método de listaPorData");
				visitaRealizada = rDao.listaAgendaKeyDown(dataParaBuscarNoBancoDeDados);
				FacesContext.getCurrentInstance().addMessage("VISITAS ATUALIZADAS",
						new FacesMessage("ATUALIZADO"));
	}
	
	public void listaPorData() throws SQLException, ParseException {	
		System.out.println("Entrou no método de listaPorData");
		
			visitaRealizada = rDao.listaAgendaKeyDown(dataParaBuscarNoBancoDeDados);
			FacesContext.getCurrentInstance().addMessage("VISITAS ATUALIZADAS",
					new FacesMessage("ATUALIZADO"));
		
	}
	public void filtrarClientes() throws SQLException, ParseException {
		if(clienteSelecionadoParaBusca != 0) 
			visitaRealizada = rDao.listaRelatorioPorTipo(clienteSelecionadoParaBusca, "0");
		else visitaRealizada = aDao.listarAgenda("1");
			
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
	public String getDataParaBuscarNoBancoDeDados() {
		return dataParaBuscarNoBancoDeDados;
	}
	public void setDataParaBuscarNoBancoDeDados(String dataParaBuscarNoBancoDeDados) {
		this.dataParaBuscarNoBancoDeDados = dataParaBuscarNoBancoDeDados;
	}

	public List<Cliente> getListaClientesDropDown() {
		return listaClientesDropDown;
	}

	public void setListaClientesDropDown(List<Cliente> listaClientesDropDown) {
		this.listaClientesDropDown = listaClientesDropDown;
	}

	public ClienteDAO getcDao() {
		return cDao;
	}

	public void setcDao(ClienteDAO cDao) {
		this.cDao = cDao;
	}

	public Integer getClienteSelecionadoParaBusca() {
		return clienteSelecionadoParaBusca;
	}

	public void setClienteSelecionadoParaBusca(Integer clienteSelecionadoParaBusca) {
		this.clienteSelecionadoParaBusca = clienteSelecionadoParaBusca;
	}


	public Agenda getVisitaSelecionada() {
		return visitaSelecionada;
	}


	public void setVisitaSelecionada(Agenda visitaSelecionada) {
		this.visitaSelecionada = visitaSelecionada;
	}


	public boolean isRenderizaInformaDialog() {
		return renderizaInformaDialog;
	}


	public void setRenderizaInformaDialog(boolean renderizaInformaDialog) {
		this.renderizaInformaDialog = renderizaInformaDialog;
	}
	
}
