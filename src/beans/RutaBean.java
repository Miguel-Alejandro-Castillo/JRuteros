package beans;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.convert.ConverterException;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import daos.DaoRuta;
import model.Actividad;
import model.Dificultad;
import model.Formato;
import model.Foto;
import model.Privacidad;
import model.Punto;
import model.Ruta;
import model.Usuario;
import model.Valoracion;
import java.util.UUID;
import utils.Factory;
import org.apache.commons.lang.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.apache.commons.io.FilenameUtils;

@ManagedBean(name = "RutaBean")
@SessionScoped
public class RutaBean {

	private static final String URL_REPOSITORY = System.getProperty("catalina.home") + File.separator + "images";

	@ManagedProperty(value = "#{OutcomeBean}")
	private OutcomeBean outcome;

	// @ManagedProperty(value = "#{param.id}")
	private Long id;

	private String nombre;

	private String descripcion;

	private Long actividad_id;

	private Long privacidad_id;

	private Long formato_id;

	private Long dificultad_id;

	// Id del usuario propietario de la ruta
	private Long propietario_id;

	private BigDecimal distancia;

	private Date tiempoEstimado;

	private Date fechaRealizacion;

	private Set<Foto> fotos;

	private List<Punto> recorrido;

	private Ruta ruta;

	private List<UploadFileBean> uploadFiles;

	private boolean uploading = false;

	private BigDecimal puntajePromedio;

	private Long cantidadVisitas;

	private String recorridoJSON;

	public RutaBean() {
		super();
		File f = new File(URL_REPOSITORY);
		if(!f.exists())
			f.mkdir();
	}

	@PostConstruct
	public void init() {
		this.uploadFiles = new ArrayList<UploadFileBean>();
	}

	public void uploadFilesAction() {
		try {
			this.uploading = true;
			Collection<Part> parts = getParts();
			for (Part part : parts) {
				if (StringUtils.isNotEmpty(part.getSubmittedFileName())) {
					UploadFileBean fileBean = new UploadFileBean();
					fileBean.setFileName(part.getSubmittedFileName());
					fileBean.setUploadProgress(100);
					fileBean.setFilePart(part);
					fileBean.setUuid(UUID.randomUUID().toString());
					this.uploadFiles.add(fileBean);
				}
			}
			/*
			 * class UploadThread extends Thread {
			 * 
			 * @Override public void run() { try { String uploadDir =
			 * "E:/images/"; for (UploadFileBean fileBean : uploadFiles) { File
			 * file = new File(uploadDir + fileBean.getFileName());
			 * file.createNewFile();
			 * 
			 * FileOutputStream outputStream = new FileOutputStream(file);
			 * InputStream inputStream =
			 * fileBean.getFilePart().getInputStream();
			 * 
			 * byte[] buffer = new byte[1024]; int length;
			 * 
			 * int sumLength = 0;
			 * 
			 * while ((length = inputStream.read(buffer)) != -1) { sumLength +=
			 * length; int calculatedpercent = (int) (((double) sumLength /
			 * (double) fileBean.getFilePart().getSize()) * 100);
			 * fileBean.setUploadProgress(calculatedpercent);
			 * outputStream.write(buffer, 0, length); }
			 * fileBean.setUploadProgress(100); outputStream.close(); } } catch
			 * (Exception e) { e.printStackTrace(); } finally { uploading =
			 * false; } }
			 * 
			 * }
			 * 
			 * if (CollectionUtils.isNotEmpty(this.uploadFiles)) { UploadThread
			 * uploadThread = new UploadThread(); uploadThread.start(); } else {
			 * uploading = false; }
			 */

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			this.uploading = false;
		}

	}
	
	public void borrarImagenAction(String uuid){
		this.getUploadFiles().removeIf(uf->uf.getUuid().equals(uuid));
		this.getFotos().removeIf(f->f.getUuid().equals(uuid));
	}

	private Collection<Part> getParts() throws Exception {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		return request.getParts();
	}

	public String crearRuta() {

		String outcome = "";
		FacesContext context = FacesContext.getCurrentInstance();
		try {

			DaoRuta daoRuta = Factory.daoRuta();
			Ruta ruta = new Ruta();
			ruta.setId(this.id);
			ruta.setNombre(this.nombre);
			ruta.setDescripcion(this.descripcion);
			ruta.setDistancia(this.distancia);
			ruta.setFechaRealizacion(this.fechaRealizacion);
			ruta.setTiempoEstimado(this.tiempoEstimado);
			this.fotos.addAll(this.obtenerImagenes(this.getUploadFiles()));
			ruta.setFotos(this.fotos);
			ruta.setRecorrido(this.recorrido);

			Actividad actividad = Factory.daoActividad().buscarPorId(this.actividad_id);
			ruta.setActividad(actividad);
			Dificultad dificultad = Factory.daoDificultad().buscarPorId(this.dificultad_id);
			ruta.setDificultad(dificultad);
			Formato formato = Factory.daoFormato().buscarPorId(this.formato_id);
			ruta.setFormato(formato);
			Privacidad privacidad = Factory.daoPrivacidad().buscarPorId(this.privacidad_id);
			ruta.setPrivacidad(privacidad);
			this.propietario_id = (Long) context.getExternalContext().getSessionMap().get("id");
			Usuario propietario = Factory.daoUsuario().buscarPorId(this.propietario_id);
			ruta.setPropietario(propietario);

			if (ruta.getId() != null) {
				outcome = this.getOutcome().editarRuta();
				daoRuta.modificar(ruta);
			} else {
				outcome = this.getOutcome().nuevaRuta();
				daoRuta.alta(ruta);
				Valoracion valoracion = new Valoracion(propietario, ruta, null, true);
				Factory.daoValoracion().alta(valoracion);
			}
			this.guardarImagenes(this.getUploadFiles());

			outcome = this.getOutcome().misRutas();
		} catch (Exception e) {
			e.printStackTrace();
			FacesMessage message = new FacesMessage("sucedio un error inesperado en el alta, reintente nuevamente");
			context.addMessage("rutaAltaForm", message);
		}
		return outcome;
	}

	private void guardarImagenes(List<UploadFileBean> uploadFiles) throws IOException {
		
		for (UploadFileBean uf : uploadFiles) {
			File targetFile = new File(URL_REPOSITORY + File.separator + uf.getUuid() + "."
					+ FilenameUtils.getExtension(uf.getFileName()));

			if (!targetFile.exists()) {
				Part part = uf.getFilePart();
				byte[] value = new byte[(int) part.getSize()];
				InputStream inputStream = part.getInputStream();
				inputStream.read(value);
				inputStream.close();
				OutputStream outStream = new FileOutputStream(targetFile);
				outStream.write(value);
				outStream.close();
			}
		}

	}

	private void eliminarImagenes(Set<Foto> fotos) {
		for (Foto foto : fotos) {
			File targetFile = new File(URL_REPOSITORY + File.separator + foto.getUuid() + "."
					+ FilenameUtils.getExtension(foto.getNombre()));
			targetFile.delete();
		}
	}

	private Set<Foto> obtenerImagenes() throws IOException, ServletException {
		HttpServletRequest servletRequest = this.getServletRequest();
		Set<Foto> fotos = new HashSet<Foto>();
		List<Part> parts = servletRequest.getParts().stream()
				.filter(p -> StringUtils.isNotEmpty(p.getContentType()) && p.getContentType().startsWith("image"))
				.collect(Collectors.toList());
		for (Part part : parts) {
			Foto foto = new Foto();
			foto.setNombre(part.getSubmittedFileName());
			foto.setContentType(part.getContentType());
			foto.setSize(part.getSize());
			byte[] value = new byte[(int) part.getSize()];
			InputStream inputStream = part.getInputStream();
			inputStream.read(value);
			inputStream.close();
			foto.setValue(value);
			fotos.add(foto);
		}
		return fotos;
	}

	private Set<Foto> obtenerImagenes(List<UploadFileBean> uploadFiles) {
		return uploadFiles.stream().filter(uf -> uf.getFilePart() != null).map(uf -> {
			Part part = uf.getFilePart();
			return new Foto(part.getSubmittedFileName(), part.getContentType(), part.getSize(), uf.getUuid());
		}).collect(Collectors.toSet());
	}

	private HttpServletRequest getServletRequest() {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest servletRequest = (HttpServletRequest) context.getExternalContext().getRequest();
		return servletRequest;
	}

	public String nuevaRuta() {
		this.id = null;
		this.nombre = null;
		this.descripcion = null;
		this.actividad_id = null;
		this.privacidad_id = null;
		this.formato_id = null;
		this.dificultad_id = null;
		this.propietario_id = null;
		this.distancia = null;
		this.tiempoEstimado = null;
		this.fechaRealizacion = null;
		this.fotos = new HashSet<Foto>();
		this.recorrido = new ArrayList<Punto>();
		this.uploadFiles = new ArrayList<UploadFileBean>();
		return this.getOutcome().nuevaRuta();
	}

	public String editarRuta(Long idRuta) {
		if (idRuta != null) {
			Ruta ruta = Factory.daoRuta().buscarPorId(idRuta);
			this.setId(ruta.getId());
			this.setNombre(ruta.getNombre());
			this.setDescripcion(ruta.getDescripcion());
			this.setActividad_id(ruta.getActividad().getId());
			this.setPrivacidad_id(ruta.getPrivacidad().getId());
			this.setFormato_id(ruta.getFormato().getId());
			this.setDificultad_id(ruta.getDificultad().getId());
			this.setPropietario_id(ruta.getPropietario().getId());
			this.setDistancia(ruta.getDistancia());
			this.setTiempoEstimado(ruta.getTiempoEstimado());
			this.setFechaRealizacion(ruta.getFechaRealizacion());
			this.setFotos(ruta.getFotos());
			this.setRecorrido(ruta.getRecorrido());

			this.uploadFiles = new ArrayList<UploadFileBean>();
			for (Foto foto : this.getFotos()) {
				UploadFileBean uploadFile = new UploadFileBean();
				uploadFile.setUuid(foto.getUuid());
				uploadFile.setFileName(foto.getNombre());
				uploadFile.setUploadProgress(100);
				this.uploadFiles.add(uploadFile);
			}
		}
		return this.getOutcome().editarRuta();
	}

	public String detalleRutaByAjax(Long idRuta) {
		if (idRuta != null) {
			Ruta ruta = Factory.daoRuta().buscarPorId(idRuta);
			this.setRuta(ruta);
			Map<String, Object> result = Factory.daoValoracion().getPromedioPuntajeYCantidadVisitas(idRuta);
			if(result!=null){
				this.setCantidadVisitas((Long) result.get("cantidadVisitas"));
				this.setPuntajePromedio((BigDecimal) result.get("puntajePromedio"));				
			}

			this.recorridoJSON = "";
			ObjectMapper objectMapper = new ObjectMapper();
			try {
				this.recorridoJSON = objectMapper.writeValueAsString(ruta.getRecorrido());
			} catch (JsonProcessingException e) {
				e.printStackTrace();
			}
		}
		return this.getOutcome().misRutas();
	}

	public String borrarRuta(Long rutaId) {
		DaoRuta daoRuta = Factory.daoRuta();
		try {
			Factory.daoValoracion().borrarPorRutaId(rutaId);
			Ruta ruta = daoRuta.buscarPorId(rutaId);
			daoRuta.baja(ruta);
			this.eliminarImagenes(ruta.getFotos());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return this.getOutcome().misRutas();

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Long getActividad_id() {
		return actividad_id;
	}

	public void setActividad_id(Long actividad_id) {
		this.actividad_id = actividad_id;
	}

	public Long getPrivacidad_id() {
		return privacidad_id;
	}

	public void setPrivacidad_id(Long privacidad_id) {
		this.privacidad_id = privacidad_id;
	}

	public Long getFormato_id() {
		return formato_id;
	}

	public void setFormato_id(Long formato_id) {
		this.formato_id = formato_id;
	}

	public Long getDificultad_id() {
		return dificultad_id;
	}

	public void setDificultad_id(Long dificultad_id) {
		this.dificultad_id = dificultad_id;
	}

	public Long getPropietario_id() {
		return propietario_id;
	}

	public void setPropietario_id(Long propietario_id) {
		this.propietario_id = propietario_id;
	}

	public BigDecimal getDistancia() {
		return distancia;
	}

	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}

	public Date getTiempoEstimado() {
		return tiempoEstimado;
	}

	public void setTiempoEstimado(Date tiempoEstimado) {
		this.tiempoEstimado = tiempoEstimado;
	}

	public Date getFechaRealizacion() {
		return fechaRealizacion;
	}

	public void setFechaRealizacion(Date fechaRealizacion) {
		this.fechaRealizacion = fechaRealizacion;
	}

	public Set<Foto> getFotos() {
		return fotos;
	}

	public void setFotos(Set<Foto> fotos) {
		this.fotos = fotos;
	}

	public List<Punto> getRecorrido() {
		return recorrido;
	}

	public void setRecorrido(List<Punto> recorrido) {
		this.recorrido = recorrido;
	}

	public Ruta getRuta() {
		return ruta;
	}

	public void setRuta(Ruta ruta) {
		this.ruta = ruta;
	}

	public List<UploadFileBean> getUploadFiles() {
		return this.uploadFiles;
	}

	public void setUploadFiles(List<UploadFileBean> uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

	public boolean isUploading() {
		return uploading;
	}

	public void setUploading(boolean uploading) {
		this.uploading = uploading;
	}

	public BigDecimal getPuntajePromedio() {
		return puntajePromedio;
	}

	public void setPuntajePromedio(BigDecimal puntajePromedio) {
		this.puntajePromedio = puntajePromedio;
	}

	public Long getCantidadVisitas() {
		return cantidadVisitas;
	}

	public void setCantidadVisitas(Long cantidadVisitas) {
		this.cantidadVisitas = cantidadVisitas;
	}

	public String getRecorridoJSON() {
		return recorridoJSON;
	}

	public void setRecorridoJSON(String recorridoJSON) {
		this.recorridoJSON = recorridoJSON;
	}

	public OutcomeBean getOutcome() {
		return outcome;
	}

	public void setOutcome(OutcomeBean outcome) {
		this.outcome = outcome;
	}

}
