package daos.daosActividad;

import java.util.List;

import model.Actividad;


public interface IDaoActividad {
	public boolean alta(Actividad dato);
	public void baja(Actividad dato);
	public boolean modificacion(Actividad dato);
	public List<Actividad> listar();
	public Actividad buscar(String actividad);
	public Actividad buscarPorId(Long id);
	public void borrarPorId(Long id);
	public boolean tieneRutasAsociadas(Long id);
}
