package daos;

import java.util.List;
import javax.persistence.*;
import utils.EMF;

public abstract class JpaDaoGenerico<T> implements IDaoGenerico<T>{
	private String model;
    public void setModel(String model){
    	this.model=model;
    }
	
	public void alta(T dato) throws Exception {
		EntityManager ent=EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
		etx.begin();
		
	    try {
	    	ent.persist(dato);
	        etx.commit();
	    } catch (Exception e) {
		     etx.rollback();
		     e.printStackTrace();
		     throw e;
	    }
	    finally{
	    	ent.close();
	    }
	}
	public void baja(T dato) throws Exception {
		EntityManager ent=EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
		etx.begin();
		try{
		    ent.remove(ent.merge(dato));
            etx.commit();
		}
		catch(Exception e){
			 etx.rollback();
		     e.printStackTrace();
		     throw e;
		}
		finally{
            ent.close();
		}
	}
	
	public void modificar(T dato){
		EntityManager ent=EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
		etx.begin();
		try{
		     ent.merge(dato);
             etx.commit();
		}
		catch(Exception e){
			 etx.rollback();
		     e.printStackTrace();
		}
		finally{
             ent.close();
		}
      
	}
		
	public List<T> listar(){	
		EntityManager ent=EMF.getEntityManager();
		return (List<T>)(ent.createQuery("from "+model)).getResultList();
			
	}

	public T buscarPorId(Long id){
	     EntityManager ent=EMF.getEntityManager();
	     T dato=null;
	     try {
		     dato=(T)ent.find(Class.forName("model."+model), id);
	     } catch (Exception e) {
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
			T dato=(T)ent.find(Class.forName("model."+model),id);
			ent.remove(dato);
			etx.commit();
		} catch ( Exception e) {
			etx.rollback();
			e.printStackTrace();
		}
		finally{
			ent.close();
		}
	 
		
		
		
		
	}
	
}