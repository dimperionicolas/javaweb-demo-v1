package persistence;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import model.Odontologo;
import model.Paciente;
import model.Turno;

class TurnoJPAController {
	private EntityManagerFactory emf;

	public TurnoJPAController() {
		emf = Persistence.createEntityManagerFactory("javaweb_demo_PU");
	}

	public void create(Turno turno) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(turno);
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

	public void update(Turno turno) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(turno);
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
			Turno turno = em.find(Turno.class, id);
			if (turno != null) {
				em.remove(em.contains(turno) ? turno : em.merge(turno));
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

	public Turno findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Turno.class, id);
		} finally {
			em.close();
		}
	}

	public List<Turno> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT t FROM Turno t", Turno.class).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Turno> findByFecha(LocalDate fecha) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT t FROM Turno t WHERE t.fechaTurno = :fecha", Turno.class)
					.setParameter("fecha", fecha).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Turno> findByMotivoConsulta(String motivo) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT t FROM Turno t WHERE t.motivoConsulta LIKE :motivo", Turno.class)
					.setParameter("motivo", "%" + motivo + "%").getResultList();
		} finally {
			em.close();
		}
	}

	public List<Turno> findByOdontologoAndFecha(Odontologo odonto, LocalDate fecha) {
		EntityManager em = emf.createEntityManager();
		try {
			return em
					.createQuery("SELECT t FROM Turno t WHERE t.odontoRel = :odonto "
							+ "AND t.fechaTurno = :fecha", Turno.class)
					.setParameter("odonto", odonto).setParameter("fecha", fecha).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Turno> findByPaciente(Paciente paci) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT t FROM Turno t WHERE t.pacieRel = :paci", Turno.class)
					.setParameter("paci", paci).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Turno> findByOdonto(Odontologo odonto) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT t FROM Turno t WHERE t.odontoRel = :odonto", Turno.class)
					.setParameter("odonto", odonto).getResultList();
		} finally {
			em.close();
		}
	}

	public List<Turno> getTurnosByOdontologoAndDate(Odontologo odonto, LocalDate fecha) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Turno> query = em.createQuery(
					"SELECT t FROM Turno t WHERE t.odontoRel = :odonto " + "AND t.fechaTurno = :fecha", Turno.class);
			TypedQuery<Turno> setParameter = query.setParameter("odonto", odonto).setParameter("fecha", fecha);
			return setParameter.getResultList();
		} finally {
			em.close();
		}
	}

	public boolean isTurnoAvailable(LocalDate fecha, String hora, Odontologo odonto) {
		EntityManager em = emf.createEntityManager();
		try {
			List<Turno> turnos = em
					.createQuery("SELECT t FROM Turno t WHERE t.odontoRel= :odonto "
							+ "AND t.fechaTurno = :fecha AND t.horaTurno = :hora", Turno.class)
					.setParameter("odonto", odonto).setParameter("fecha", fecha).setParameter("hora", hora)
					.getResultList();
			return turnos.isEmpty();
		} finally {
			em.close();
		}
	}

}