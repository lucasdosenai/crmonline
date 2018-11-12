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
	private List<Cargo> cargos;
	private List<Cliente> clientes;
	private Contato contato;
	ContatoDAO cDAO;
	Cargo cargo = new Cargo();
	CargoDAO carDAO = new CargoDAO();

	private List<Contato> contatos;

	public ContatosMB() {
		cDAO = new ContatoDAO();
		contato = new Contato();
		carDAO = new CargoDAO();
		contatos = cDAO.listarcontato();
		cargos = carDAO.listarcargo();
		ClienteDAO cliDao = new ClienteDAO();
		clientes = cliDao.listaCliente(1);
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

	public List<Contato> getContatos() {
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

}
