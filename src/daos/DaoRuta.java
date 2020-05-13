package daos;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.*;

import org.apache.commons.lang.StringUtils;

import model.Ruta;
import model.User;
import utils.EMF;
import utils.FiltrosBuscadorRutas;
import utils.OrdenamientosBuscadorRutas;
import utils.ResultadoBuscadorRutas;
public class DaoRuta extends JpaDaoGenerico<Ruta>{

	public DaoRuta(){
	    this.setModel("Ruta");
	}
	public List<Ruta> buscarPorPropietarioId(Long propietarioId){
		EntityManager ent = EMF.getEntityManager();
		List<Ruta> resultado = new ArrayList<Ruta>();
		try {
			Query q = ent.createQuery("SELECT r FROM Ruta r WHERE r.propietario.id = :propietarioId");
			q.setParameter("propietarioId", propietarioId);
			resultado = q.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ent.close();
		}
		return resultado;
	}
	
	public List<ResultadoBuscadorRutas> buscadorRutas(FiltrosBuscadorRutas filtros, OrdenamientosBuscadorRutas ordenamientos){
	  EntityManager ent=EMF.getEntityManager();
	  List<ResultadoBuscadorRutas> resultado = null;
	  try {
		List<Object[]> result = null;
		String LEFTJOIN = "LEFT JOIN VALORACIONES v ON v.ruta_id = r.id ";
		String INNERJOIN = "INNER JOIN DIFICULTADES d ON d.id = r.dificultad_id ";
		
		String SELECT = "SELECT r.id, r.propietario_id, r.nombre, r.distancia, d.nombre dificultad, AVG(coalesce(v.puntaje, 0)) puntajePromedio, SUM( CASE WHEN v.hizoLaRuta = TRUE THEN 1 ELSE 0 END) cantidadVisitas ";	
		String FROM = "FROM RUTAS r " + LEFTJOIN + INNERJOIN;
		
		String SUBQUERY1 = "SELECT * FROM PUNTOS p WHERE p.ruta_id = r.id AND GetDistance(:latitud, :longitud, p.latitud, p.longitud) < :radioDistancia";
		String WHERE = "WHERE (r.propietario_id <> :usuarioId) AND " 
					+ "		 ( (:latitud IS NULL OR :longitud IS NULL OR :radioDistancia IS NULL) OR EXISTS(" + SUBQUERY1 + ") ) AND " 
					+ "		 (:actividad_id IS NULL OR r.actividad_id = :actividad_id) AND "
					+ "		 (:formato_id IS NULL OR r.formato_id = :formato_id) AND "
					+ "		 (:distancia IS NULL OR r.distancia = :distancia) AND "
					+ "		 (:dificultad_id IS NULL OR r.dificultad_id = :dificultad_id) ";
		String GROUPBY = "GROUP BY r.id "; 
		
		String ORDER = "";
		/*
		String sortDistancia = ordenamientos.getDistancia();
		ORDER += StringUtils.isEmpty(sortDistancia) ? "" : "r.distancia " + sortDistancia + ",";
		String sortDificultad = ordenamientos.getDificultad();
		ORDER += StringUtils.isEmpty(sortDificultad) ? "" : "d.nombre " + sortDificultad + ",";
		String sortPuntuacionPromedio = ordenamientos.getPuntuacionPromedio();
		ORDER += StringUtils.isEmpty(sortPuntuacionPromedio) ? "" : "puntuacionPromedio " + sortPuntuacionPromedio + ",";
		String sortCantidadVisitas = ordenamientos.getCantidadVisitas();
		ORDER += StringUtils.isEmpty(sortCantidadVisitas) ? "" : "cantidadVisitas " + sortPuntuacionPromedio;
	
		ORDER = StringUtils.chompLast(ORDER, ",");
	    ORDER = ORDER.isEmpty() ? ORDER : "ORDER BY " + ORDER; 
	    */
				
		Query q = ent.createNativeQuery( SELECT + FROM + WHERE + GROUPBY + ORDER);
		q.setParameter("usuarioId", filtros.getUsuarioId());
		q.setParameter("latitud", filtros.getLatitud());
		q.setParameter("longitud", filtros.getLongitud());
		q.setParameter("radioDistancia", filtros.getRadioDistancia());
		q.setParameter("actividad_id", filtros.getActividadId());
		q.setParameter("formato_id", filtros.getFormatoId());
		q.setParameter("distancia", filtros.getDistancia());
		q.setParameter("dificultad_id", filtros.getDificultadId());
		result = q.getResultList();
		resultado = result.stream().map( r -> new ResultadoBuscadorRutas( Long.valueOf(r[0].toString()), Long.valueOf(r[1].toString()), r[2].toString(), new BigDecimal(r[3].toString()), r[4].toString(), new BigDecimal(r[5].toString()), Integer.valueOf(r[6].toString()) ) ).collect(Collectors.toList() );
	  }catch ( Exception e) {
		     e.printStackTrace();
	  }	
	  finally{
		  ent.close();
	  } 
	  return resultado;
	}
}
