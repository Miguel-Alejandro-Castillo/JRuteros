package daos;
import javax.persistence.*;
import model.Privacidad;
public class DaoPrivacidad extends JpaDaoGenerico<Privacidad>{
	public DaoPrivacidad(){
	    this.setModel("Privacidad");
	}
                            
}
