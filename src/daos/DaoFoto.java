package daos;
import javax.persistence.*;
import model.Foto;
public class DaoFoto extends JpaDaoGenerico<Foto>{
	public DaoFoto(){
	    this.setModel("Foto");
	}
}
