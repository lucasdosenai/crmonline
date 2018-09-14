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
	private String usuario = "";
	private String password = "";
	UserDAO uDao;
	Usuario comum;
	private Usuario userAtual;
	private String emailRecupera;
	
	public LoginMB() {
		super();
		uDao = new UserDAO();
	}
// -------------------------------------------------------------------------------------------------
	
	public String logaUsuario() {
			if(getUsuario().equals("") || getPassword().equals("")) {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Preencha os campos"));
				return "index?faces-redirect=true";
			}else {
				userAtual = uDao.buscaLogin(getUsuario(), getPassword());
				if(userAtual.getStatu() == 1 && userAtual.getTipo_user() == 1) {
					// ADIMINISTRADOR
					return "cadastrar?faces-redirect=true";
				}else if(userAtual.getStatu() == 1 && userAtual.getTipo_user() == 0) {
					// COMUM
					return "home?faces-redirect=true";
				}else {
					// DESATIVADO
					FacesContext.getCurrentInstance().addMessage(null, 
							new FacesMessage("Esse usu�rio esta Desativado!"));
					return "index?faces-redirect=true";
				}
			}
		
	}
	
// -------------------------------------------------------------------------------------------------
	
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
		return userAtual;
	}

	public void setUserAtual(Usuario userAtual) {
		userAtual = userAtual;
	}

	public String getEmailRecupera() {
		return emailRecupera;
	}

	public void setEmailRecupera(String emailRecupera) {
		this.emailRecupera = emailRecupera;
	}

	public Usuario getComum() {
		return comum;
	}

	public void setComum(Usuario comum) {
		this.comum = comum;
	}
	
}
