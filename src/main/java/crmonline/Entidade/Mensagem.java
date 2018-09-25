package crmonline.Entidade;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Mensagem {
	
	private String destinatario;
	private String assunto;
	private String mensagem;
	
	public String getDestinatario() {
		return destinatario;
	}
	public void setDestinatario(String destinatario) {
		this.destinatario = destinatario;
	}
	public String getAssunto() {
		return assunto;
	}
	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String object) {
		this.mensagem =  object;
	}
}
