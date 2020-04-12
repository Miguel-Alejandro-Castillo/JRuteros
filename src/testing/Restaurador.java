package testing;

import java.io.IOException;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import utilitarios.EMF;

public class Restaurador {
	private static String []tablas={"actividades","administradores","dificultades","formatos","fotos","privacidades",
	         "puntos","rutas","users","usuarios","valoraciones","rutas_fotos","rutas_valoraciones",
	         "rutas_puntos","usuarios_rutas"};
	public static void dropTable(String table){
		EntityManager ent=EMF.getEntityManager();
		EntityTransaction etx=ent.getTransaction();
		etx.begin();
		String query;
		query= "SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 ";
		ent.createNativeQuery(query).executeUpdate();
		query= "DROP TABLE IF EXISTS "+table;
		ent.createNativeQuery(query).executeUpdate();
		query="SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS";
		ent.createNativeQuery(query).executeUpdate();
		etx.commit();
		ent.close();
		
	}
	public static void dropAllTables(){	
		for (int i = 0; i < tablas.length; i++) {
			dropTable(tablas[i]);
		}	
	}
	/*public static void truncateTable(String tabla){
		EntityManager ent=EMF.getEntityManager();
		EntityTransaction etx=ent.getTransaction();
		etx.begin();
		String query="TRUNCATE "+tabla;
		ent.createNativeQuery(query).executeUpdate();
		etx.commit();
		ent.close();
	}
	public static void truncateAllTables(){
		for (int i = 0; i < tablas.length; i++) {
			truncateTable(tablas[i]);
		}
	}
	*/
	public static void main(String[] args) throws IOException{
		dropAllTables();  
	}

}
