package beans;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.mail.MessagingException;
import daos.daosUser.IDaoUser;
import model.User;
import model.Usuario;
import utils.*;

@ManagedBean(name = "UsuarioBean")
@SessionScoped
public class UsuarioBean {
	
	@ManagedProperty(value = "#{OutcomeBean}")
	private OutcomeBean outcome;
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

	private FacesMessage facesMessage;

	public UsuarioBean() {
		super();
	}
	
	@PostConstruct
	public void init() {
		this.facesMessage = new FacesMessage();
	}
	
	public void initialize(){
		this.id = null;
		this.usuario = null;
		this.dni = null;
		this.apellido = null;
		this.nombre = null;
		this.domicilio = null;
		this.sexo = '\0';
		this.email = null;
		this.password = null;
		this.fechaNacimiento = null;
		this.facesMessage = new FacesMessage();
	}

	public String habilitarDeshabilitarUsuario(Long idUsuario) {
		try{
			User user = Factory.daoUser().buscarPorId(idUsuario);
			user.setHabilitado(!user.isHabilitado());
			Factory.daoUser().modificacion(user);
		}
		catch(Exception e){
			throw new RuntimeException(e.getMessage(), e);
		}
		return this.getOutcome().usuarios();
	}

	public String crearUsuario() throws RuntimeException {
		//FacesContext context = FacesContext.getCurrentInstance();
		this.facesMessage = new FacesMessage();
		IDaoUser daoUser = Factory.daoUser();
		User user = daoUser.buscar(this.getUsuario());
		Usuario nuevoUser = null;
		if (user == null) {
			try {
				// GENERAR LA PASSWORD DE FORMA ALEATORIA
				String passwordGenerada = PasswordGenerator.getPassword(8);
				nuevoUser = new Usuario(this.getUsuario(), this.getDni(), this.getApellido(), this.getNombre(),
						this.getDomicilio(), this.getSexo(), this.getEmail(), passwordGenerada, this.getFechaNacimiento());
				
				daoUser.alta(nuevoUser);
				this.setPassword(passwordGenerada);
				String subject = "Alta de usuario exitosa";
				String body = "Hola señor/a " + nuevoUser.getNombre() + " " + nuevoUser.getApellido() + "\n" +
						"Le informamos que su usuario " + nuevoUser.getUsuario() + " ha sido dado de alta exitosamente en JRuteros\n" +
						"Su contraseña es: " + nuevoUser.getContrasenia() + "\n" +
						"Saludos, que tenga un buen dia";
				MailerApp.sendMail(subject, body, nuevoUser.getEmail());
				this.facesMessage = new FacesMessage(FacesMessage.SEVERITY_INFO, "usuario.successSendMail", "Usuario registrado exitosamente.\n Contraseña enviada al correo electronico" );
			} catch(MessagingException  ex){
				daoUser.baja(nuevoUser);
				ex.printStackTrace();
				this.facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "usuario.errorSendMail", "Fallo el envio de mail" );				
			}
			catch (Exception e) {
				this.facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "usuario.errorAlta", "Sucedio un error inesperado en el alta, reintente nuevamente");
			}		

		} else {
			this.facesMessage = new FacesMessage(FacesMessage.SEVERITY_ERROR, "usuario.nombreRepetido" ,"Nombre de usuario utilizado, intente con otro nombre de usuario");
		}

		//context.addMessage("userAltaForm", this.facesMessage);
		return this.getOutcome().nuevoUsuario();

	}
	
	public String nuevoUsuario(){
		this.initialize();
		return this.getOutcome().nuevoUsuario();
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
