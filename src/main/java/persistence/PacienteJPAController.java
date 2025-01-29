package persistence;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Paciente;

class PacienteJPAController {
	private EntityManagerFactory emf;

	public PacienteJPAController() {
		emf = Persistence.createEntityManagerFactory("javaweb_demo_PU");
	}

	public void create(Paciente paciente) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(paciente);
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

	public void update(Paciente paciente) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(paciente);
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
			Paciente paciente = em.find(Paciente.class, id);
			if (paciente != null) {
				em.remove(em.contains(paciente) ? paciente : em.merge(paciente));
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

	public Paciente findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Paciente.class, id);
		} finally {
			em.close();
		}
	}

	public List<Paciente> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT p FROM Paciente p", Paciente.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Paciente> findByTipoSangre(String tipoSangre) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT p FROM Paciente p WHERE p.tipo_sangre = :tipoSangre", Paciente.class)
					.setParameter("tipoSangre", tipoSangre).getResultList();
		} finally {
			em.close();
		}
	}
}