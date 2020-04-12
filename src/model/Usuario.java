package model;
import java.util.List;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.*;
@Entity

@Table(name="USUARIOS")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Usuario extends User {
	
	
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name= "USUARIOS_RUTAS",
	    joinColumns={@JoinColumn(name="usuario_id" ,referencedColumnName="id")},
	    inverseJoinColumns={@JoinColumn
	 		 (name="ruta_id" ,referencedColumnName="id")
	    })
	private List<Ruta> misRutas;
   
	//private List<Ruta> rutasVisitadas;
	public Usuario(String usuario, Long dni, String apellido, String nombre, String domicilio, char sexo,
			String email, String password, Date fechaNacimiento) {
		super(usuario, dni, apellido, nombre, domicilio, sexo, email, password, fechaNacimiento);
	}

	public Usuario() {
		super();
		misRutas=new ArrayList<Ruta>();
    }
	
	public List<Ruta> getMisRutas() {
		return misRutas;
	}
	public void setMisRutas(List<Ruta> misRutas) {
		this.misRutas = misRutas;
	}
	

}