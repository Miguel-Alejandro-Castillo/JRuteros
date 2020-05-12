package beans;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import utils.Factory;
import model.Actividad;
import model.Dificultad;
import model.Formato;
import model.Privacidad;
import model.Ruta;
import model.Usuario;

@ManagedBean(name = "ListadosBean")
@RequestScoped
public class ListadosBean {
   
   public ListadosBean() {
		super();
   }
   
   public List<Actividad> getActividades() {
	return Factory.daoActividad().listar();
   }
   
   public List<Actividad> getActividadesHabilitadas() {
	return Factory.daoActividad().listarHabilitadas();
   }
   
   public List<Dificultad> getDificultades() {
	return Factory.daoDificultad().listar();
   }

   public List<Formato> getFormatos() {
	return Factory.daoFormato().listar();
   }

   public List<Privacidad> getPrivacidades(){
	   return Factory.daoPrivacidad().listar();
   }
   
   public List<Ruta> getMisRutas(Long usuarioId){
	   return Factory.daoRuta().buscarPorPropietarioId(usuarioId);
   }
   
   public List<Usuario> getUsuarios(){
	   return Factory.daoUsuario().listar();
   }
     
}
