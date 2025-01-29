package persistence;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Horario;

class HorarioJPAController {
	private EntityManagerFactory emf;

	public HorarioJPAController() {
		emf = Persistence.createEntityManagerFactory("javaweb_demo_PU");
	}

	public void create(Horario horario) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(horario);
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

	public void update(Horario horario) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(horario);
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
			Horario horario = em.find(Horario.class, id);
			if (horario != null) {
				em.remove(em.contains(horario) ? horario : em.merge(horario));
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

	public Horario findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Horario.class, id);
		} finally {
			em.close();
		}
	}

	public List<Horario> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT h FROM Horario h", Horario.class).getResultList();
		} finally {
			em.close();
		}
	}
}