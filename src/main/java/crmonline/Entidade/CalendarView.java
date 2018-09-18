package crmonline.Entidade;


import java.util.Date;
import javax.faces.bean.ManagedBean;
 
import org.primefaces.PrimeFaces;
 
@ManagedBean
public class CalendarView {
         
    private Date date1;
   
     
    public void click() {
        PrimeFaces.current().ajax().update("form:display");
        PrimeFaces.current().executeScript("PF('dlg').show()");
    }
 
    public Date getDate1() {
        return date1;
    }
 
    public void setDate1(Date date1) {
        this.date1 = date1;
    }
}
 
   