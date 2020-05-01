package daos.daosUser;
import javax.persistence.*;

import daos.JpaDaoGenerico;
import model.User;
import utils.EMF;

import java.util.*;
public class JPADaoUser implements IDaoUser {
	
	
	
	public boolean alta(User dato) throws Exception{
		boolean ok = true;
		EntityManager ent = EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
		etx.begin();
	    try {
	    	 ent.persist(dato);
	         etx.commit();
	    } catch (Exception e) {
		     etx.rollback();
		     e.printStackTrace();
		     ok = false;
		     throw e;
	    }
	    finally{
	    	ent.close();
	    }
	    return ok;
	}
	public void baja(User dato){
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
	
	public boolean modificacion(User dato) throws Exception{
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
		     e.printStackTrace();
		     throw e;
		}
		finally{
             ent.close();
		}
		return ok;
      
	}
		
	public List<User> listar(){	
		EntityManager ent=EMF.getEntityManager();
		return (List<User>)(ent.createQuery("from model.User ")).getResultList();
			
	}
	
	public User buscarPorId(Long id){
	     EntityManager ent=EMF.getEntityManager();
	     User dato=null;
	     try {
		     dato=(User)ent.find(Class.forName("model.User"), id);
	     } catch ( Exception e) {
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
			
			User dato=(User)ent.find(Class.forName("model.User"),id);
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
	
	public User buscar(String username ,String password){
		EntityManager ent=EMF.getEntityManager();
		User user=null;
	  try {
		Query q = ent.createQuery("SELECT u FROM User u WHERE u.usuario = :usuario AND u.contrasenia = :password");
	    q.setParameter("usuario", username);
	    q.setParameter("password", password);
	    user=(User)q.getSingleResult();
	  }catch ( Exception e) {
			 System.out.println("ocurrio un error en la busqueda");
		     e.printStackTrace();
	     }	
	     finally{
	    	 ent.close();
	     } 
	     return user;
	}
	public User buscar(String username){
		EntityManager ent=EMF.getEntityManager();
		User user=null;
	  try {
		Query q = ent.createQuery("SELECT u FROM User u WHERE u.usuario = :usuario");
	    q.setParameter("usuario", username);
	    user=(User)q.getSingleResult();
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
