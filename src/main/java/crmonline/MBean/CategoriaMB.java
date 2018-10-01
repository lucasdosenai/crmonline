package crmonline.MBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import crmonline.DAO.CategoriaDAO;
import crmonline.Entidade.Categoria;

@ManagedBean
public class CategoriaMB {
	Categoria ctg;
	CategoriaDAO cDao;
	
	public CategoriaMB() {
		ctg = new Categoria();
		cDao = new CategoriaDAO();
	}
	
	public void novaCategoria() {
		if(cDao.novaCategoria(ctg)) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(ctg.getNome() + " INSERIDO COM SUCESSO!"));
		}else {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("Problema verifique o campo"));
		}
	}
	
}
