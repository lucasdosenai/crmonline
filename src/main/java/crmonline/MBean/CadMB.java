package crmonline.MBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import crmonline.DAO.UserDAO;

@ManagedBean
public class CadMB {

	 UserDAO uDao;
	 private String nome;
	 private String nif;
	 private String sexo;
	 private String email;
	 private String senha;
	 private String statu;
	 private String tipo_usuario;
	
	 public CadMB() {
		 uDao = new UserDAO();
	 }
	 
	 public void cadastraUsuario() {
		 FacesContext contex =  FacesContext.getCurrentInstance();
		if(getNome().equals("") ||
				getNif().equals("") ||
				getSexo().equals("") ||
				getEmail().equals("") ||
				getSenha().equals("") ||
				getStatu().equals("") ||
				getTipo_usuario().equals("")) {
			
			contex.addMessage(null,new FacesMessage( "Preencha os Campos Vazios!"));
		}
	 }
	 
	 
	 public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	public String getStatu() {
		return statu;
	}
	public void setStatu(String statu) {
		this.statu = statu;
	}
	public String getTipo_usuario() {
		return tipo_usuario;
	}
	public void setTipo_usuario(String tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
}
