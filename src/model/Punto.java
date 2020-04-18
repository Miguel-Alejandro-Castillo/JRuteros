package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Entity
@Table(name="PUNTOS")
public class Punto {
	
    @Id @GeneratedValue
    private Long id;
    private String latitud;
    private String longitud;
    
	public Punto() {
		super();
    }
	
	public Punto(String latitud, String longitud) {
		this();
		this.latitud = latitud;
		this.longitud = longitud;
	}
    
    public Punto(Long id, String latitud, String longitud) {
		this(latitud, longitud);
		this.id = id;
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