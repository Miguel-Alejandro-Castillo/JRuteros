package daos;
import javax.persistence.*;
import model.Formato;
public class DaoFormato extends JpaDaoGenerico<Formato> { 
	public DaoFormato(){
	    this.setModel("Formato");
	}
	
	

}
