package crmonline.Entidade;


public class Usuario {
	
	private Integer codigo;
	private String nif;
	private String nome;
	private String email;
	private String password;
	private Integer statu;
	private Integer tipo_user;
	
	
	
	public Integer getCodigo() {
		return codigo;
	}
	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}
	public String getNif() {
		return nif;
	}
	public void setNif(String nif) {
		this.nif = nif;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getStatu() {
		return statu;
	}
	public void setStatu(Integer statu) {
		this.statu = statu;
	}
	public Integer getTipo_user() {
		return tipo_user;
	}
	public void setTipo_user(Integer tipo_user) {
		this.tipo_user = tipo_user;
	}
	
	
	
}
