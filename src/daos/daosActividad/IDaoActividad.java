package daos.daosActividad;

import java.util.List;

import model.Actividad;


public interface IDaoActividad {
	public boolean alta(Actividad dato) throws Exception;
	public void baja(Actividad dato);
	public boolean modificacion(Actividad dato) throws Exception;
	public List<Actividad> listar();
	public List<Actividad> listarHabilitadas();
	public Actividad buscar(String actividad);
	public Actividad buscarPorId(Long id);
	public void borrarPorId(Long id) throws Exception;
	public boolean tieneRutasAsociadas(Long id);
}
