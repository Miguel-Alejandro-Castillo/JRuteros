package model;
import javax.persistence.*;
@Entity
@Table(name="ACTIVIDADES",uniqueConstraints=
@UniqueConstraint(columnNames = {"nombre"}))
public class Actividad {
	
	@Id @GeneratedValue
	private Long id;
	private String nombre;
	private boolean habilitado;
    
    public Actividad() {
    	super();
    	this.habilitado = true;
    }
    public Actividad(String nombre) {
		super();
		this.nombre = nombre;
		this.habilitado = true;
	}
    public Actividad(Long id,String nombre,boolean habilitado) {
	    this(nombre);
	    this.id=id;
	    this.habilitado=habilitado;
    
    }
    
    public Long getId(){
    	return this.id;
    }
   
    public void setId(Long id) {
		this.id = id;
	}
	public String getNombre() {  
        return this.nombre;
    }

    public void setNombre(String nombre) {
         this.nombre=nombre;
    }

   
    public boolean getHabilitado() {
        return this.habilitado;
    }
	public void setHabilitado(boolean habilitado) {
        this.habilitado=habilitado;
    }
	
	
	public boolean equals(Object o){
		Actividad act=(Actividad)o;
		if (act instanceof Actividad) {
			if((this.getId() == act.getId())&&(this.getHabilitado() == act.getHabilitado())&&(this.getNombre().equals(act.getNombre()))){
				return true;
			}
			else
				return false;
		}
		else
			return false;
		
	}
	public Actividad clone(){
		return new Actividad(this.getId(),this.getNombre(),this.getHabilitado());
	}
	

}