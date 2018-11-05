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
import crmonline.Entidade.Cargo;
import crmonline.Entidade.Cliente;
import crmonline.Entidade.Contato;

@ManagedBean
@ViewScoped
public class ContatosMB {
ClienteDAO cDAO;
	private Contato contato;
	private List<Cargo> cargos;
	private List<Cliente> clientes;
	Integer cogigocliente;
	
	
	
	public ContatosMB() {
		cDAO = new ClienteDAO();
		contato = new Contato();
		CargoDAO carDao = new CargoDAO();
		cargos = carDao.listarcargo();
		clientes = cDAO.listaCliente();
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Cargo> getCargos() {
		return cargos;
	}

	public void setCargos(List<Cargo> cargos) {
		this.cargos = cargos;
	}

	public ClienteDAO getcDAO() {
		return cDAO;
	}

	public void setcDAO(ClienteDAO cDAO) {
		this.cDAO = cDAO;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}

	public Integer getCogigocliente() {
		return cogigocliente;
	}

	public void setCogigocliente(Integer cogigocliente) {
		this.cogigocliente = cogigocliente;
	}
	
	
}
