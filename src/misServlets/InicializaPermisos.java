package misServlets;
import java.io.*;
import java.util.*;
import javax.servlet.*;
/**
 * Application Lifecycle Listener implementation class InicializaPermisos
 *
 */
public class InicializaPermisos implements ServletContextListener {

    /**
     * Default constructor. 
     */
    public InicializaPermisos() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see ServletContextListener#contextDestroyed(ServletContextEvent)
     */
    public void contextDestroyed(ServletContextEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    public void contextInitialized(ServletContextEvent sce)  { 
    	ServletContext contexto=sce.getServletContext();
    	String permisos=  contexto.getInitParameter("permisos");
    	BufferedReader cat=null;
    	HashMap<String,HashMap<String,Boolean>> URIS=new HashMap<String,HashMap<String,Boolean>>();
    	try {
    	cat=new BufferedReader(new InputStreamReader(contexto.getResourceAsStream(permisos)));
    	String linea= cat.readLine();
    	while (linea != null) {
    		 String []lin=linea.split(" ");
    	    if(lin.length > 1){
    	       String URI=lin[0];
       		   HashMap<String,Boolean> aux=new HashMap<String,Boolean>();
    		   if((lin.length == 2)  &&( lin[1].equals("all"))){
    			   aux.put("all",true);
    		   }
    		   else{
    			   for (int i = 1; i < lin.length; i++) {
					  String []user=lin[i].split("=");
					  if(user.length == 2){
					      aux.put(user[0],Boolean.valueOf(user[1]));
					  } 
				   }	 
    		   }
    		   URIS.put(URI, aux);
    		 }
    	     linea= cat.readLine();
    	}
    	contexto.setAttribute("URIS",URIS);
    	contexto.log("lista de permisos de uris creada");
    	}catch(IOException e)
    	      {contexto.log("Ocurrió una excepción mientraS se leia el archivo permisos",e);
    	}finally{
    	     if (cat!=null) {
    	            try{
    	            	cat.close();
    	            } catch(IOException e) {
    	            	 
    	             }
    	     }
    	}
    }
	
}
