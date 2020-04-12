package daos.daosActividad;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import model.Ruta;
import model.Actividad;
import utilitarios.EMF;

public class JPADaoActividad implements IDaoActividad{
	public boolean alta(Actividad dato){
		boolean ok=true;
		EntityManager ent=EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
		etx.begin();
	    try {
	    	 ent.persist(dato);
	         etx.commit();
	    } catch (Exception e) {
		     etx.rollback();
		     System.out.println("ocurrio un error al guardar");
		     e.printStackTrace();
		     ok=false;
	    }
	    finally{
	    	ent.close();
	    }
	    return ok;
	}
	public void baja(Actividad dato){
		EntityManager ent=EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
		etx.begin();
		try{
		    ent.remove(ent.merge(dato));
            etx.commit();
		}
		catch(Exception e){
			 System.out.println("ocurrio un error al borrar");
		     e.printStackTrace();
		}
		finally{
            ent.close();
		}
	}
	
	public boolean modificacion(Actividad dato){
		EntityManager ent=EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
	    boolean ok=true;
		etx.begin();
		try{
		     ent.merge(dato);
             etx.commit();
		}
		catch(Exception e){
			 etx.rollback();
			 ok=false;
		     System.out.println("ocurrio un error al modificar");
		     e.printStackTrace();
		}
		finally{
             ent.close();
		}
		return ok;
      
	}
		
	public List<Actividad> listar(){	
		EntityManager ent=EMF.getEntityManager();
		return (List<Actividad>)(ent.createQuery("from model.Actividad")).getResultList();
			
	}
	
	
	
	
	public Actividad buscar(String nombre){
		EntityManager ent=EMF.getEntityManager();
		Actividad actividad=null;
	  try {
		Query q = ent.createQuery("SELECT a FROM Actividad a WHERE a.nombre = :nombre");
	    q.setParameter("nombre", nombre);
	    actividad=(Actividad)q.getSingleResult();
	  }catch ( Exception e) {
			 System.out.println("ocurrio un error en la busqueda");
		     e.printStackTrace();
	     }	
	     finally{
	    	 ent.close();
	     } 
	     return actividad;
	}
	public Actividad buscarPorId(Long id){
	     EntityManager ent=EMF.getEntityManager();
	     Actividad dato=null;
	     try {
		     dato=(Actividad)ent.find(Class.forName("model.Actividad"), id);
	     } catch ( Exception e) {
	    	 System.out.println("ocurrio un error en la busqueda");
		     e.printStackTrace();
	     }	
	     finally{
	    	 ent.close();
	     }
	     return dato;
    }
	public void borrarPorId(Long id){
		EntityManager ent=EMF.getEntityManager();
		EntityTransaction etx=ent.getTransaction();
		etx.begin();
		try {
			
	Actividad dato=(Actividad)ent.find(Class.forName("model.Actividad"),id);
			ent.remove(dato);
			etx.commit();
		} catch ( Exception e) {
			System.out.println("ocurrio un error al borrar");
			e.printStackTrace();
		}
		finally{
			ent.close();
		}
		
	}
	public boolean tieneRutasAsociadas(Long id){
		EntityManager ent=EMF.getEntityManager();
		List<Ruta> rutas=(List<Ruta>)(ent.createQuery("select r from Ruta r where r.actividad.id = "+id)).getResultList();
	    return (rutas !=null) && (!rutas.isEmpty());
		
	}
}
