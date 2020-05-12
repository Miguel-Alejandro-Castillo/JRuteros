package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "OutcomeBean")
@ApplicationScoped
public class OutcomeBean {

	public OutcomeBean() {
		super();
	}
	
	/* HOME */
	public String home(){
		// agregar logica para que redireccione al home que corresponda al tipo de usuario
		return "home";
	}
	
	/* ABM RUTA */
	public String misRutas(){
		return "mis_rutas";
	}
	
	public String buscadorRutas(){
		return "buscador_de_rutas";
	}

	public String nuevaRuta(){
		return "nueva_ruta";
	}
	
	public String editarRuta(){
		return "editar_ruta";
	}
	
	public String detalleRuta(){
		return "detalle_ruta";
	}
	
	/* USUARIOS Y ADMINISTRADORES */
	
	public String usuarios() {
		return "usuarios";
	}
	
	public String editarUsuario() {
		return "editar_usuario";
	}
	
	public String nuevoUsuario() {
		return "nuevo_usuario";
	}
	
	/* ACTIVIDADES */
	
	public String nuevaActividad(){
		return "nueva_actividad";
	}
	
	public String editarActividad(){
		return "editar_actividad";
	}
	
	public String actividades(){
		return "actividades";
	}
	

}
