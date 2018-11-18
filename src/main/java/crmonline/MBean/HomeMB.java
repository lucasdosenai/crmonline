package crmonline.MBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import crmonline.Entidade.Agenda;

@ManagedBean
@ViewScoped
public class HomeMB {

	Agenda visitaPendenteClicadaParaVisualizar;

	public HomeMB() {
		visitaPendenteClicadaParaVisualizar = new Agenda();
	}

	// GET and SET
	public Agenda getVisitaPendenteClicadaParaVisualizar() {
		return visitaPendenteClicadaParaVisualizar;
	}

	public void setVisitaPendenteClicadaParaVisualizar(Agenda visitaPendenteClicadaParaVisualizar) {
		this.visitaPendenteClicadaParaVisualizar = visitaPendenteClicadaParaVisualizar;
	}
}
