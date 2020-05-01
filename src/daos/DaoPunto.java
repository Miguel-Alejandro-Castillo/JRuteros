package daos;
import javax.persistence.*;
import model.Punto;
public class DaoPunto extends JpaDaoGenerico<Punto> {
	public DaoPunto(){
	    this.setModel("Punto");
	}
}
