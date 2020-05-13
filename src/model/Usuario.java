package model;

import java.util.Date;
import javax.persistence.*;
@Entity

@Table(name="USUARIOS")
@PrimaryKeyJoinColumn(referencedColumnName="id")
public class Usuario extends User {
	
	/*
	@ManyToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
	@JoinTable(name= "USUARIOS_RUTAS",
	    joinColumns={@JoinColumn(name="usuario_id" ,referencedColumnName="id")},
	    inverseJoinColumns={@JoinColumn
	 		 (name="ruta_id" ,referencedColumnName="id")
	    })
	private List<Ruta> misRutas;
	*/
	
	public Usuario() {
		super();
    }
   
	public Usuario(String usuario, Long dni, String apellido, String nombre, String domicilio, char sexo,
			String email, String password, Date fechaNacimiento) {
		super(usuario, dni, apellido, nombre, domicilio, sexo, email, password, fechaNacimiento);
	}	

}