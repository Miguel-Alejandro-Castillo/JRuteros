package model;
import java.util.*;
import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;

@Entity
@Table(name="RUTAS")
public class Ruta{

	@Id @GeneratedValue
    private Long id;
	
    private String nombre;
    
    private String descripcion;
    
    @OneToOne(optional=false,cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private Actividad actividad;
    
    @OneToOne(optional=false,cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private Privacidad privacidad;
    
    @OneToOne(optional=false,cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private Formato formato;

    private BigDecimal distancia;
 
    @OneToOne(optional=false,cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private Dificultad dificultad;

    private Time tiempo;

    private Date fechaRealizacion;

    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name= "RUTAS_FOTOS",
               joinColumns={@JoinColumn(name="ruta_id" ,referencedColumnName="id")},
               inverseJoinColumns={@JoinColumn
            		 (name="foto_id" ,referencedColumnName="id")
               })
    private List<Foto> fotos;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.MERGE})
    //@OneToMany
    @JoinTable(name= "RUTAS_PUNTOS",
    joinColumns={@JoinColumn(name="ruta_id" ,referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn
 		 (name="punto_id" ,referencedColumnName="id")
    })
    private List<Punto> recorrido;
   
    @OneToOne(optional=false,cascade={CascadeType.PERSIST,CascadeType.MERGE})
    private Usuario propietario;
    
    /*
    @ManyToMany(mappedBy="misRutas",cascade={CascadeType.MERGE})
    private Set<Usuario> visitantes;
    */
    
    /*
    @OneToMany(cascade=CascadeType.ALL)
    @JoinTable(name= "RUTAS_VALORACIONES",
    joinColumns={@JoinColumn(name="ruta_id" ,referencedColumnName="id")},
    inverseJoinColumns={@JoinColumn
 		 (name="valoracion_id" ,referencedColumnName="id")
    })
    private List<Valoracion> valoraciones;
    */
    
    public Ruta() {
           super();
           fotos=new ArrayList<Foto>();
           // valoraciones=new ArrayList<Valoracion>();
           recorrido=new ArrayList<Punto>();          
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

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Actividad getActividad() {
		return actividad;
	}

	public void setActividad(Actividad actividad) {
		this.actividad = actividad;
	}

	public Privacidad getPrivacidad() {
		return privacidad;
	}

	public void setPrivacidad(Privacidad privacidad) {
		this.privacidad = privacidad;
	}

	public Formato getFormato() {
		return formato;
	}

	public void setFormato(Formato formato) {
		this.formato = formato;
	}

	public BigDecimal getDistancia() {
		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	public Dificultad getDificultad() {
		return dificultad;
	}

	public void setDificultad(Dificultad dificultad) {
		this.dificultad = dificultad;
	}

	public Time getTiempo() {
		return tiempo;
	}

	public void setTiempo(Time tiempo) {
		this.tiempo = tiempo;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public List<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(List<Foto> fotos) {
		this.fotos = fotos;
	}

	public List<Punto> getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(List<Punto> recorrido) {
		this.recorrido = recorrido;
	}

	public void agregarFoto(Foto foto){
		this.getFotos().add(foto);
	}
	
	public void agregarPunto(Punto punto){
		this.getRecorrido().add(punto);
	}
	
	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}

} 