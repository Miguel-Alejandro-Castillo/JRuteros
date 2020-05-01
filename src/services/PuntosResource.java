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
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.UriInfo;
import model.*;
import utils.AutoIncremental;
import utils.Factory;

@Path("/puntos")
public class PuntosResource {

	@Context
	UriInfo uriInfo;
	@Context
	Request request;
	PuntoService puntoService;

	public PuntosResource() {
		super();
		this.puntoService = PuntoService.getInstance();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Punto> getPuntos(@QueryParam("rutaId") Long rutaId) {
		return puntoService.getPuntos();
	}
	
	@GET
	@Path("/ruta/{rutaId}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Set<Punto> getPuntosByRutaId(@PathParam("rutaId") Long rutaId) {
		 Ruta ruta = Factory.daoRuta().buscarPorId(rutaId);
		 return ruta != null ? ruta.getRecorrido() : new HashSet<Punto>();
	}

	@POST
	@Consumes({MediaType.APPLICATION_FORM_URLENCODED, MediaType.APPLICATION_JSON} )
	@Produces(MediaType.TEXT_HTML)
	public void createPunto(Punto punto,
			@Context HttpServletResponse servletResponse) throws IOException {
		//Punto punto = new Punto(AutoIncremental.getAutoIncremental(), lat, lng);
		punto.setId(AutoIncremental.getAutoIncremental());
		puntoService.agregarPunto(punto);
		servletResponse.sendRedirect("./rutas/");
	}

	@DELETE
	public void deletePuntos() {
		this.puntoService.borrarPuntos();
	}
	
	@DELETE
	@Path("{punto}")
	public void deletePunto(@PathParam("punto") Long id) {
		this.puntoService.borrarPunto(id);
	}

}
