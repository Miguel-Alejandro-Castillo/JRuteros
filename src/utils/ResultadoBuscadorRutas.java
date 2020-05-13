package utils;

import java.math.BigDecimal;

public class ResultadoBuscadorRutas {
    private Long id;
    private Long propietario_id;
    private String nombre;
    private BigDecimal distancia;
    private String dificultad;
    private BigDecimal puntajePromedio;
    private Integer cantidadVisitas;
    
    
	public ResultadoBuscadorRutas() {
		super();
	}
	
	public ResultadoBuscadorRutas(Long id, Long propietario_id, String nombre, BigDecimal distancia, String dificultad,
			BigDecimal puntajePromedio, Integer cantidadVisitas) {
		this();
		this.id = id;
		this.propietario_id = propietario_id;
		this.nombre = nombre;
		this.distancia = distancia;
		this.dificultad = dificultad;
		this.puntajePromedio = puntajePromedio;
		this.cantidadVisitas = cantidadVisitas;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getPropietario_id() {
		return propietario_id;
	}
	public void setPropietario_id(Long propietario_id) {
		this.propietario_id = propietario_id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public BigDecimal getDistancia() {
		return distancia;
	}
	public void setDistancia(BigDecimal distancia) {
		this.distancia = distancia;
	}
	public String getDificultad() {
		return dificultad;
	}
	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}
	public BigDecimal getPuntajePromedio() {
		return puntajePromedio;
	}
	public void setPuntajePromedio(BigDecimal puntajePromedio) {
		this.puntajePromedio = puntajePromedio;
	}
	public Integer getCantidadVisitas() {
		return cantidadVisitas;
	}
	public void setCantidadVisitas(Integer cantidadVisitas) {
		this.cantidadVisitas = cantidadVisitas;
	}
    
}
