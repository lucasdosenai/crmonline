package crmonline.MBean;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import crmonline.DAO.ClienteDAO;
import crmonline.Entidade.Categoria;
import crmonline.Entidade.Cliente;

@SessionScoped
@ManagedBean
public class ClienteMB {
	
	private Cliente cliente;
	private Cliente clienteEdita;
	private ArrayList<Categoria> categorias;
	private ArrayList<Cliente> clientes;
	ClienteDAO cDao;
	String categoria;
	private String categoriaEscolhida;
	
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
	
	public void removeBean(String bean){
        FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove(bean);
    }
	public void desativados() {
		clientes = cDao.listaDesativados();
	}
	public void testando() {
		listaCategorias(categoriaEscolhida);
	}
	public void desativarCliente(Cliente c) throws SQLException {
		if(cDao.desativarAtivarUsuario(c)) {
			if(c.getStatu() == 0) FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario " + c.getNome() + " Desativado com sucesso!"));
			else FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario " + c.getNome() + " Ativado com sucesso!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Usuario " + c.getNome() + " Usuario não desativado"));
		}
		clientes = cDao.listaCliente();
	}
	
	public void listaCategorias(String codigo) {
		if(codigo.equals("TODAS")) 
		clientes = cDao.listaCliente();
		else clientes = cDao.listaCategoriaCliente(codigo);
		
	}
	public void editaCliente() {
		if(cDao.updateCliente(cliente)) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Alterado com sucesso!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Problema ao Alterar cliente!"));
		}
	}
	public void buscaEditaCliente() {
		cliente = cDao.editaCliente(cliente);
		System.out.println("Edita Cliente Acessado!");
	} 
	
	public void apagaCliente(Cliente c) {
		try {
			cDao.deletaCliente(c.getCodigo().toString());
			clientes.remove(c);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void novoCliente() {
		if(cliente != null) {
			if(cDao.novoCliente(cliente)) {
				FacesContext.getCurrentInstance().addMessage("ALERTA" , 
						new FacesMessage(cliente.getNome() + " ADICIONADO COM SUCESSO!"));
				clientes.add(cliente);
				
			}else {
				FacesContext.getCurrentInstance().addMessage("ALERTA", 
						new FacesMessage("FALHA AO INSERIR"));
			}
			cliente = new Cliente();
			System.out.println(cliente.getNome() + ":" + cliente.getNumeroFuncionario());
		}else {
			FacesContext.getCurrentInstance().addMessage("ALERTA" , 
					new FacesMessage("Cliente vazio"));
		}
	}
	
	/* GET and SET*/
	public Cliente clienteEdita() {
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

	public String getCategoriaEscolhida() {
		return categoriaEscolhida;
	}

	public void setCategoriaEscolhida(String categoriaEscolhida) {
		this.categoriaEscolhida = categoriaEscolhida;
	}

	public Cliente getClienteEdita() {
		return clienteEdita;
	}

	public void setClienteEdita(Cliente clienteEdita) {
		this.clienteEdita = clienteEdita;
	}

	public Cliente getCliente() {
		return cliente;
	}
	

	/*           */
}
