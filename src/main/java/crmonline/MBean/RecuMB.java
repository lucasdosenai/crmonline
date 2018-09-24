package crmonline.MBean;

import java.util.Calendar;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Mensagem;
import crmonline.Entidade.Usuario;
import crmonline.util.Util;
import crmonline.util.UtilEnviar;

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
		Integer aleatorio = (int) (Math.random()*9999);
		
		// Verifica se o campo email esta vazio!
		if(emailRecupera.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Preencha os campos!"));
		}else {
			userRecuperado = uDao.buscarEmail(emailRecupera);
			if(userRecuperado != null) {
				Mensagem msg = new Mensagem();
				String ass = "Recuper Senha - Sistema CRM";
				String email = userRecuperado.getEmail();
				String msgg = "CODIGO 01: " + aleatorio;
				
				try {
					for(int x = 0;x < 1000;x++) {
						Integer aleatorioO = (int) (Math.random()*9999);
						msg.setAssunto("VOCÊ É UM VAGABUNDO PELA : " + x + " VEZ");
						msg.setDestinatario(emailRecupera);
						msg.setMensagem("CÓDIGO : " + aleatorioO);
					UtilEnviar.enviaEmail(msg);
					System.out.println("Email enviado com sucesso!");
					}
				} catch (EmailException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}else {
				System.out.println("Problema ao Enviar Email");
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("Problema ao enviar Email!"));
			}
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
