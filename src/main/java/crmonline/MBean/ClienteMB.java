package crmonline.MBean;

import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import crmonline.DAO.ClienteDAO;
import crmonline.Entidade.Categoria;
import crmonline.Entidade.Cliente;

@ManagedBean
public class ClienteMB {
	
	private Cliente cliente;
	private ArrayList<Categoria> categorias;
	private ArrayList<Cliente> clientes;
	ClienteDAO cDao;
	String categoria;
	
	public ClienteMB() {
		cliente    = new Cliente();
		cDao	   = new ClienteDAO();
		categorias = new ArrayList<>();
		clientes   = new ArrayList<>();
		
		
	//  Retorna uma lista de categorias preenchida 
		// categorias = cDao.listaCategorias();
		clientes = cDao.listaCliente();
		
	}
	
	/*
	 categoria = cDao.categoriaCliente(cliente.getCod_categoria().toString());
		cliente.setCategoriaNome(categoria);
	  */
	
	public String categoriaCliente(String codigo) {
		categoria = cDao.categoriaCliente(codigo);
		cliente.setCategoriaNome(categoria);
		return cliente.getCategoriaNome();
	}
	public void novoCliente() {
		if(cliente != null) {
			if(cDao.novoCliente(cliente)) {
				cliente = new Cliente();
				FacesContext.getCurrentInstance().addMessage("ALERTA" , 
						new FacesMessage(cliente.getNome() + " ADICIONADO COM SUCESSO!"));
			}else {
				FacesContext.getCurrentInstance().addMessage("ALERTA", 
						new FacesMessage("FALHA AO INSERIR"));
			}
			System.out.println(cliente.getNome() + ":" + cliente.getNumeroFuncionario());
		}else {
			FacesContext.getCurrentInstance().addMessage("ALERTA" , 
					new FacesMessage("Cliente vazio"));
		}
	}
	
	/* GET and SET*/
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public ArrayList<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(ArrayList<Categoria> categorias) {
		this.categorias = categorias;
	}

	public ClienteDAO getcDao() {
		return cDao;
	}

	public void setcDao(ClienteDAO cDao) {
		this.cDao = cDao;
	}

	public ArrayList<Cliente> getClientes() {
		return clientes;
	}

	public void setClientes(ArrayList<Cliente> clientes) {
		this.clientes = clientes;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	
	/*            */
	
}
