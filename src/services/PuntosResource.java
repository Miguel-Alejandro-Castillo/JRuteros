package services;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;



//import com.javapapers.webservices.rest.jersey.DELETE;

import model.*;
import utilitarios.AutoIncremental;


@Path("/rutas")
public class PuntosResource {
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	PuntoService puntoService;
	
   //static public Long id=(long)0;
   //static public List<Punto> puntos=new ArrayList<Punto>();
	
	public PuntosResource() {
		puntoService=PuntoService.getInstance();
	}
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Punto> getPuntos(){		
		return puntoService.getPuntos();
	}
  

	

	@POST
	@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public void createAnimal(
			@FormParam("lat") String lat,
			@FormParam("lon") String lng,
			@Context HttpServletResponse servletResponse) throws IOException {
		    Punto punto = new Punto(AutoIncremental.getAutoIncremental(), lat,lng );
		    //id++;
		   // puntos.add(punto);
		    puntoService.agregarPunto(punto);
		    servletResponse.sendRedirect("./rutas/");
	}
	@DELETE
	public void deletePuntos() {
		//puntos=new ArrayList<Punto>();
		puntoService.borrarPuntos();
		//id=(long)0;
	}
	
	/*@DELETE
    @Path("{punto}")
	public void  deletePunto(@FormParam("id") String id) {
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
	*/
	@Path("{punto}")
	public PuntoResource getAnimal(@PathParam("punto") String id) {
		return new PuntoResource(uriInfo, request, id);
	}


}
