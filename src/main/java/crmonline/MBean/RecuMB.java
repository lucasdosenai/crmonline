package crmonline.MBean;

import java.util.Calendar;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Usuario;
import crmonline.util.Util;

@ManagedBean
public class RecuMB {
	UserDAO uDao;
	private Usuario UserAtual;
	private String emailRecupera = "";
	private Usuario userRecuperado;
	
	Util u ;
	
	public RecuMB() {
		super();
		uDao = new UserDAO();
	}
	
	public String verificaEmailExistente() {
		Random random = new Random();
		Integer aleatorio = (int) (Math.random()*9999);
		
		// Verifica se o campo email esta vazio!
		if(emailRecupera.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Preencha os campos!"));
		}else {
			userRecuperado = uDao.buscarEmail(emailRecupera);
		}
		
		return null;
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
