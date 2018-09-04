package crmonline.MBean;

import java.util.Calendar;
import java.util.Random;

import javax.faces.bean.ManagedBean;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Usuario;

@ManagedBean
public class RecuMB {
	UserDAO uDao = new UserDAO();
	private Usuario UserAtual;
	private String emailRecupera;
	private Usuario userRecuperado;
	
	public RecuMB() {
		super();
		
	}
	
	public String verificaEmailExistente() {
		  userRecuperado = uDao.buscarEmail(emailRecupera);
		if(userRecuperado.getEmail().equals(emailRecupera)) {
			 
			 String codigoVerificaEmail = String.valueOf(Calendar.getInstance().getTimeInMillis());
			}
		return "";
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
