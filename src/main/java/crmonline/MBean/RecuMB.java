package crmonline.MBean;

import java.awt.Label;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.apache.commons.mail.EmailException;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Mensagem;
import crmonline.Entidade.RecuperaUser;
import crmonline.Entidade.Usuario;
import crmonline.util.Util;
import crmonline.util.UtilEnviar;

@ManagedBean
public class RecuMB {
	UserDAO uDao;
	Util u ;
	Label c;
	
	boolean render;
	
	private Usuario UserAtual;
	private String emailRecupera = "";
	private Usuario userRecuperado;
	private String vCod = "" ;
	
	String ID_USUARIO_FINAL = "";
	String verificaPassword = "";
	String Password = "";
	
	public RecuMB() {
		super();
		uDao = new UserDAO();
		c = new Label();
		render = false;
	}
	
	public String verificaEmailExistente() {
		// Verifica se o campo email esta vazio!
		if(emailRecupera.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Preencha os campos!"));
		}else {
			userRecuperado = uDao.buscarEmail(emailRecupera);
			if(userRecuperado != null) {
				Mensagem msg = new Mensagem();	
				try {
					Integer aleatorio = (int) (Math.random()*9999);
					RecuperaUser rUser = new RecuperaUser();
					rUser.setId_user(userRecuperado.getCodigo());
					rUser.setCodigo(aleatorio.toString());
					if(uDao.recuperaUser(rUser)) {
						c.setText("<html>CÓDIGO : <b>" + aleatorio + "</b></html>");
						msg.setAssunto("Recuper Senha - Sistema CRM");
						msg.setDestinatario(userRecuperado.getEmail());
						msg.setMensagem(c.getText());
					UtilEnviar.enviaEmail(msg);
					System.out.println("Email enviado com sucesso!");
					return "verifica-codigo?faces-redirect=true";
					}else {
						System.out.println("Problema ao cadastrar codigo");
						FacesContext.getCurrentInstance().addMessage(null, 
								new FacesMessage("Problema com protocologo de segurança"));
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
	public String verificaCodigo() {
		if(vCod != "") {
			RecuperaUser protocolo = uDao.listaProtocolo(vCod);
			if(protocolo != null) {
				ID_USUARIO_FINAL = protocolo.getId_user().toString();
				render = true;
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("PROTOCOLOGO! LIBERADO") );
			}else {
				FacesContext.getCurrentInstance().addMessage(null, 
						new FacesMessage("PROTOCOLOGO! NÃO LIBERADO, APARENTEMENTE VERIFIQUE SEU CÓDIGO") );
			}
		}else {
				FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("COLOQUE UM CÓDIGO") );
		}
		return null;
	}
	public String novaSenha() {
		render = true;
		if(Password.equals("") || verificaPassword.equals("")) {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("Preencha o campo em branco!"));
			return null;
		}else {
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage("NOVA SENHA") );
			return "index?faces-redirect=true";
		}
		
		
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

	public boolean isrender() {
		return render;
	}

	public void setCodigo(boolean render) {
		this.render = render;
	}

	public Util getU() {
		return u;
	}

	public void setU(Util u) {
		this.u = u;
	}

	public Label getC() {
		return c;
	}

	public void setC(Label c) {
		this.c = c;
	}

	public String getvCod() {
		return vCod;
	}

	public void setvCod(String vCod) {
		this.vCod = vCod;
	}

	public boolean isRender() {
		return render;
	}

	public void setRender(boolean render) {
		this.render = render;
	}

	public String getID_USUARIO_FINAL() {
		return ID_USUARIO_FINAL;
	}

	public void setID_USUARIO_FINAL(String iD_USUARIO_FINAL) {
		ID_USUARIO_FINAL = iD_USUARIO_FINAL;
	}

	public String getVerificaPassword() {
		return verificaPassword;
	}

	public void setVerificaPassword(String verificaPassword) {
		this.verificaPassword = verificaPassword;
	}

	public String getPassword() {
		return Password;
	}

	public void setPassword(String password) {
		Password = password;
	}
	
	
	
}
