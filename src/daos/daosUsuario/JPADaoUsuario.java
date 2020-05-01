package daos.daosUsuario;
import javax.persistence.*;
import model.Usuario;
import utils.EMF;

import java.util.*;
public class JPADaoUsuario implements IDaoUsuario {
	
	
	
	public boolean alta(Usuario dato){
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
	public void baja(Usuario dato){
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
	
	public boolean modificacion(Usuario dato){
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
		
	public List<Usuario> listar(){	
		EntityManager ent=EMF.getEntityManager();
		return (List<Usuario>)(ent.createQuery("from model.Usuario ")).getResultList();
			
	}
	
	public Usuario buscarPorId(Long id){
	     EntityManager ent=EMF.getEntityManager();
	     Usuario dato=null;
	     try {
		     dato=(Usuario)ent.find(Class.forName("model.Usuario"), id);
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
			
			Usuario dato=(Usuario)ent.find(Class.forName("model.Usuario"),id);
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
	
	public Usuario buscar(String username ,String password){
		EntityManager ent=EMF.getEntityManager();
		Usuario user=null;
	  try {
		Query q = ent.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario AND u.contrasenia = :password");
	    q.setParameter("usuario", username);
	    q.setParameter("password", password);
	    user=(Usuario)q.getSingleResult();
	  }catch ( Exception e) {
			 System.out.println("ocurrio un error en la busqueda");
		     e.printStackTrace();
	     }	
	     finally{
	    	 ent.close();
	     } 
	     return user;
	}
	public Usuario buscar(String username){
		EntityManager ent=EMF.getEntityManager();
		Usuario user=null;
	  try {
		Query q = ent.createQuery("SELECT u FROM Usuario u WHERE u.usuario = :usuario");
	    q.setParameter("usuario", username);
	    user=(Usuario)q.getSingleResult();
	  }catch ( Exception e) {
			 System.out.println("ocurrio un error en la busqueda");
		     e.printStackTrace();
	     }	
	     finally{
	    	 ent.close();
	     } 
	     return user;
	}

}
