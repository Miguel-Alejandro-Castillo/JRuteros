package model;
import java.util.Date;
import javax.persistence.*;
@Entity
@Table(name="ADMINISTRADORES")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Administrador extends User {
	
    public Administrador(String usuario, Long dni, String apellido, String nombre, String domicilio,
			char sexo, String email, String password, Date fechaNacimiento) {
		super(usuario, dni, apellido, nombre, domicilio, sexo, email, password, fechaNacimiento);
		// TODO Auto-generated constructor stub
	}

	public Administrador() {
    	
    }

}