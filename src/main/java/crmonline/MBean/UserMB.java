package crmonline.MBean;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;

import crmonline.DAO.AgendaDAO;
import crmonline.DAO.RelatorioDAO;
import crmonline.DAO.UserDAO;
import crmonline.Entidade.Agenda;
import crmonline.Entidade.Usuario;

@ManagedBean
@ViewScoped
public class UserMB {
	private Usuario cliente;
	private ArrayList<Usuario> clientes;
	private UserDAO uDao;

	@ManagedProperty("#{loginMB}")
	private LoginMB usuarioLogado;
	
	@PostConstruct
	public void post() {
		usuarioLog = usuarioLogado.getUserAtual();
	}
	
	Usuario usuarioLog;
	private RelatorioMB rMB;

	public List<Agenda> visitaRealizada;
	AgendaDAO aDao;
	RelatorioDAO rDao;
	
	String buscaDataAtualizandoALista;

	List<Agenda> lista;

	public UserMB() {
		uDao = new UserDAO();
		cliente = new Usuario();
		clientes = new ArrayList<>();
		aDao = new AgendaDAO();
		clientes = uDao.listaClientes();
		rMB = new RelatorioMB();
		rDao = new RelatorioDAO();
		visitaRealizada = aDao.listarAgenda("0");

		lista = new ArrayList<>();
		lista = rMB.visitaRealizada;
	}

	
	

	public Usuario getCliente() {
		return cliente;
	}

	public void setCliente(Usuario cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Usuario> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Usuario> clientes) {
		this.clientes = clientes;
	}

	public UserDAO getuDao() {
		return uDao;
	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

	public LoginMB getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(LoginMB usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	public RelatorioMB getrMB() {
		return rMB;
	}

	public void setrMB(RelatorioMB rMB) {
		this.rMB = rMB;
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

	public List<Agenda> getLista() {
		return lista;
	}

	public void setLista(List<Agenda> lista) {
		this.lista = lista;
	}

	public Usuario getUsuarioLog() {
		return usuarioLog;
	}

	public void setUsuarioLog(Usuario usuarioLog) {
		this.usuarioLog = usuarioLog;
	}

}
