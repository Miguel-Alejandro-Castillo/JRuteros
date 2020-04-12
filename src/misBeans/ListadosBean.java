package misBeans;
import java.util.List;
import java.util.ArrayList;
import model.Usuario;
import model.Actividad;
import utilitarios.Factory;
public class ListadosBean {
   private List<Usuario> listaUsuarios;
   private List<Actividad> listaActividades;
   
   public List<Actividad> getListaActividades() {
	   return listaActividades=Factory.daoActividad().listar();
	    
	      
  }
   public void setListaActividades(List<Actividad> listaActividades) {
	      this.listaActividades = listaActividades;
   }
   public List<Usuario> getListaUsuarios() {
	   listaUsuarios=Factory.daoUsuario().listar();
	    if(listaUsuarios == null){
	    	listaUsuarios=new ArrayList<Usuario>();
	    }
	    return listaUsuarios;    
   }
  	public void setListaUsuarios(List<Usuario> listaUsuarios) {
		this.listaUsuarios = listaUsuarios;
	}
   
    public ListadosBean() {
		super();
	}
   
    

     
}
