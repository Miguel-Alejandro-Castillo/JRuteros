package services;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;




public class PuntoResource {
	
	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	String id;
	PuntoService puntoService;

	public PuntoResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		puntoService=PuntoService.getInstance();
		
	}
	@DELETE
	public void deleteAnimal() {
		puntoService.borrarPunto(id);
	}
}
