package crmonline.MBean;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import crmonline.DAO.ClienteDAO;
import crmonline.Entidade.Categoria;
import crmonline.Entidade.Cliente;

@ManagedBean
public class ClienteMB {
	
	private Cliente cliente;
	private ArrayList<Categoria> categorias;
	ClienteDAO cDao;
	public ClienteMB() {
		cliente    = new Cliente();
		cDao	   = new ClienteDAO();
		categorias = new ArrayList<>();
		categorias = cDao.listaCategorias();
	}
	
	
	
}
