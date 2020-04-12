package misBeans;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import daos.daosUser.IDaoUser;
import model.User;
import model.Usuario;
import utilitarios.*;

public class UsuarioBean {
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
	    
	    public UsuarioBean() {
			super();
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
		
		public String habilitarDeshabilitarUsuario(){
			    Usuario user=Factory.daoUsuario().buscarPorId(this.getId());
			    user.setHabilitado(!user.isHabilitado());
			    Factory.daoUsuario().modificacion(user);
			    return "usuarios";	    
		}
		
		public String alta(){
			FacesContext context = FacesContext.getCurrentInstance();
			String mensaje="";
			IDaoUser daoUser=Factory.daoUser();
			User user= daoUser.buscar(this.getUsuario());
			if(user == null){
				//GENERAR LA PASSWORD DE FORMA ALEATORIA
				String passwordGenerada=PasswordGenerator.getPassword();
				Usuario nuevoUser=new Usuario(this.getUsuario(),this.getDni(),this.getApellido(),this.getNombre(),this.getDomicilio(),
						this.getSexo(),this.getEmail(),passwordGenerada,this.getFechaNacimiento());
				if(daoUser.alta(nuevoUser)){
					//mensaje="exito_alta_usuario";
					this.setPassword(passwordGenerada);
					mensaje="success_alta_usuario";
				}
				else{
					  FacesMessage message = new FacesMessage("sucedio un error inesperado en el alta,reintente nuevamente");
				      context.addMessage("userAltaForm", message);
				      
				      mensaje="formulario_alta_usuario";
				}
				
			}
			else{
				  FacesMessage message = new FacesMessage("nombre de usuario utilizado,intente con otro nombre de usuario");
			      context.addMessage("userAltaForm", message);
			      mensaje="formulario_alta_usuario";
			}
			
			
			
			return mensaje;
			
			
			
		}
	    
}
