package model;

import java.util.*;
import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "RUTAS")
public class Ruta {

	@Id
	@GeneratedValue
	private Long id;

	@Column(nullable = false)
	private String nombre;

	@Column(nullable = false)
	private String descripcion;

	@ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "actividad_id", referencedColumnName = "id")
	private Actividad actividad;

	@ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "privacidad_id")
	private Privacidad privacidad;

	@ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "formato_id")
	private Formato formato;

	@Column(nullable = false)
	private BigDecimal distancia;

	@ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "dificultad_id")
	private Dificultad dificultad;

	@Column(nullable = false)
	private Date tiempoEstimado;

	@Column(nullable = false)
	private Date fechaRealizacion;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "ruta_id")
	private Set<Foto> fotos = new HashSet<Foto>();

	/*
	 * @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,
	 * orphanRemoval = true)
	 * 
	 * @JoinTable(name = "RUTAS_FOTOS", joinColumns = {
	 * 
	 * @JoinColumn(name = "ruta_id", referencedColumnName = "id") },
	 * inverseJoinColumns = {
	 * 
	 * @JoinColumn(name = "foto_id", referencedColumnName = "id") }) private
	 * Set<Foto> fotos = new HashSet<Foto>();
	 */

	/*
	 * @OneToMany(cascade={CascadeType.ALL}, fetch = FetchType.EAGER,
	 * orphanRemoval = true)
	 * 
	 * @JoinTable(name= "RUTAS_PUNTOS", joinColumns={@JoinColumn(name="ruta_id"
	 * ,referencedColumnName="id")}, inverseJoinColumns={@JoinColumn
	 * (name="punto_id" ,referencedColumnName="id") })
	 * 
	 * @OrderBy("id ASC") private Set<Punto> recorrido = new
	 * LinkedHashSet<Punto>();
	 */

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
	@JoinColumn(name = "ruta_id")
	private List<Punto> recorrido = new ArrayList<Punto>();

	@ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH })
	@JoinColumn(name = "propietario_id")
	private Usuario propietario;

	/*
	 * @ManyToMany(mappedBy="misRutas",cascade={CascadeType.MERGE}) private
	 * Set<Usuario> visitantes;
	 */

	/*
	 * @OneToMany(cascade=CascadeType.ALL)
	 * 
	 * @JoinTable(name= "RUTAS_VALORACIONES",
	 * joinColumns={@JoinColumn(name="ruta_id" ,referencedColumnName="id")},
	 * inverseJoinColumns={@JoinColumn (name="valoracion_id"
	 * ,referencedColumnName="id") }) private List<Valoracion> valoraciones;
	 */

	public Ruta() {
		super();
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

	public Date getTiempoEstimado() {
		return this.tiempoEstimado;
	}

	public void setTiempoEstimado(Date tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public Usuario getPropietario() {
		return propietario;
	}

	public void setPropietario(Usuario propietario) {
		this.propietario = propietario;
	}
	
	public Set<Foto> getFotos() { return fotos; }
	
	public void setFotos(Set<Foto> fotos) { this.fotos = fotos; }

	public List<Punto> getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(List<Punto> recorrido) {
		this.recorrido = recorrido;
	}

	/*
	 * public Set<Punto> getRecorrido() { return recorrido; }
	 * 
	 * public void setRecorrido(Set<Punto> recorrido) { this.recorrido =
	 * recorrido; }
	 */

}