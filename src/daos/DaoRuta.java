package daos;
import javax.persistence.*;
import model.Ruta;
public class DaoRuta extends JpaDaoGenerico<Ruta>{
	public DaoRuta(){
	    this.setModel("Ruta");
	}
}
