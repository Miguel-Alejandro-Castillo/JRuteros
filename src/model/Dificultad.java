package model;

import javax.persistence.*;

@Entity
@Table(name="DIFICULTADES",uniqueConstraints=
@UniqueConstraint(columnNames = {"nombre"}))
public class Dificultad {
	
	@Id @GeneratedValue
	private Long id;
	
	@Column(nullable = false, unique = true)
	private String nombre;
	
	public Dificultad() {
		super();
	}
	
	public Dificultad( String nombre) {
		this();
		this.nombre = nombre;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
