package crmonline.util;

import javax.faces.bean.ManagedBean;

@ManagedBean
public class Util {
	public String irCadastrar() {
		return "cadastro?faces-redirect=true";
	}
}
