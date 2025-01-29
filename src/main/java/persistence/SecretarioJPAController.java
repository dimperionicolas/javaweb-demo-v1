package persistence;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Secretario;

class SecretarioJPAController {
	private EntityManagerFactory emf;

	public SecretarioJPAController() {
		emf = Persistence.createEntityManagerFactory("javaweb_demo_PU");
	}

	public void create(Secretario secretario) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(secretario);
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

	public void update(Secretario secretario) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(secretario);
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
			Secretario secretario = em.find(Secretario.class, id);
			if (secretario != null) {
				em.remove(em.contains(secretario) ? secretario : em.merge(secretario));
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

	public Secretario findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Secretario.class, id);
		} finally {
			em.close();
		}
	}

	public List<Secretario> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT s FROM Secretario s", Secretario.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Secretario> findBySector(String sector) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT s FROM Secretario s WHERE s.sector = :sector", Secretario.class)
					.setParameter("sector", sector).getResultList();
		} finally {
			em.close();
		}
	}
}