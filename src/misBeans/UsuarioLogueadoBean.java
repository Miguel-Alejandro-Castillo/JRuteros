package misBeans;


import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;


import utilitarios.Factory;
import model.User;

import model.Usuario;

import daos.DaoUsuario;
import daos.daosUser.IDaoUser;
import daos.daosUsuario.IDaoUsuario;

public class UsuarioLogueadoBean {
	
	//private User user;
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
    
	private String passwordAnt;
	private String passwordNew;
	
	private Usuario user;
	
	public UsuarioLogueadoBean() {
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
	public String getPasswordAnt() {
		return passwordAnt;
	}
	public void setPasswordAnt(String passwordAnt) {
		this.passwordAnt = passwordAnt;
	}
	public String getPasswordNew() {
		return passwordNew;
	}
	public void setPasswordNew(String passwordNew) {
		this.passwordNew = passwordNew;
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

	 public String login(){
		   FacesContext context = FacesContext.getCurrentInstance(); 
			user=Factory.daoUsuario().buscar(this.getUsuario(),this.getPassword());
			if(user != null ){
				if(user.isHabilitado()){
					this.initialize(user.getId(), user.getUsuario(),user.getDni(),
			        user.getApellido(),user.getNombre(),user.getDomicilio(),
			        user.getSexo(),user.getEmail(),user.getPassword(),user.getFechaNacimiento());
				   context.getExternalContext().getSessionMap().put("id", user.getId());
				   context.getExternalContext().getSessionMap().put("rol","usuario");
				   //context.getExternalContext().getSessionMap().put("habilitado",usuario.isHabilitado());
				   return "home_usuario";
			    }
			    else{
			    	FacesMessage message = new FacesMessage("Usted no esta habilitado,por favor contactese con el administrador");
			        context.addMessage("loginForm", message);
			    	return "login";
			    }
			}
			else{
				 User administrador=Factory.daoUser().buscar(this.getUsuario(),this.getPassword());
				 if(administrador != null){
				     context.getExternalContext().getSessionMap().put("id", administrador.getId());
				     context.getExternalContext().getSessionMap().put("rol","administrador");
				    //context.getExternalContext().getSessionMap().put("habilitado",true);
				    return "home_administrador"; 
			    }
			    else{
		           FacesMessage message = new FacesMessage("Invalid Username and/or Password");
		           context.addMessage("loginForm", message);
		           return "login";
			    }
		  }
	    
		/*FacesContext context = FacesContext.getCurrentInstance();
		IDaoUser daoUser=Factory.daoUser();
	    user=daoUser.buscar(this.getUsuario(),this.getPassword());
	    if(user != null){
	        this.initialize(user.getId(), user.getUsuario(),user.getDni(),
	        		user.getApellido(),user.getNombre(),user.getDomicilio(),
	        		user.getSexo(),user.getEmail(),user.getPassword(),user.getFechaNacimiento());
	        DaoUsuario daoUsuario=Factory.daoUsuario();
			Usuario usuario=daoUsuario.buscarPorId(user.getId());
			if(usuario != null ){
				if(usuario.isHabilitado()){
				   context.getExternalContext().getSessionMap().put("id", user.getId());
				   context.getExternalContext().getSessionMap().put("rol","usuario");
				   //context.getExternalContext().getSessionMap().put("habilitado",usuario.isHabilitado());
				   return "home_usuario";
			    }
			    else{
			    	return "login";
			    }
			}
			else{
				 context.getExternalContext().getSessionMap().put("id", user.getId());
				 context.getExternalContext().getSessionMap().put("rol","administrador");
				 //context.getExternalContext().getSessionMap().put("habilitado",true);
				 return "home_administrador";
			}
			
		}
		else {
		      FacesMessage message = new FacesMessage("Invalid Username and/or Password");
		      context.addMessage("loginForm", message);
		     
		      return "login";
		}
		*/
	}
	
	public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "login";
	}
	public String  editarUsuario(){
		String mensaje="";
		boolean ocurrioError=true;
		String para="";
		String destino="";
		boolean intentarModificarUsuario=false; 
		FacesContext context = FacesContext.getCurrentInstance();
	if((!this.getPasswordAnt().isEmpty())&&
	   (!this.getPasswordNew().isEmpty()))
	{
	   if(!this.getPasswordAnt().equals(this.getPassword())){
		 mensaje="Password invalida,ingrese su password actual si desea cambiar su password";
		 destino="password1";
	   }
	   else{
		   if((!this.getUsuario().equals(user.getUsuario()))&&(Factory.daoUsuario().buscar(this.getUsuario()) != null)){
				mensaje="el usuario ya esta siendo usado,por favor ingrese un nuevo usuario";
			    para="nick";
			}
		   else{
			   this.setPassword(this.getPasswordNew());
			   intentarModificarUsuario=true;
		   }
		   
	   }
	}
	else if((!this.getPasswordAnt().isEmpty())&&
			(this.getPasswordNew().isEmpty()))
	{
	    mensaje="campo vacio,por favor ingrese una nueva password";
		para="password2";
	}
	else if((this.getPasswordAnt().isEmpty())&&
		    (!this.getPasswordNew().isEmpty()))
	{
		mensaje="campo vacio,por favor ingrese su password actual si desea cambiar su password ";
	    para="password2";
	}
	else{
		intentarModificarUsuario=true;
	
	}
	if(intentarModificarUsuario){
		       user.setApellido(this.getApellido());
		       user.setDni(this.getDni());
		       user.setDomicilio(this.getDomicilio());
		       user.setEmail(this.getEmail());
		       user.setUsuario(this.getUsuario());
		       user.setNombre(this.getNombre());
        	   user.setSexo(this.getSexo());
        	   user.setPassword(this.getPassword());
        	   user.setFechaNacimiento(this.getFechaNacimiento());
		
		if(Factory.daoUsuario().modificacion(user)){
    		destino="home_user";
    		ocurrioError=false;
    	}
    	else{
    	   mensaje="sucedio un error inesperado,por favor reintente nuevamente";
		   para="formEditUsuario";
    	}
	}
	if(ocurrioError){
		FacesMessage message = new FacesMessage(mensaje);
	    context.addMessage(para, message);
	    destino="editar_usuario";
	}
	
    return destino;
	}
}
