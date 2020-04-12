package model;

import javax.persistence.*;
@Entity
@Table(name="VALORACIONES")
public class Valoracion {
	
	@Id @GeneratedValue
    private Long id;
	
    @OneToOne(optional=false,cascade={CascadeType.MERGE})
    private Usuario usuario;
    
    @OneToOne(optional=false,cascade={CascadeType.MERGE})
    private Ruta ruta;
	
    private Integer puntaje;
    
    private Boolean hiceLaRuta;
    
    public Valoracion() {
    }

	public Valoracion(int puntaje, Usuario usuario) {
		super();
		this.puntaje = puntaje;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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


}