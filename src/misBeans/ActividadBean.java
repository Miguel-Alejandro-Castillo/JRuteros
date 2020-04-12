package misBeans;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import daos.daosActividad.IDaoActividad;
import utilitarios.Factory;
import model.Actividad;
//import model.Usuario;

public class ActividadBean {
	private Long id;
	private String actividad;
	private Actividad act;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

	public ActividadBean() {
		super();
	}

	public String getActividad() {
		return actividad;
	}

	public void setActividad(String actividad) {
		this.actividad = actividad;
	}
	public String alta(){
		FacesContext context=FacesContext.getCurrentInstance();
		 IDaoActividad daoActividad=Factory.daoActividad();
		 if(daoActividad.buscar(this.getActividad()) != null ){
			  FacesMessage message = new FacesMessage("nombre de actividad ya usado,use otro nombre ");
		      context.addMessage("formAltaActividad", message);
		       return "alta_actividad";
		 }else{
		
			 if(daoActividad.alta(new Actividad(this.getActividad()))  ){
				 return "home_admin";
			 }
			 else{
				 FacesMessage message = new FacesMessage("ocurrio un error inesperado,reintente nuevamente ");
			      context.addMessage("formAltaActividad", message);
			       return "alta_actividad";
			 }
		 }
	}
	public String modificacion(){
		FacesContext context=FacesContext.getCurrentInstance();
		 IDaoActividad daoActividad=Factory.daoActividad();
		 Actividad actividad=daoActividad.buscar(this.getActividad());
		 if( (actividad != null)&&(!this.getActividad().equals(act.getNombre())) ){
			  FacesMessage message = new FacesMessage("nombre de actividad ya usado,use otro nombre ");
		      context.addMessage("formEditActividad", message);
		       return "editar_actividad_actividad";
		 }else{
			 act.setNombre(this.getActividad());
			 if(daoActividad.modificacion(act) ){
				 return "actividades";
			 }
			 else{
				 FacesMessage message = new FacesMessage("ocurrio un error inesperado,reintente nuevamente ");
			      context.addMessage("formEditActividad", message);
			       return "editar_actividad";
			 }
		 }
	}
	public String editarActividad(){
	    act=Factory.daoActividad().buscarPorId(this.getId());
		this.setActividad(act.getNombre());
		return "editar_actividad";
	}
	public String habilitarDeshabilitarActividad(){
	       
		    Actividad actividad=Factory.daoActividad().buscarPorId(this.getId());
		    actividad.setHabilitado(!actividad.getHabilitado());
		    Factory.daoActividad().modificacion(actividad);
		    return "actividades";	    
	
	}
	public String borrarActividad(){
	  FacesContext context=FacesContext.getCurrentInstance();
	  IDaoActividad daoActividad=Factory.daoActividad();
	  if(!daoActividad.tieneRutasAsociadas(this.getId())){
		Factory.daoActividad().borrarPorId(this.getId());
		this.setId(null);
		this.setActividad(null);
		System.out.println("lo borreee");
		
		
	  }
	  else{
		  System.out.println("no lo puedo borrar");
		  FacesMessage message = new FacesMessage("No se puede borrar la actividad porque posee rutas asociadas");
	      context.addMessage("actividad"+this.getId(), message);
		 
	  }
	  return "actividades";
	}

}
