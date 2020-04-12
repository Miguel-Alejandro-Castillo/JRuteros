package daos.daosUser;

import java.util.List;
import model.User;
public interface IDaoUser {
	public boolean alta(User dato);
	public void baja(User dato);
	public boolean modificacion(User dato);
	public List<User> listar();
	public User buscarPorId(Long id);
	public void borrarPorId(Long id);
	public User buscar(String username ,String password);
	public User buscar(String username);
}
