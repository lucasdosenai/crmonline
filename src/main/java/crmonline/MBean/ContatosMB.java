package crmonline.MBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;

import crmonline.DAO.ClienteDAO;
import crmonline.Entidade.Cliente;
import crmonline.Entidade.Contato;

@ManagedBean
public class ContatosMB {
	
	private Contato contato;
	private List<Cliente> clientes;
	private ClienteDAO cDao;
	
	public ContatosMB() {
		contato = new Contato();
		clientes = new ArrayList<>();
		cDao = new ClienteDAO();
		clientes = cDao.listaCliente();
		
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(List<Cliente> clientes) {
		this.clientes = clientes;
	}
	
}
