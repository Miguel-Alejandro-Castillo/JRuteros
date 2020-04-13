package model;

import javax.persistence.*;
@Entity
@Table(name="FORMATOS",uniqueConstraints=
@UniqueConstraint(columnNames = {"nombre"}))
public class Formato {
	@Id @GeneratedValue
	private Long id;
	@Column(unique=true)
	private  String nombre;
    public Formato(){
    	
    }
	public Formato(String nombre) {
		super();
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
