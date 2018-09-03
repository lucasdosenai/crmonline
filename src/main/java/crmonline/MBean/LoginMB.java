package crmonline.MBean;

import java.util.Calendar;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.xml.crypto.Data;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Usuario;

@ManagedBean
public class LoginMB {
	private String usuario;
	private String password;
	UserDAO uDao;
	private Usuario UserAtual;
	private String emailRecupera;
	
	private Integer A,B;
	
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
	public void intermediario() {
		
	}
	
	public void verificaEmailExistente() {
		if(uDao.buscarEmail(emailRecupera).getEmail().equals(emailRecupera)) {
			Random r = new Random(Calendar.getInstance().getTimeInMillis());
		}
	}
	public String verificaLogin() {
		verificaEmailExistente();
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

	public UserDAO getuDao() {
		return uDao;
	}

	public void setuDao(UserDAO uDao) {
		this.uDao = uDao;
	}

	public Usuario getUserAtual() {
		return UserAtual;
	}

	public void setUserAtual(Usuario userAtual) {
		UserAtual = userAtual;
	}

	public String getEmailRecupera() {
		return emailRecupera;
	}

	public void setEmailRecupera(String emailRecupera) {
		this.emailRecupera = emailRecupera;
	}

	public Integer getA() {
		return A;
	}

	public void setA(Integer a) {
		A = a;
	}

	public Integer getB() {
		return B;
	}

	public void setB(Integer b) {
		B = b;
	}
	
}
