package crmonline.MBean;

import javax.faces.bean.ManagedBean;

import org.apache.commons.mail.EmailException;

import crmonline.Entidade.Mensagem;
import crmonline.util.UtilEnviar;

@ManagedBean
public class EmailMBean {
	
	private Mensagem msgClass;
	
	public EmailMBean() {
		msgClass = new Mensagem();
	}
	
	public void enviar(){
		try {
			UtilEnviar.enviaEmail(msgClass);
		} catch (EmailException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Mensagem getMsgClass() {
		return msgClass;
	}

	public void setMsgClass(Mensagem msgClass) {
		this.msgClass = msgClass;
	}
	
	
	
}
