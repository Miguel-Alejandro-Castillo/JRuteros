package daos;
import java.util.*;
public interface IDaoGenerico<T> {
	
	public void alta(T dato) throws Exception;
	public void baja(T dato) throws Exception;
	public void modificar(T dato) throws Exception;
	public List<T> listar();
	public T buscarPorId(Long id);
	public void borrarPorId(Long id);

}
