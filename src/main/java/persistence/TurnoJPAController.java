package persistence;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
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

	public List<Turno> findByFecha(Date fecha) {
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

	public List<Turno> findByOdontologoAndFecha(String id_odonto, LocalDate fecha) {
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

	public List<Turno> findByPaciente(int pacienteId) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT t FROM Turno t WHERE t.pacieRel.id_persona = :pacienteId", Turno.class)
					.setParameter("pacienteId", pacienteId).getResultList();
		} finally {
			em.close();
		}
	}

	public boolean isTurnoAvailable(LocalDate fecha, String hora, int odontologoId) {
		EntityManager em = emf.createEntityManager();
		try {
			List<Turno> turnos = em
					.createQuery("SELECT t FROM Turno t WHERE t.odontoRel.id_persona = :odontologoId "
							+ "AND t.fechaTurno = :fecha AND t.horaTurno = :hora", Turno.class)
					.setParameter("odontologoId", odontologoId).setParameter("fecha", fecha).setParameter("hora", hora)
					.getResultList();
			return turnos.isEmpty();
		} finally {
			em.close();
		}
	}

	public void cancelTurno(int turnoId) throws Exception {
		EntityManager em = emf.createEntityManager();
		try {
			em.getTransaction().begin();
			Turno turno = em.find(Turno.class, turnoId);
			if (turno == null) {
				throw new Exception("Turno no encontrado");
			}

			// Eliminar referencias bidireccionales
			Odontologo odontologo = turno.getOdontoRel();
			Paciente paciente = turno.getPacieRel();

			if (odontologo != null) {
				odontologo.getTurnos().remove(turno);
			}
			if (paciente != null) {
				paciente.getTurnos().remove(turno);
			}

			em.remove(turno);
			em.getTransaction().commit();
		} catch (Exception ex) {
			if (em != null && em.getTransaction().isActive()) {
				em.getTransaction().rollback();
			}
			throw ex;
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

}