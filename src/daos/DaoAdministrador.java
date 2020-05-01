package daos;
import javax.persistence.*;
import model.Administrador;
public class DaoAdministrador extends JpaDaoGenerico<Administrador>{
	public DaoAdministrador(){
	    this.setModel("Administrador");
	}
}
