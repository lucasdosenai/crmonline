package crmonline.MBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Usuario;

@ManagedBean
public class LoginMB {
	private String usuario;
	private String password;
	UserDAO uDao;
	private Usuario UserAtual;
	
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
		FacesContext context = FacesContext.getCurrentInstance();
		UserAtual = uDao.buscaLogin(getUsuario(), getPassword());
		if(!getUsuario().equals("") || getPassword().equals("")) {
			if(UserAtual.getNif() != getUsuario()) {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("Usuario e/ou senha incorretos"));
		        
				System.out.println("problema ao se conectar!");
				return  "/index.xhtml?faces-redirect=true";
			}else if(uDao.buscaLogin(getUsuario(), getPassword()).getNif().equals(getUsuario())){
				
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("Bem-Vindo " + UserAtual.getNome() ));
				
				System.out.println("usuario conectado com sucesso!");
				return "/home.xhtml?faces-redirect=true";
			}
		}else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Preencha os campos!" ));
			
		}
		return "true";
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
