package crmonline.MBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Usuario;

@ViewScoped
@ManagedBean
public class CadMB {

	UserDAO uDao;
	Usuario usuario;

	public CadMB() {
		uDao = new UserDAO();
		usuario = new Usuario();
	}
	
	public String verifica() {
		if(uDao.verificaNifNoBanco(usuario.getNif()) == null) {
			if(uDao.cadastrar(usuario)) {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("Inserido com sucesso! "));
				FacesContext.getCurrentInstance().getExternalContext().
				getFlash().setKeepMessages(true);
				
				return "index?faces-redirect=true";
			}else {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("Não possível inserir! "));
				return "";
			}
		}else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("NIF JÁ EXISTENTE! "));
			return "";
		}
	}

	public UserDAO getuDao() {
		return uDao;
	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
