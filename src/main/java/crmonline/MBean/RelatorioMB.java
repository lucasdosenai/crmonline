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
	
	String acao = "";
	
	String buscaDataAtualizandoALista;
	String dataParaBuscarNoBancoDeDados;
	
	Cliente clienteSelecionadoParaBusca;
	
	public RelatorioMB() {
		aDao = new AgendaDAO();
		rDao = new RelatorioDAO();
		cDao = new ClienteDAO();
		
		clienteSelecionadoParaBusca = new Cliente();
		visitaRealizada = aDao.listarAgenda("0");
		listaClientesDropDown = cDao.listaCliente(1);
	}
	
	public void buscarRelatorios() {
		try {
			visitaRealizada = rDao.listaRelatorioPorTipo(clienteSelecionadoParaBusca.getCodigo(), acao);
			if(visitaRealizada.size() > -1){
				FacesContext.getCurrentInstance().addMessage(null,
						new FacesMessage("BUSCA REALIZADA"));
			}
		} catch (SQLException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	} 
	
	public void x() {
		System.out.println("Entrou no método de listaPorData");
	
			if(rDao.listaAgendaKeyDown(dataParaBuscarNoBancoDeDados).size() > 1) {
				visitaRealizada = rDao.listaAgendaKeyDown(dataParaBuscarNoBancoDeDados);
				FacesContext.getCurrentInstance().addMessage("VISITAS ATUALIZADAS",
						new FacesMessage("ATUALIZADO"));
			}else {
				visitaRealizada = null;
				FacesContext.getCurrentInstance().addMessage("PROBLEMAS COM VISITAS",
						new FacesMessage("PROBLEMAS"));
			}
	}
	
	public void listaPorData() throws SQLException, ParseException {	
		System.out.println("Entrou no método de listaPorData");
		if(rDao.listaAgendaKeyDown(dataParaBuscarNoBancoDeDados).size() > -1) {
			visitaRealizada = rDao.listaAgendaKeyDown(dataParaBuscarNoBancoDeDados);
			FacesContext.getCurrentInstance().addMessage("VISITAS ATUALIZADAS",
					new FacesMessage("ATUALIZADO"));
		}else {
			// visitaRealizada = null;
			FacesContext.getCurrentInstance().addMessage("PROBLEMAS COM VISITAS",
					new FacesMessage("PROBLEMAS"));
		}
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

	public Cliente getClienteSelecionadoParaBusca() {
		return clienteSelecionadoParaBusca;
	}

	public void setClienteSelecionadoParaBusca(Cliente clienteSelecionadoParaBusca) {
		this.clienteSelecionadoParaBusca = clienteSelecionadoParaBusca;
	}

	public String getAcao() {
		return acao;
	}

	public void setAcao(String acao) {
		this.acao = acao;
	}
	
}
