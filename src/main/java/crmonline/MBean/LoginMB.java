package crmonline.MBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import crmonline.DAO.UserDAO;

@ManagedBean
public class LoginMB {
	private String usuario;
	private String password;
	UserDAO uDao;
	
	
	public LoginMB() {
		super();
		uDao = new UserDAO();
	}
	
	public void isLogged() {
		String msg = (String) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("msg");

		if(msg != null) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(msg));
		}
	}
	
	public String verificaLogin() {
		System.out.println("ENTRO NO METODO");
		if(uDao.buscaLogin(getUsuario(), getPassword()) != null) {
			System.out.println("usuario conectado com sucesso!");
			return "/index.xhtml?faces-redirect=true";
		}else {
			System.out.println("problema ao se conectar!");
			return  "/home.xhtml?faces-redirect=true";
		}
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
