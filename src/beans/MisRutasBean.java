package beans;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import model.Ruta;
import utils.Factory;

@ManagedBean(name = "MisRutasBean")
@RequestScoped
public class MisRutasBean {
	
	List<Ruta> rutas;

	public MisRutasBean() {
		super();
	}

	@PostConstruct
	public void init() {
		this.rutas = Factory.daoRuta().listar();
	}

	public List<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(List<Ruta> rutas) {
		this.rutas = rutas;
	}
	
}
