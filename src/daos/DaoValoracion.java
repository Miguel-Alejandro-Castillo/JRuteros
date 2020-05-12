package daos;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import model.Ruta;
import model.Valoracion;
import utils.EMF;

public class DaoValoracion extends JpaDaoGenerico<Valoracion>{
	
	public DaoValoracion(){
	    this.setModel("Valoracion");
	}
	
	public void borrarPorRutaId(Long rutaId) throws Exception{
		EntityManager ent = EMF.getEntityManager();
	    EntityTransaction etx=ent.getTransaction();
		etx.begin();
		try {
			Query q = ent.createQuery("DELETE FROM Valoracion v WHERE v.ruta.id = :rutaId");
			q.setParameter("rutaId", rutaId);
			q.executeUpdate();
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			etx.rollback();
			throw e;
		} finally {
			ent.close();
		}
	}
	
	public Valoracion buscarPorUsuarioIdYRutaId(Long usuarioId, Long rutaId){
		EntityManager ent = EMF.getEntityManager();
		Valoracion resultado = null;
		try {
			Query q = ent.createQuery("SELECT v FROM Valoracion v WHERE v.usuario.id = :usuarioId AND v.ruta.id = :rutaId");
			q.setParameter("usuarioId", usuarioId);
			q.setParameter("rutaId", rutaId);
			resultado = (Valoracion) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ent.close();
		}
		return resultado;
	}
	
	
	public Map<String, Object> getPromedioPuntajeYCantidadVisitas(Long rutaId){
		  EntityManager ent=EMF.getEntityManager();
		  Map<String, Object> resultado = null;
		  try {
			List<Object[]> result = null;
			
			String SELECT = "SELECT AVG(coalesce(v.puntaje, 0)) puntajePromedio, SUM( CASE WHEN v.hizoLaRuta = TRUE THEN 1 ELSE 0 END) cantidadVisitas ";	
			String FROM = "FROM RUTAS r LEFT JOIN VALORACIONES v ON r.id = :rutaId AND r.id = v.ruta_id ";
			Query q = ent.createNativeQuery( SELECT + FROM);
			q.setParameter("rutaId", rutaId);
			result = q.getResultList();
			resultado = new HashMap<String, Object>();
			resultado.put("puntajePromedio", new BigDecimal(result.get(0)[0].toString()));
			resultado.put("cantidadVisitas", Long.valueOf(result.get(0)[1].toString()));
		  }catch ( Exception e) {
			     e.printStackTrace();
		  }	
		  finally{
			  ent.close();
		  } 
		  return resultado;
		}
}
