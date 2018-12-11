package crmonline.Entidade;

public class RelatorioFiltro {
	public Integer ID_CLIENTE;
	public String DATAV;
	
	public Integer getID_CLIENTE() {
		return ID_CLIENTE;
	}
	public void setID_CLIENTE(Integer iD_CLIENTE) {
		ID_CLIENTE = iD_CLIENTE;
	}
	public String getDATAV() {
		return DATAV;
	}
	public void setDATAV(String dATAV) {
		if(dATAV.isEmpty()) {
			dATAV = null;
		} else {
			DATAV = dATAV;
		}
	}
}
