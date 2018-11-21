package crmonline.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.faces.bean.ManagedBean;

import crmonline.DAO.RelatorioDAO;

@ManagedBean
public class Util {
	RelatorioDAO rDAO;
	public Util() {
		rDAO = new RelatorioDAO();
	}
	
	public String irCadastrarNovaVisita(){
		return "agendar?faces-redirect=true";
	}
	public String irCadastrar() {
		return "cadastro?faces-redirect=true";
	}
	public String irRecuperar() {
		return "recupera?faces-redirect=true";
	}
	public String irVerificaCodigo() {
		return "verifica-codigo?faces-redirect=true";
	}
	public String irCadastrarContato() {
		return "contatos?faces-redirect=true";
	}
	public String numeroAleatorio() {
		Random random = new Random(1000);
		return "CODIGO : " + random.nextInt(9999);
	}
	
	public String converteDate(Date date) {
		SimpleDateFormat s = new SimpleDateFormat("dd/MM/yyyy");
		return s.format(date);
	}
	
	public String mostraCliente(Integer codigo) {
		String nomeDoCliente = "";
		String cod = codigo.toString();
		return nomeDoCliente = rDAO.clienteConverte(cod);
	}
}
