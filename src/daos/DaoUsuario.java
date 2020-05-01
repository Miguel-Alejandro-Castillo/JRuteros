package daos;
import javax.persistence.*;
import model.Usuario;

public class DaoUsuario extends JpaDaoGenerico<Usuario>{
	public DaoUsuario(){
	    this.setModel("Usuario");
	}

}
