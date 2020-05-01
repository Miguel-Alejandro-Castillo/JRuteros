package model;

import java.util.Date;
import javax.persistence.*;

@Entity
@Table(name="USERS")
@Inheritance(strategy=InheritanceType.JOINED)
public class User {
	
	@Id @GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(nullable = false, unique = true)
    private String usuario;
    
	@Column(nullable = false)
    private Long dni;
    
	@Column(nullable = false)
    private String apellido;
    
	@Column(nullable = false)
    private String nombre;
    
	@Column(nullable = false)
    private String domicilio;
    
	@Column(nullable = false)
    private char sexo;
    
	@Column(nullable = false)
    private String email;
    
	@Column(nullable = false)
    private String contrasenia;
    
	@Column(nullable = false)
    private Date fechaNacimiento;

    private boolean habilitado;

    public User(){
    	super();
    	this.habilitado = true;
    }
    
    public User( String usuario, Long dni, String apellido, String nombre, String domicilio, char sexo,
			String email, String contrasenia, Date fechaNacimiento) {
		this();
		this.usuario = usuario;
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.sexo = sexo;
		this.email = email;
		this.contrasenia = contrasenia;
		this.fechaNacimiento = fechaNacimiento;
	}
    
    public User(Long id, String usuario, Long dni, String apellido, String nombre, String domicilio, char sexo,
			String email, String contrasenia, Date fechaNacimiento) {
		this(usuario, dni, apellido, nombre, domicilio, sexo, email, contrasenia, fechaNacimiento);
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Long getDni() {
		return dni;
	}

	public void setDni(Long dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDomicilio() {
		return domicilio;
	}

	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	
	public boolean isHabilitado() {
		return habilitado;
	}
	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}
    
}