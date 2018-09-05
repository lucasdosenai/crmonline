package crmonline.MBean;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import crmonline.DAO.UserDAO;
import crmonline.Entidade.Usuario;

@ManagedBean
public class CadMB {

	 UserDAO uDao;
	 private String nome;
	 private String nif;
	 private String sexo;
	 private String email;
	 private String senha;
	 private Integer statu = 0;
	 private Integer tipo_usuario = 0;
	
	 public CadMB() {
		 uDao = new UserDAO();
	 }
	 
	 public void cadastraUsuario() {
		 FacesContext contex =  FacesContext.getCurrentInstance();
		if(getNome().equals("") ||
				getNif().equals("") ||
				getSexo().equals("") ||
				getEmail().equals("") ||
				getSenha().equals("")) {
			System.out.println("Campos vazios");
			contex.addMessage(null,new FacesMessage( "Preencha os Campos Vazios!"));
		}else {
			
			System.out.println("entrou no else, e preencheou o usuario");
			Usuario usuario = new Usuario();
			Usuario usuarioVerifica = new Usuario();
			usuario.setNome(getNome());
			usuario.setNif(getNif().replaceAll(" ", ""));
			usuario.setSexo(getSexo());
			usuario.setEmail(getEmail());
			usuario.setPassword(getSenha());
			usuario.setStatu(getStatu());
			usuario.setTipo_user(getTipo_usuario());
			
			usuarioVerifica = uDao.verificaNifNoBanco(usuario.getNif());
			if(usuarioVerifica.getNif() == usuario.getNif())
				contex.addMessage(null, new FacesMessage("NIF ja cadastrado"));
			else { 
				if(uDao.cadastrar(usuario)) {
					System.out.println(usuario.getNome() + " : cadastrado com sucesso!");
				}else {
					System.out.println(usuario.getNome() + " : não cadastrado");
				}
			}
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
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	public Integer getTipo_usuario() {
		return tipo_usuario;
	}
	public void setTipo_usuario(Integer tipo_usuario) {
		this.tipo_usuario = tipo_usuario;
	}
	
}
