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
import crmonline.Entidade.Cargo;
import crmonline.Entidade.Cliente;
import crmonline.Entidade.Contato;

@ManagedBean
@ViewScoped
public class ContatosMB {

	private Contato contato;
	private Cliente usuario;
	private List<Contato> contatos;
	private ClienteDAO cDao;
	private ArrayList<Cliente> clientes;
	private List<Cargo> listaCargo;
	private CargoDAO aDAO;

	public ContatosMB() {
		contato = new Contato();
		usuario = new Cliente();
		contatos = new ArrayList<>();
		cDao = new ClienteDAO();
		listaCargo = new ArrayList<>();
		aDAO = new CargoDAO();
		clientes = new ArrayList<>();

		listaCargo("TODAS");
	}

	public void listaCargo(String codigo) {
		if (codigo.equals("TODAS"))
			listaCargo = aDAO.listarcargo();
		else
			clientes = cDao.listaCategoriaCliente(codigo);

	}

	public void inserirContato() {
		if(contato != null) {
			if(cDao.inserirContato(contato)) {
				FacesContext.getCurrentInstance().addMessage("ALERTA" , 
						new FacesMessage(contato.getNome() + " ADICIONADO COM SUCESSO!"));
				clientes.add(contato);
				
			}else {
				FacesContext.getCurrentInstance().addMessage("ALERTA", 
						new FacesMessage("FALHA AO INSERIR"));
			}
			contato = inserirContato();
			
		}else {
			FacesContext.getCurrentInstance().addMessage("ALERTA" , 
					new FacesMessage("Cliente vazio"));
		}
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

	public List<Cargo> getListaCargo() {
		return listaCargo;
	}

	public void setListaCargo(List<Cargo> listaCargo) {
		this.listaCargo = listaCargo;
	}

	public Cliente getUsuario() {
		return usuario;
	}

	public void setUsuario(Cliente usuario) {
		this.usuario = usuario;
	}

}
