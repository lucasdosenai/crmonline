package crmonline.MBean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Usuario;

@ManagedBean
public class UserMB {
	private Usuario cliente;
	private ArrayList<Usuario> clientes;
	private UserDAO uDao;
	
	public UserMB() {
		uDao = new UserDAO();
		cliente = new Usuario();
		clientes = new ArrayList<>();
		clientes = uDao.listaClientes();
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
	
	
}
