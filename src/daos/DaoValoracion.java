package daos;
import javax.persistence.*;
import model.Valoracion;
public class DaoValoracion extends JpaDaoGenerico<Valoracion>{
	public DaoValoracion(){
	    this.setModel("Valoracion");
	}
}
