package model;

import javax.persistence.*;

@Entity
@Table(name="FOTOS")
public class Foto {
	 @Id @GeneratedValue
	private Long id;
	private String nombre;
    private String path;
    
    public Foto() {
    	super();
    }
      
    public Foto(String nombre, String path) {
		super();
		this.nombre = nombre;
		this.path = path;
	}
    
    public void setId(Long id) {
		this.id = id;
	}

	public Long getId(){
    	return this.id;
    }
    public String getPath() {   
        return this.path;
    }
    
    public void setPath(String path) {
        this.path=path;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
       this.nombre=nombre;
    }

}