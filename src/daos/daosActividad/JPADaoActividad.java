package daos.daosActividad;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.apache.commons.collections.CollectionUtils;

import model.Ruta;
import utils.EMF;
import model.Actividad;

public class JPADaoActividad implements IDaoActividad {

	public boolean alta(Actividad dato) throws Exception {
		boolean ok = true;
		EntityManager ent = EMF.getEntityManager();
		EntityTransaction etx = ent.getTransaction();
		etx.begin();
		try {
			ent.persist(dato);
			etx.commit();
		} catch (Exception e) {
			etx.rollback();
			e.printStackTrace();
			ok = false;
			throw e;
		} finally {
			ent.close();
		}
		return ok;
	}

	public void baja(Actividad dato) {
		EntityManager ent = EMF.getEntityManager();
		EntityTransaction etx = ent.getTransaction();
		etx.begin();
		try {
			ent.remove(ent.merge(dato));
			etx.commit();
		} catch (Exception e) {
			System.out.println("ocurrio un error al borrar");
			e.printStackTrace();
		} finally {
			ent.close();
		}
	}

	public boolean modificacion(Actividad dato) throws Exception {
		EntityManager ent = EMF.getEntityManager();
		EntityTransaction etx = ent.getTransaction();
		boolean ok = true;
		etx.begin();
		try {
			ent.merge(dato);
			etx.commit();
		} catch (Exception e) {
			etx.rollback();
			ok = false;
			e.printStackTrace();
			throw e;
		} finally {
			ent.close();
		}
		return ok;

	}

	public List<Actividad> listar() {
		EntityManager ent = EMF.getEntityManager();
		return (List<Actividad>) (ent.createQuery("from model.Actividad")).getResultList();
	}

	public List<Actividad> listarHabilitadas() {
		EntityManager ent = EMF.getEntityManager();
		return (List<Actividad>) (ent.createQuery("SELECT a FROM Actividad a WHERE a.habilitado = true"))
				.getResultList();
	}

	public Actividad buscar(String nombre) {
		EntityManager ent = EMF.getEntityManager();
		Actividad actividad = null;
		try {
			Query q = ent.createQuery("SELECT a FROM Actividad a WHERE a.nombre = :nombre");
			q.setParameter("nombre", nombre);
			actividad = (Actividad) q.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ent.close();
		}
		return actividad;
	}

	public Actividad buscarPorId(Long id) {
		EntityManager ent = EMF.getEntityManager();
		Actividad dato = null;
		try {
			dato = (Actividad) ent.find(Class.forName("model.Actividad"), id);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ent.close();
		}
		return dato;
	}

	public void borrarPorId(Long id) throws Exception {
		EntityManager ent = EMF.getEntityManager();
		EntityTransaction etx = ent.getTransaction();
		etx.begin();
		try {
			Actividad dato = (Actividad) ent.find(Class.forName("model.Actividad"), id);
			ent.remove(dato);
			etx.commit();
		} catch (Exception e) {
			e.printStackTrace();
			etx.rollback();
			throw e;
		} finally {
			ent.close();
		}

	}

	public boolean tieneRutasAsociadas(Long id) {
		EntityManager ent = EMF.getEntityManager();
		List<Ruta> rutas = (List<Ruta>) (ent.createQuery("select r from Ruta r where r.actividad.id = " + id))
				.getResultList();
		return !CollectionUtils.isEmpty(rutas);
	}
}
