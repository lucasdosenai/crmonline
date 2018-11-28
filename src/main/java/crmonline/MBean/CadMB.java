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
	String verificaSenha;
	
	public CadMB() {
		uDao = new UserDAO();
		usuario = new Usuario();
	}
	
	public String verifica() {
		if(usuario.getPassword().equals(verificaSenha)) {
			
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
	}else {
		FacesContext.getCurrentInstance().addMessage(null, 
				new FacesMessage("SENHAS NÃO CORRESPONDEM! "));
		return "";
	}
	}
	
	
	public String getVerificaSenha() {
		return verificaSenha;
	}

	public void setVerificaSenha(String verificaSenha) {
		this.verificaSenha = verificaSenha;
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
