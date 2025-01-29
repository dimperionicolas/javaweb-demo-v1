package persistence;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Responsable;

class ResponsableJPAController {
	private EntityManagerFactory emf;

	public ResponsableJPAController() {
		emf = Persistence.createEntityManagerFactory("javaweb_demo_PU");
	}

	public void create(Responsable responsable) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(responsable);
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

	public void update(Responsable responsable) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(responsable);
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
			Responsable responsable = em.find(Responsable.class, id);
			if (responsable != null) {
				em.remove(em.contains(responsable) ? responsable : em.merge(responsable));
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

	public Responsable findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Responsable.class, id);
		} finally {
			em.close();
		}
	}

	public List<Responsable> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT r FROM Responsable r", Responsable.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Responsable> findByTipoResponsable(String tipoRespo) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT r FROM Responsable r WHERE r.tipo_respo = :tipoRespo", Responsable.class)
					.setParameter("tipoRespo", tipoRespo).getResultList();
		} finally {
			em.close();
		}
	}
}