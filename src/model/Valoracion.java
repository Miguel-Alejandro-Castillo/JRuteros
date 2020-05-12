package model;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="VALORACIONES")
public class Valoracion implements Serializable{

	private static final long serialVersionUID = -8117902343840943934L;

	@Id
	@ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;
    
	@Id
	@ManyToOne(optional = false, cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
    @JoinColumn(name = "ruta_id")
    private Ruta ruta;
	
    private Integer puntaje;
    
    private Boolean hizoLaRuta;
    
    public Valoracion() {
    	super();
    }

	public Valoracion(Usuario usuario, Ruta ruta) {
		this();
		this.usuario = usuario;
		this.ruta = ruta;
	}

	public Valoracion(Usuario usuario, Ruta ruta, Integer puntaje, Boolean hizoLaRuta) {
		this(usuario, ruta);
		this.puntaje = puntaje;
		this.hizoLaRuta = hizoLaRuta;
	}

	public Integer getPuntaje() {
		return puntaje;
	}

	public void setPuntaje(Integer puntaje) {
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

	public Boolean getHizoLaRuta() {
		return hizoLaRuta;
	}

	public void setHizoLaRuta(Boolean hizoLaRuta) {
		this.hizoLaRuta = hizoLaRuta;
	}

}