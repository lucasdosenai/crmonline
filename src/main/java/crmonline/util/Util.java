package crmonline.util;

import java.util.Random;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Util {
	public String irCadastrar() {
		return "cadastro?faces-redirect=true";
	}
	public String irRecuperar() {
		return "recupera?faces-redirect=true";
	}
	public String irVerificaCodigo() {
		return "verifica-codigo?faces-redirect=true";
	}
	public String numeroAleatorio() {
		Random random = new Random(1000);
		return "CODIGO : " + random.nextInt(9999);
	}
}
