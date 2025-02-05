package persistence;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Odontologo;
import model.Turno;

class OdontologoJPAController {
	private EntityManagerFactory emf;

	public OdontologoJPAController() {
		emf = Persistence.createEntityManagerFactory("javaweb_demo_PU");
	}

	public void create(Odontologo odontologo) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(odontologo);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			ex.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void update(Odontologo odontologo) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(odontologo);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			ex.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public void delete(int id) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			Odontologo odontologo = em.find(Odontologo.class, id);
			if (odontologo != null) {
				em.remove(em.contains(odontologo) ? odontologo : em.merge(odontologo));
			}
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			ex.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}
	}

	public Odontologo findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Odontologo.class, id);
		} finally {
			em.close();
		}
	}

	public List<Odontologo> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT o FROM Odontologo o", Odontologo.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Odontologo> findByEspecialidad(String especialidad) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT o FROM Odontologo o WHERE o.especialidad = :especialidad", Odontologo.class)
					.setParameter("especialidad", especialidad).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Odontologo> findByName(String name) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT o FROM Odontologo o WHERE o.nombre = :nombre", Odontologo.class)
					.setParameter("nombre", name).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Turno> getTurnosByOdontologo(String id_odonto, LocalDate fecha) {
		EntityManager em = emf.createEntityManager();
		try {
			return em
					.createQuery("SELECT t FROM Turno t WHERE t.odontoRel.id_persona = :id_odonto "
							+ "AND t.fechaTurno = :fecha", Turno.class)
					.setParameter("id_odonto", Integer.parseInt(id_odonto)).setParameter("fecha", fecha)
					.getResultList();
		} finally {
			em.close();
		}
	}

}