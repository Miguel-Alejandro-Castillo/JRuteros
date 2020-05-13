package model;

import javax.persistence.*;

@Entity
@Table(name="PRIVACIDADES",uniqueConstraints=
@UniqueConstraint(columnNames = {"nombre"}))
public class Privacidad {

	@Id @GeneratedValue
	private Long id;

	@Column(nullable = false, unique = true)
	private String nombre;
	
	public Privacidad(){
		super();
	}

	public Privacidad(String nombre) {
		this();
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setId(Long id) {
		this.id = id;
	}	

}
