package beans;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import org.apache.commons.io.FilenameUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.Ruta;
import model.Usuario;
import model.Valoracion;
import utils.Factory;
import utils.FiltrosBuscadorRutas;
import utils.OrdenamientosBuscadorRutas;
import utils.ResultadoBuscadorRutas;

@ManagedBean(name = "BuscadorRutasBean")
@SessionScoped
public class BuscadorRutasBean {

	@ManagedProperty(value = "#{OutcomeBean}")
	private OutcomeBean outcome;

	@ManagedProperty(value = "#{UsuarioLogueadoBean}")
	private UsuarioLogueadoBean usuarioLogueado;

	private FiltrosBuscadorRutas filtros;

	private OrdenamientosBuscadorRutas ordenamientos;

	List<ResultadoBuscadorRutas> resultado;

	Ruta ruta;

	Valoracion valoracion;

	List<String> images;

	private String recorridoJSON;

	public BuscadorRutasBean() {
		super();
		this.init();
	}

	private void init() {
		this.filtros = new FiltrosBuscadorRutas();
		this.ordenamientos = new OrdenamientosBuscadorRutas();
		this.resultado = new ArrayList<ResultadoBuscadorRutas>();
		this.valoracion = new Valoracion();
		this.ruta = new Ruta();
	}

	public String buscarRutas() {
		this.getFiltros().setUsuarioId(this.getUsuarioLogueado().getId());
		this.resultado = Factory.daoRuta().buscadorRutas(this.getFiltros(), this.getOrdenamientos());
		return this.getOutcome().buscadorRutas();
	}

	public String buscadorRutas() {
		this.init();
		return this.getOutcome().buscadorRutas();
	}

	public String detalleRutaByAjax(Long idRuta) {
		if (idRuta != null) {
			Ruta ruta = Factory.daoRuta().buscarPorId(idRuta);
			this.setRuta(ruta);
			// Map<String, Object> result =
			// Factory.daoValoracion().getPromedioPuntajeYCantidadVisitas(idRuta);
			// this.setCantidadVisitas((Long) result.get("cantidadVisitas"));
			// this.setPuntajePromedio((BigDecimal)
			// result.get("puntajePromedio"));
			this.recorridoJSON = "";
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				this.recorridoJSON = objectMapper.writeValueAsString(ruta.getRecorrido());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return this.getOutcome().buscadorRutas();
	}

	public String valoracionRuta(Long usuarioId, Long rutaId) {
		this.valoracion = Factory.daoValoracion().buscarPorUsuarioIdYRutaId(usuarioId, rutaId);
		if (this.valoracion == null) {
			Usuario usuario = Factory.daoUsuario().buscarPorId(usuarioId);
			Ruta ruta = Factory.daoRuta().buscarPorId(rutaId);
			this.valoracion = new Valoracion(usuario, ruta);
		}
		return this.getOutcome().buscadorRutas();
	}

	public String guardarValoracion() {
		try {
			Valoracion valoracion = Factory.daoValoracion().buscarPorUsuarioIdYRutaId(
					this.getValoracion().getUsuario().getId(), this.getValoracion().getRuta().getId());
			if (valoracion == null) {
				Factory.daoValoracion().alta(this.getValoracion());
			} else {
				Factory.daoValoracion().modificar(this.getValoracion());
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage(), e);
		}
		return this.getOutcome().buscadorRutas();
	}

	public FiltrosBuscadorRutas getFiltros() {
		return filtros;
	}

	public void setFiltros(FiltrosBuscadorRutas filtros) {
		this.filtros = filtros;
	}

	public OrdenamientosBuscadorRutas getOrdenamientos() {
		return ordenamientos;
	}

	public void setOrdenamientos(OrdenamientosBuscadorRutas ordenamientos) {
		this.ordenamientos = ordenamientos;
	}

	public List<ResultadoBuscadorRutas> getResultado() {
		return resultado;
	}

	public void setResultado(List<ResultadoBuscadorRutas> resultado) {
		this.resultado = resultado;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public Valoracion getValoracion() {
		return valoracion;
	}

	public void setValoracion(Valoracion valoracion) {
		this.valoracion = valoracion;
	}

	public List<String> getImages() {
		return images;
	}

	public void setImages(List<String> images) {
		this.images = images;
	}

	public OutcomeBean getOutcome() {
		return outcome;
	}

	public void setOutcome(OutcomeBean outcome) {
		this.outcome = outcome;
	}

	public UsuarioLogueadoBean getUsuarioLogueado() {
		return usuarioLogueado;
	}

	public void setUsuarioLogueado(UsuarioLogueadoBean usuarioLogueado) {
		this.usuarioLogueado = usuarioLogueado;
	}

	public String getRecorridoJSON() {
		return recorridoJSON;
	}

	public void setRecorridoJSON(String recorridoJSON) {
		this.recorridoJSON = recorridoJSON;
	}

}
