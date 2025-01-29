package persistence;

import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import model.Persona;

class PersonaJPAController {
	private EntityManagerFactory emf;

	public PersonaJPAController() {
		emf = Persistence.createEntityManagerFactory("javaweb_demo_PU");
	}

	public void create(Persona persona) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(persona);
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

	public void update(Persona persona) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(persona);
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
			Persona persona = em.find(Persona.class, id);
			if (persona != null) {
				em.remove(em.contains(persona) ? persona : em.merge(persona));
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

	public Persona findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Persona.class, id);
		} finally {
			em.close();
		}
	}

	public List<Persona> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Persona> findByApellido(String apellido) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT p FROM Persona p WHERE p.apellido = :apellido", Persona.class)
					.setParameter("apellido", apellido).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Persona> findByDni(String dni) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT p FROM Persona p WHERE p.dni = :dni", Persona.class).setParameter("dni", dni)
					.getResultList();
		} finally {
			em.close();
		}
	}

	public List<Persona> findByFechaNacimiento(Date fechaNacimiento) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT p FROM Persona p WHERE p.fecha_nac = :fechaNacimiento", Persona.class)
					.setParameter("fechaNacimiento", fechaNacimiento).getResultList();
		} finally {
			em.close();
		}
	}
}