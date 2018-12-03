package crmonline.MBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import crmonline.DAO.CargoDAO;
import crmonline.DAO.ClienteDAO;
import crmonline.DAO.ContatoDAO;
import crmonline.Entidade.Agenda;
import crmonline.Entidade.Cargo;
import crmonline.Entidade.Cliente;
import crmonline.Entidade.Contato;

@ManagedBean
@ViewScoped
public class ContatosMB {
	private List<Cargo> cargos;
	private List<Cliente> clientes;
	private Contato contato;
	ContatoDAO cDAO;
	List<Contato> listacontato;
	Cargo cargo = new Cargo();
	CargoDAO carDAO = new CargoDAO();
	ClienteDAO cliDao ;
	
	Integer codigoCargoParaBuscar,codigoClienteParaBuscar;
	String emailCompleto = "";
	// String testeEmail = listaCompletaEmail();
	private List<Contato> contatos;

	public ContatosMB() {
		cDAO = new ContatoDAO();
		contato = new Contato();
		carDAO = new CargoDAO();
		
		contatos = cDAO.listarcontato();
		cargos = carDAO.listarcargo();
		cliDao = new ClienteDAO();
		clientes = cliDao.listaCliente(1);
		listacontato = new ArrayList<>();
		listacontato = cDAO.listarcontato();
	}
	
	
	public void listaCompletaEmail() {
		emailCompleto = "";
		for(Contato contato : contatos) {
			emailCompleto += contato.getEmail() + ",";
		}
	}
	
	public void buscaCliente() {
		contatos = cDAO.listarClienteContato(codigoClienteParaBuscar);
	}
	public void buscarCargo() {
		contatos = cDAO.listarCargoContato(codigoCargoParaBuscar);
	} 
	
	public void inserircontato() throws SQLException {
		if (cDAO.inserircontato(contato)) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Contato cadastrado"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao inserir!"));
		}

	}

	public void salvarCargo() throws SQLException {
		if (carDAO.inserircargo(cargo)) {
			cargos = carDAO.listarcargo();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Cargo cadastrado"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao inserir!"));
		}
	}
	
	public void editacago() {
		if (cDAO.editarContato(contato)){
			
			
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Alterado com sucesso!"));
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao Alterar cliente!"));
		}
	}
	
	public void excluiVisita() {
		try {
			if (cDAO.excluiVisita(contato.getId())) {
				contatos = cDAO.listarcontato();
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Excluido com Sucesso!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao Excluir!"));
			}
		} catch (SQLException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Problema ao Excluir!"));
			e.printStackTrace();
		}

	}
	
	public String getEmailCompleto() {
		return emailCompleto;
	}


	public void setEmailCompleto(String emailCompleto) {
		this.emailCompleto = emailCompleto;
	}


	public Integer getCodigoClienteParaBuscar() {
		return codigoClienteParaBuscar;
	}

	public void setCodigoClienteParaBuscar(Integer codigoClienteParaBuscar) {
		this.codigoClienteParaBuscar = codigoClienteParaBuscar;
	}

	public Integer getCodigoCargoParaBuscar() {
		return codigoCargoParaBuscar;
	}

	public void setCodigoCargoParaBuscar(Integer codigoCargoParaBuscar) {
		this.codigoCargoParaBuscar = codigoCargoParaBuscar;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public List<Contato> visualizar() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public ContatoDAO getcDAO() {
		return cDAO;
	}

	public void setcDAO(ContatoDAO cDAO) {
		this.cDAO = cDAO;
	}

	public Cargo getCargo() {
		return cargo;
	}

	public void setCargo(Cargo cargo) {
		this.cargo = cargo;
	}

	public CargoDAO getCarDAO() {
		return carDAO;
	}

	public void setCarDAO(CargoDAO carDAO) {
		this.carDAO = carDAO;
	}

	public List<Contato> getListacontato() {
		return listacontato;
	}

	public void setListacontato(List<Contato> listacontato) {
		this.listacontato = listacontato;
	}

	public ClienteDAO getCliDao() {
		return cliDao;
	}

	public void setCliDao(ClienteDAO cliDao) {
		this.cliDao = cliDao;
	}

	public List<Contato> getContatos() {
		return contatos;
	}
	
}
