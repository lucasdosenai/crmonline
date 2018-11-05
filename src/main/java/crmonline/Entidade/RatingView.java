package crmonline.Entidade;



import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
 
import org.primefaces.event.RateEvent;
 
@ManagedBean
public class RatingView {
     
    
    private Integer rating3;    
   
     
    public void onrate(RateEvent rateEvent) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Classificação", "Você Classificou:" + ((Integer) rateEvent.getRating()).intValue() + " Estrelas");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public void oncancel() {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Classificação", "Classificação Cancelada");
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
 
    
 
    public Integer getRating3() {
        return rating3;
    }
 
    public void setRating3(Integer rating3) {
        this.rating3 = rating3;
    }
 
}