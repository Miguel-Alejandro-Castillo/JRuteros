package daos;
import java.util.*;
public interface IDaoGenerico<T> {
	
	public void alta(T dato);
	public void baja(T dato);
	public void modificacion(T dato);
	public List<T> listar();
	public T buscarPorId(Long id);
	public void borrarPorId(Long id);

}
