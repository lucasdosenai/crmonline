package crmonline.MBean;

import java.util.Calendar;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Usuario;

@ManagedBean
public class RecuMB {
	UserDAO uDao;
	private Usuario UserAtual;
	private String emailRecupera = "";
	private Usuario userRecuperado;
	
	public RecuMB() {
		super();
		uDao = new UserDAO();
	}
	
	public String verificaEmailExistente() {
		FacesContext context = FacesContext.getCurrentInstance();
		userRecuperado = uDao.buscarEmail(emailRecupera);
		if(emailRecupera.equals("")) {
		if(userRecuperado.getEmail().equals(emailRecupera)) 
			 return "verifica-codigo?faces-redirect=true";	 
		     else return "recupera?faces-redirect=true";
		}
		context.addMessage(null, new FacesMessage("Preencha os campos!"));
		return "recupera?faces-redirect=true";
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

	public Usuario getUserRecuperado() {
		return userRecuperado;
	}

	public void setUserRecuperado(Usuario userRecuperado) {
		this.userRecuperado = userRecuperado;
	}
	
}
