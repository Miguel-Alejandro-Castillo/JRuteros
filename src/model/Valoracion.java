package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="VALORACIONES")
public class Valoracion implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
    @OneToOne(optional=false,cascade={CascadeType.MERGE})
    private Usuario usuario;
    
	@Id
    @OneToOne(optional=false,cascade={CascadeType.MERGE})
    private Ruta ruta;
	
    private Integer puntaje;
    
    private Boolean hiceLaRuta;
    
    public Valoracion() {
    	super();
    }

	public Valoracion(int puntaje, Usuario usuario) {
		super();
		this.puntaje = puntaje;
		this.usuario = usuario;
	}

	public int getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(int puntaje) {
		this.puntaje = puntaje;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public Boolean getHiceLaRuta() {
		return hiceLaRuta;
	}

	public void setHiceLaRuta(Boolean hiceLaRuta) {
		this.hiceLaRuta = hiceLaRuta;
	}

	public void setPuntaje(Integer puntaje) {
		this.puntaje = puntaje;
	}


}