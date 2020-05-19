package beans;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.User;
import model.Usuario;
import utils.Factory;
import utils.Role;

@ManagedBean(name = "UsuarioLogueadoBean")
@SessionScoped
public class UsuarioLogueadoBean {

	@ManagedProperty(value = "#{OutcomeBean}")
	private OutcomeBean outcome;
	// private User user;
	private Long id;
	private String usuario;
	private Long dni;
	private String apellido;
	private String nombre;
	private String domicilio;
	private char sexo;
	private String email;
	private String password;
	private Date fechaNacimiento;
	private String passwordActual;
	private String passwordNueva;
	private String passwordNuevaRepetida;
	private Usuario user;
	private List<Role> roles;
	private FacesMessage facesMessage;

	public UsuarioLogueadoBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.roles = new ArrayList<Role>();
		this.facesMessage = new FacesMessage();
	}

	public void initialize(Long id, String usuario, Long dni, String apellido, String nombre, String domicilio,
			char sexo, String email, String password, Date fechaNacimiento) {
		this.id = id;
		this.usuario = usuario;
		this.dni = dni;
		this.apellido = apellido;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.sexo = sexo;
		this.email = email;
		this.password = password;
		this.fechaNacimiento = fechaNacimiento;
	}

	public String login() {
		this.roles = new ArrayList<Role>();
		FacesContext context = FacesContext.getCurrentInstance();
		this.user = Factory.daoUsuario().buscar(this.getUsuario(), this.getPassword());
		if (this.user != null) {
			if (this.user.isHabilitado()) {
				this.initialize(user.getId(), user.getUsuario(), user.getDni(), user.getApellido(), user.getNombre(),
						user.getDomicilio(), user.getSexo(), user.getEmail(), user.getContrasenia(),
						user.getFechaNacimiento());
				context.getExternalContext().getSessionMap().put("id", user.getId());
				context.getExternalContext().getSessionMap().put("rol", "usuario");
				this.roles.add(Role.USER);
				return this.getOutcome().home();
			} else {
				FacesMessage message = new FacesMessage(
						"Usuario inhabilitado, por favor contactese con el administrador");
				context.addMessage("loginForm", message);
				return this.getOutcome().login();
			}
		} else {
			User administrador = Factory.daoUser().buscar(this.getUsuario(), this.getPassword());
			if (administrador != null) {
				context.getExternalContext().getSessionMap().put("id", administrador.getId());
				context.getExternalContext().getSessionMap().put("rol", "administrador");
				this.roles.add(Role.ADMIN);
				return this.getOutcome().home();
			} else {
				FacesMessage message = new FacesMessage("Usuario y/o contraseña invalida");
				context.addMessage("loginForm", message);
				return this.getOutcome().login();
			}
		}
	}

	public void inicializarPasswords() {
		this.setPasswordActual("");
		this.setPasswordNueva("");
		this.setPasswordNuevaRepetida("");
		this.setFacesMessage(new FacesMessage());
	}

	public void cambiarPassword() {
		if(!this.getPasswordActual().equals(this.getPassword())){
			this.facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "user.changePassword.error", "La contraseña actual es incorrecta" );
		} else {
			if(this.getPasswordActual().equals(this.getPasswordNueva())){
				this.facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "user.changePassword.error", "La contraseña actual y la nueva contraseña deben ser distintas" );
			} else {
				this.getUser().setContrasenia(this.getPasswordNueva());
				try {
					Factory.daoUser().modificacion(this.getUser());
					this.facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "user.changePassword.succes", "Su contraseña ha sido cambiada.\n Por favor, vuelva a loguearse" );
				} catch (Exception e) {
					e.printStackTrace();
					this.facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "user.changePassword.error", "Ocurrio un error en el servidor" );
				}
			}
		}
	}

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return this.getOutcome().login();
	}
	
	public String editarUsuario(){
		FacesContext context = FacesContext.getCurrentInstance();
		if ((!this.getUsuario().equals(this.user.getUsuario())) && (Factory.daoUsuario().buscar(this.getUsuario()) != null)) {	
			FacesMessage message = new FacesMessage("El usuario ya existe, por favor reintente con otro usuario");
			context.addMessage("formEditUsuario:usuario", message);
			return this.getOutcome().editarUsuario();
		} else {
			this.user.setUsuario(this.getUsuario());
			this.user.setDni(this.getDni());
			this.user.setApellido(this.getApellido());
			this.user.setNombre(this.getNombre());
			this.user.setDomicilio(this.getDomicilio());
			this.user.setFechaNacimiento(this.getFechaNacimiento());
			this.user.setSexo(this.getSexo());
			this.user.setEmail(this.getEmail());
				
			try {
				Factory.daoUser().modificacion(this.user);
				return this.getOutcome().home();
			} catch (Exception e) {
				e.printStackTrace();
				FacesMessage message = new FacesMessage("Ocurrio un error inesperado, por favor reintente mas tarde");
				context.addMessage("formEditUsuario", message);
				return this.getOutcome().editarUsuario();
			}			
		}
		
	}

	public boolean hasRole(Role role) {
		return this.roles.contains(role);
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getPasswordActual() {
		return passwordActual;
	}

	public void setPasswordActual(String passwordActual) {
		this.passwordActual = passwordActual;
	}

	public String getPasswordNueva() {
		return passwordNueva;
	}

	public String getPasswordNuevaRepetida() {
		return passwordNuevaRepetida;
	}

	public void setPasswordNuevaRepetida(String passwordNuevaRepetida) {
		this.passwordNuevaRepetida = passwordNuevaRepetida;
	}

	public void setPasswordNueva(String passwordNueva) {
		this.passwordNueva = passwordNueva;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}

	public FacesMessage getFacesMessage() {
		return facesMessage;
	}

	public void setFacesMessage(FacesMessage facesMessage) {
		this.facesMessage = facesMessage;
	}

	public OutcomeBean getOutcome() {
		return outcome;
	}

	public void setOutcome(OutcomeBean outcome) {
		this.outcome = outcome;
	}

}
