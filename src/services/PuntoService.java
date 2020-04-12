package services;

import java.util.ArrayList;
import java.util.List;

import model.Punto;

public class PuntoService {
	 
	    private   List<Punto> puntos;
	    
	    private static PuntoService  instance=null;
	    
	    private PuntoService(){
	          restart();
	    }
	    private void restart(){
	    	  
		      this.puntos=new ArrayList<Punto>();
	    }
	    public synchronized static PuntoService getInstance(){
	    	 if(instance == null){
	    		  instance=new PuntoService();	  
	    	 }
	    	 return instance;
	    }
	   
		
	    //-------------------------------------------------------
	    public List<Punto> getPuntos(){
	    	return this.puntos;
	    }
	    public void agregarPunto(Punto punto){
	    	this.getPuntos().add(punto);
	    }
	    public  void borrarPuntos(){
	    	  this.restart();
	    }
	    public void borrarPunto(String id){
	    	int pos=-1;
	        for (int i = 0; i < puntos.size(); i++) {
				   if( String.valueOf(puntos.get(i).getId()).equals(id)){
					   pos=i;
					   break;
				   }
			}
	        if(pos != -1){
	            puntos.remove(pos);
	        }
	    }
		
	    
	  
	    
	    
}
