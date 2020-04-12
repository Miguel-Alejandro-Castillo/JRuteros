package daos;
import java.util.List;

import javax.inject.Singleton;
import javax.persistence.*;


import utilitarios.EMF;



public abstract class JpaDaoGenerico<T> implements IDaoGenerico<T>{
	private String model;
    public void setModel(String model){
    	this.model=model;
    }
	
	public void alta(T dato){
		EntityManager ent=EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
		etx.begin();
		
	    try {
	    	 //dato=ent.merge(dato);
	         //etx.commit();
	        // etx.begin();
	         //ent.refresh(dato);
	    	ent.persist(dato);
	         etx.commit();
	    } catch (Exception e) {
		     etx.rollback();
		     System.out.println("ocurrio un error al guardar");
		     e.printStackTrace();
	    }
	    finally{
	    	ent.close();
	    }
	}
	public void baja(T dato){
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
	
	public void modificacion(T dato){
		EntityManager ent=EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
		etx.begin();
		try{
		     ent.merge(dato);
             etx.commit();
		}
		catch(Exception e){
			 etx.rollback();
		     System.out.println("ocurrio un error al modificar");
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
	/*public T buscarPorId(int id){
		EntityManager ent=EMF.getEntityManager();
		Query query=ent.createQuery("from "+model+" where id = :id");
		query.setParameter("id",new Long(id));
		try {
			return (T)query.getSingleResult();
		} catch ( NoResultException e) {
			return null;
		}	
	}
	*/
	public T buscarPorId(Long id){
	     EntityManager ent=EMF.getEntityManager();
	     T dato=null;
	     try {
		     dato=(T)ent.find(Class.forName("model."+model), id);
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
			T dato=(T)ent.find(Class.forName("model."+model),id);
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
	
}