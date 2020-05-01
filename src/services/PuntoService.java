package services;

import java.util.ArrayList;
import java.util.List;
import model.Punto;

public class PuntoService {

	private List<Punto> puntos;

	private static PuntoService instance = null;

	private PuntoService() {
		super();
		init();
	}

	private void init() {
		this.puntos = new ArrayList<Punto>();
	}

	public synchronized static PuntoService getInstance() {
		if (instance == null) {
			instance = new PuntoService();
		}
		return instance;
	}

	public List<Punto> getPuntos() {
		return this.puntos;
	}

	public void agregarPunto(Punto punto) {
		this.getPuntos().add(punto);
	}

	public void borrarPuntos() {
		this.init();
	}

	public void borrarPunto(Long id) {
		this.puntos.removeIf(punto -> punto.getId().equals(id));
	}

}
