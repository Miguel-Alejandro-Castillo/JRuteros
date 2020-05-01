package beans;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import daos.daosActividad.IDaoActividad;
import model.Actividad;
import utils.Factory;

@ManagedBean(name = "ActividadBean")
@SessionScoped
public class ActividadBean {

	@ManagedProperty(value = "#{OutcomeBean}")
	private OutcomeBean outcome;

	private Long id;

	private String nombre;

	private boolean habilitado;

	public ActividadBean() {
		super();
	}

	@PostConstruct
	public void init() {
		// Map<String,String> params =
		// FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
		// String idActividad = params.get("id");
	}

	public String nuevaActividad() {
		this.setId(null);
		this.setNombre(null);
		this.setHabilitado(true);
		return this.getOutcome().nuevaActividad();
	}

	public String editarActividad(Long idActividad) {
		if(idActividad != null){
			Actividad actividad = Factory.daoActividad().buscarPorId(idActividad);
			this.setId(actividad.getId());
			this.setNombre(actividad.getNombre());
			this.setHabilitado(actividad.getHabilitado());
		}
		return this.getOutcome().editarActividad();
	}

	public String alta() {
		FacesContext context = FacesContext.getCurrentInstance();
		IDaoActividad daoActividad = Factory.daoActividad();
		Actividad actividad = daoActividad.buscar(this.getNombre());
		if ( actividad != null) {
			FacesMessage message = new FacesMessage("Nombre de actividad usado");
			context.addMessage("formAltaActividad", message);
			return this.getOutcome().nuevaActividad();
		} else {
			try {
				daoActividad.alta(new Actividad(this.getNombre()));
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("ocurrio un error inesperado, reintente nuevamente.");
				context.addMessage("formAltaActividad", message);
				return this.getOutcome().nuevaActividad();
			}
			return this.getOutcome().actividades();
		}
	}

	public String modificacion() {
		Actividad actividad;
		FacesContext context = FacesContext.getCurrentInstance();
		IDaoActividad daoActividad = Factory.daoActividad();
		actividad = daoActividad.buscar(this.getNombre());
		if (actividad != null && !actividad.getId().equals(this.getId())) {
			FacesMessage message = new FacesMessage("Nombre de actividad usado, reintente con otro nombre");
			context.addMessage("formEditActividad", message);
			return this.getOutcome().editarActividad();
		} else {
			try {
				actividad = new Actividad(this.getId(), this.getNombre(), this.isHabilitado());
				daoActividad.modificacion(actividad);
			} catch (Exception e) {
				FacesMessage message = new FacesMessage("ocurrio un error inesperado,reintente nuevamente ");
				context.addMessage("formEditActividad", message);
				return this.getOutcome().editarActividad();
			}
			return this.getOutcome().actividades();
		}
	}

	public String habilitarDeshabilitarActividad(Long idActividad) {
		try {
			Actividad actividad = Factory.daoActividad().buscarPorId(idActividad);
			actividad.setHabilitado(!actividad.getHabilitado());
			Factory.daoActividad().modificacion(actividad);
		} catch (Exception e) {
			throw new RuntimeException(e.getMessage(), e);
		}
		return this.getOutcome().actividades();
	}

	public String borrarActividad(Long idActividad) {
		FacesContext context = FacesContext.getCurrentInstance();
		IDaoActividad daoActividad = Factory.daoActividad();
		if (daoActividad.tieneRutasAsociadas(idActividad)) {
			FacesMessage message = new FacesMessage("No se puede borrar la actividad porque posee rutas asociadas");
			context.addMessage("actividad" + this.getId(), message);
			throw new RuntimeException("actividad.poseeRutasAsociadas");
		} else {
			try {
				Factory.daoActividad().borrarPorId(idActividad);
			} catch (Exception e) {
				throw new RuntimeException(e.getMessage(), e);
			}
		}
		return this.getOutcome().actividades();
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

	public boolean isHabilitado() {
		return habilitado;
	}

	public void setHabilitado(boolean habilitado) {
		this.habilitado = habilitado;
	}

	public OutcomeBean getOutcome() {
		return outcome;
	}

	public void setOutcome(OutcomeBean outcome) {
		this.outcome = outcome;
	}

}
