package utils;

import java.math.BigDecimal;

public class FiltrosBuscadorRutas {
	
	private Long usuarioId;
	private BigDecimal latitud;
	private BigDecimal longitud; 
	private BigDecimal radioDistancia;
	private Long actividadId;
	private Long formatoId;
	private BigDecimal distancia;
	private Long dificultadId;
	
	
	public FiltrosBuscadorRutas() {
		super();
	}
	
	public FiltrosBuscadorRutas(BigDecimal latitud, BigDecimal longitud, BigDecimal radioDistancia, Long actividadId,
			Long formatoId, BigDecimal distancia, Long dificultadId) {
		this();
		this.latitud = latitud;
		this.longitud = longitud;
		this.radioDistancia = radioDistancia;
		this.actividadId = actividadId;
		this.formatoId = formatoId;
		this.distancia = distancia;
		this.dificultadId = dificultadId;
	}
	
	public BigDecimal getLatitud() {
		return latitud;
	}
	public void setLatitud(BigDecimal latitud) {
		this.latitud = latitud;
	}
	public BigDecimal getLongitud() {
		return longitud;
	}
	public void setLongitud(BigDecimal longitud) {
		this.longitud = longitud;
	}
	public BigDecimal getRadioDistancia() {
		return radioDistancia;
	}
	public void setRadioDistancia(BigDecimal radioDistancia) {
		this.radioDistancia = radioDistancia;
	}
	public Long getActividadId() {
		return actividadId;
	}
	public void setActividadId(Long actividadId) {
		this.actividadId = actividadId;
	}
	public Long getFormatoId() {
		return formatoId;
	}
	public void setFormatoId(Long formatoId) {
		this.formatoId = formatoId;
	}
	public BigDecimal getDistancia() {
		return distancia;
	}
	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}
	public Long getDificultadId() {
		return dificultadId;
	}
	public void setDificultadId(Long dificultad) {
		this.dificultadId = dificultad;
	}

	public Long getUsuarioId() {
		return usuarioId;
	}

	public void setUsuarioId(Long usuarioId) {
		this.usuarioId = usuarioId;
	}

}
