package daos;
import javax.persistence.*;
import model.Dificultad;
public class DaoDificultad extends JpaDaoGenerico<Dificultad>{
	public DaoDificultad(){
	    this.setModel("Dificultad");
	}
}
