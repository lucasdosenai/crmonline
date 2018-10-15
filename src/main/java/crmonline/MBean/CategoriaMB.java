package crmonline.MBean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import crmonline.DAO.CategoriaDAO;
import crmonline.Entidade.Categoria;

@ManagedBean
@SessionScoped
public class CategoriaMB {
	Categoria categoria;
	List<Categoria> categorias;
	CategoriaDAO cDao;
	
	public CategoriaMB() {
		categorias = new ArrayList<>();
		categoria  = new Categoria();
		cDao       = new CategoriaDAO();
		categorias = cDao.todasCategorias();
	}
	public void att() {
		categorias = cDao.todasCategorias();
	}
	public void novaCategoria() {
		if(categoria.getNome() != "") {
		if(cDao.novaCategoria(categoria)) {
			categorias = cDao.todasCategorias();
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(categoria.getNome() + " INSERIDO COM SUCESSO!"));
		}
		}else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("PROBLEMA AO INSERIR !"));
		}
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Categoria> getCategorias() {
		return categorias;
	}

	public void setCategorias(List<Categoria> categorias) {
		this.categorias = categorias;
	}

	public CategoriaDAO getcDao() {
		return cDao;
	}

	public void setcDao(CategoriaDAO cDao) {
		this.cDao = cDao;
	}
	
	
}
