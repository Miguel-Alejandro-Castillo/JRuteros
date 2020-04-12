package daos.daosUsuario;

import java.util.List;
import model.Usuario;
public interface IDaoUsuario {
	public boolean alta(Usuario dato);
	public void baja(Usuario dato);
	public boolean modificacion(Usuario dato);
	public List<Usuario> listar();
	public Usuario buscarPorId(Long id);
	public void borrarPorId(Long id);
	public Usuario buscar(String username ,String password);
	public Usuario buscar(String username);
}
