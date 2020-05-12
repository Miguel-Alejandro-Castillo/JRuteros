package utils;

public class OrdenamientosBuscadorRutas {

	/*
	 * 
	 * Distancia, dificultad, puntuación promedio de los usuarios, cantidad de
	 * personas que hicieron la ruta
	 * 
	 */

	private String distancia;

	private String dificultad;

	private String puntuacionPromedio;

	private String cantidadVisitas;

	public OrdenamientosBuscadorRutas() {
		super();
	}
	
	

	public OrdenamientosBuscadorRutas(String distancia, String dificultad, String puntuacionPromedio,
			String cantidadVisitas) {
		this();
		this.distancia = distancia;
		this.dificultad = dificultad;
		this.puntuacionPromedio = puntuacionPromedio;
		this.cantidadVisitas = cantidadVisitas;
	}



	public String getDistancia() {
		return distancia;
	}

	public void setDistancia(String distancia) {
		this.distancia = distancia;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public String getPuntuacionPromedio() {
		return puntuacionPromedio;
	}

	public void setPuntuacionPromedio(String puntuacionPromedio) {
		this.puntuacionPromedio = puntuacionPromedio;
	}

	public String getCantidadVisitas() {
		return cantidadVisitas;
	}

	public void setCantidadVisitas(String cantidadVisitas) {
		this.cantidadVisitas = cantidadVisitas;
	}

}
