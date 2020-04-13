package testing;
import java.util.List;
import utilitarios.*;
import java.util.ArrayList;
import java.math.BigDecimal;
import java.sql.Date;
import model.*;
import daos.*;
import daos.daosActividad.IDaoActividad;
import daos.daosUsuario.IDaoUsuario;
public class ProviderTest {
    private List<Actividad> actividades;
    private Usuario usuario;
    private Ruta ruta;
	public ProviderTest(){
		actividades=new ArrayList<Actividad>();
		ruta=new Ruta();
	}
	
	public void pruebaABMActividad(){
		System.out.println("TEST ABM_ACTIVIDAD");
		//List<Actividad> actividadesRecuperadas;
		IDaoActividad dao=Factory.daoActividad();
		//-------------CREACION DE ACTIVIDADES-----------------------//
		ArrayList<Actividad> acts=new ArrayList<Actividad>();
		acts.add(new Actividad("Alpinismo"));
		acts.add(new Actividad("Senderismo"));
		acts.add(new Actividad("Motociclismo"));
		acts.add(new Actividad("Actividad aburridaa"));
		
	
	    //--------------PRUEBA DEL ALTA-----------------------------//
		for (int i = 0; i < acts.size(); i++) {
			dao.alta(acts.get(i));
		}
	    actividades=dao.listar();
		if(actividades.size() != 4){
			System.out.println("Fallo el alta");
			System.out.println("FAILURE TEST");	
		}
		else{
			//------------PRUEBA DEL BORRADO-----------------------//
			
			
		    dao.borrarPorId(actividades.get(3).getId());
		    actividades=dao.listar();
		   if(actividades.size() != 3){
			   System.out.println("Fallo la baja");
			   System.out.println("FAILURE TEST");	
		   }
		   else{
			   //-----------PRUEBA DE LA MODIFICACION---------------//
			   
			   Actividad premodificado=actividades.get(0).clone();
			  
			   actividades.get(0).setHabilitado(false);
			   actividades.get(0).setNombre("AHORA SOY OTRO");
			   dao.modificacion(actividades.get(0));
			   Actividad postmodificado=dao.buscarPorId(premodificado.getId());
			   
			   
			   if(premodificado.equals(postmodificado)){
				   System.out.println("Fallo la modificacion");
				   System.out.println("FAILURE TEST");
			   }
			   else{
				   System.out.println("ABM CORRECTO");
				   System.out.println("SUCCESS TEST");
				   actividades=dao.listar();
			   }
		   }
		}
		System.out.println("-------------------------------------------------------------");	
	}
	public void pruebaABMUsuario(){
		System.out.println("TEST ABM_USUARIO");
		Usuario usuario1=new Usuario("pepito",(long)36011092,"perez","pepe","1 y 60",'m',
					"aaas@sss", "AAa",new Date(1));
	    Usuario usuario2=new Usuario("pepa",(long)36012292,"poiz","peepaita","1 y 50",'f',
					"bbbs@sss", "AAa",new Date(2));
		 IDaoUsuario dao=Factory.daoUsuario();
		 ///---------------PRUEEBA DE ALTA-------------///
		 dao.alta(usuario1);
		 dao.alta(usuario2);
	     List<Usuario> lista=dao.listar();
	     if(lista.size() !=2){
	    	    System.out.println("Fallo el alta");
				System.out.println("FAILURE TEST");
	     }
	     else{//----------------PRUEBA DE MODIFICACION-----//
	    	 usuario2=lista.get(1);
	    	 usuario2.setEmail("mimail@yahoo");
	    	 dao.modificacion(usuario2);
	    	 usuario2=dao.buscarPorId(usuario2.getId());
	    	 if(usuario2 != null){
	    		 if (!usuario2.getEmail().equals("mimail@yahoo")) {
	    			    System.out.println("Fallo la modificacion");
						System.out.println("FAILURE TEST");
				 }
	    		 else{
	    			 //-------PRUEBA DE BORRADO---------------//
	    			 dao.baja(usuario2);
	    			 lista=dao.listar();
	    			 if(lista.size() !=1){
	    				   System.out.println("Fallo la baja");
	    				   System.out.println("FAILURE TEST");
	    			 }
	    			 else{
	    				System.out.println("ABM CORRECTO");
	  				    System.out.println("SUCCESS TEST");
	  				    usuario=lista.get(0);
	    			 }
	    		 }
	    	 }
	    	 else{
	    		    System.out.println("Fallo la modificacion");
					System.out.println("FAILURE TEST"); 
	    	 }
	    	 
	     }
	     System.out.println("-------------------------------------------------------------");
		 
		 
	}
	public void pruebaABMRuta(){
		 System.out.println("TEST ABM_RUTA");
		 Privacidad p=new Privacidad("Publica");
		 Formato f=new  Formato("Solo Ida");
		 Dificultad d=new Dificultad("intermedio");
		 Foto fhotoa=new Foto("mifoto1.jpeg","/home/var/");
		 Foto fhotob=new Foto("mifoto2.jpeg","/home/var/");
		 Valoracion val=new Valoracion(5,usuario);
		 Actividad a=new Actividad("PARACAIDISMO");
		 Punto pa=new Punto("-11","111");
		 Punto pb=new Punto("-22","211");
		 
		 ruta.setActividad(a);
		 ruta.setDescripcion("un terrible presagio");
		 ruta.setDificultad(d);
		 ruta.setDistancia(new BigDecimal(20));
		 ruta.setFechaRealizacion(new java.sql.Date(1));
		 ruta.setFormato(f);
		 ruta.setNombre("ruta bacaalao");
		 ruta.setPrivacidad(p);
		 ruta.setTiempo(null);
		
		 //ruta.agregarValoracion(val);
		 //ruta.agregarVisitante(usuario);
		ruta.agregarFoto(fhotoa);
		ruta.agregarFoto(fhotob);
		ruta.agregarPunto(pa);
		ruta.agregarPunto(pb);
		
		 DaoRuta dao=Factory.daoRuta();
		 
		///---------------PRUEEBA DE ALTA-------------///
		 dao.alta(ruta);
		 List<Ruta> lista=dao.listar();
		 if(lista.size() != 1 ){
			    System.out.println("Fallo el alta");
				System.out.println("FAILURE TEST");
		 }
		 else{
			//----------------PRUEBA DE MODIFICACION-----//
	    	 ruta=lista.get(0);
	    	 ruta.setDescripcion("cambie la descripcion");
	    	 dao.modificacion(ruta);
	    	 ruta=dao.buscarPorId(ruta.getId());
	    	 if(ruta != null){
	    		 if (!ruta.getDescripcion().equals("cambie la descripcion")) {
	    			    System.out.println("Fallo la modificacion");
						System.out.println("FAILURE TEST");
				 }
	    		 else{
	    			    System.out.println("ABM CORRECTO");
	  				    System.out.println("SUCCESS TEST");
	    		 }
	    	 }
	         else{
	        	    System.out.println("Fallo la modificacion");
					System.out.println("FAILURE TEST");
	         }
		 }
	    	 
		 
		 
		 System.out.println("-------------------------------------------------------------");
		 
		 //System.out.println(ruta.getId());
		 
	    
	}
}
