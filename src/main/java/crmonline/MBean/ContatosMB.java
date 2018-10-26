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
	private List<Contato> contatos;
	private ClienteDAO cDao;
	
	public ContatosMB() {
		contato = new Contato();
		contatos = new ArrayList<>();
		cDao = new ClienteDAO();
	
		
	}

	public Contato getContato() {
		return contato;
	}

	public void setContato(Contato contato) {
		this.contato = contato;
	}

	public List<Contato> getContatos() {
		return contatos;
	}

	public void setContatos(List<Contato> contatos) {
		this.contatos = contatos;
	}

	public ClienteDAO getcDao() {
		return cDao;
	}

	public void setcDao(ClienteDAO cDao) {
		this.cDao = cDao;
	}

	
}
