package model;
import java.util.*;

import javax.persistence.*;
@Entity
@Table(name="PRIVACIDADES",uniqueConstraints=
@UniqueConstraint(columnNames = {"nombre"}))
public class Privacidad {

	@Id @GeneratedValue
	private Long id;
	private String Nombre;
	
	public Privacidad(){
		super();
	}

	public Privacidad(String nombre) {
		this();
		Nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return Nombre;
	}

	public void setNombre(String nombre) {
		Nombre = nombre;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	
	

}
