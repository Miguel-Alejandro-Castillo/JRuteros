package utils;

import javax.persistence.*;

public class EMF {
	 private static EntityManagerFactory emf=null;
	 private EMF(){
	 }
	 public static EntityManager getEntityManager(){
		 if(emf == null)
             emf= Persistence.createEntityManagerFactory("pro");
		 return emf.createEntityManager();
		 
	 }

}
