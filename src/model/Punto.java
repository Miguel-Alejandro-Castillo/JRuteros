package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="PUNTOS")
public class Punto {
	public Punto() {
    }
    
    public Punto(Long id, String latitud, String longitud) {
		super();
		this.id = id;
		this.latitud = latitud;
		this.longitud = longitud;
	}
	
    @Id @GeneratedValue
    private Long id;
    private String latitud;
    private String longitud;
    
	

	public Punto(String latitud, String longitud) {
		super();
		this.latitud = latitud;
		this.longitud = longitud;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public Long getId() {
		return id;
	}

  

}