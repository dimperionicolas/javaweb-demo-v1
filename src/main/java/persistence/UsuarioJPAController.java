package persistence;

import java.util.List;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import model.Usuario;

class UsuarioJPAController {
	private EntityManagerFactory emf;

	public UsuarioJPAController() {
		emf = Persistence.createEntityManagerFactory("javaweb_demo_PU");
	}

	@Transactional
	public void create(Usuario usuario) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.persist(usuario);
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

	public void update(Usuario usuario) {
		EntityManager em = null;
		try {
			em = emf.createEntityManager();
			em.getTransaction().begin();
			em.merge(usuario);
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
			Usuario usuario = em.find(Usuario.class, id);
			if (usuario != null) {
				em.remove(em.contains(usuario) ? usuario : em.merge(usuario));
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

	public Usuario findById(int id) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.find(Usuario.class, id);
		} finally {
			em.close();
		}
	}

	public List<Usuario> findAll() {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT u FROM Usuario u", Usuario.class).getResultList();
		} finally {
			em.close();
		}
	}

	public Usuario findByNombreUsuario(String nombreUsuario) {
		EntityManager em = emf.createEntityManager();
		try {
			List<Usuario> usuarios = em
					.createQuery("SELECT u FROM Usuario u WHERE u.nombre_usuario = :nombreUsuario", Usuario.class)
					.setParameter("nombreUsuario", nombreUsuario).getResultList();
			return usuarios.isEmpty() ? null : usuarios.get(0);
		} finally {
			em.close();
		}
	}

	public List<Usuario> findByRol(String rol) {
		EntityManager em = emf.createEntityManager();
		try {
			return em.createQuery("SELECT u.nombre_usuario, u.rol, u.id_usuario FROM Usuario u WHERE u.rol = :rol",
					Usuario.class).setParameter("rol", rol).getResultList();
		} finally {
			em.close();
		}
	}

	public boolean validateUserAndPass(String nombreUsuario, String contrasenia) {
		EntityManager em = emf.createEntityManager();
		try {
			TypedQuery<Long> query = em.createQuery(
					"SELECT COUNT(u) FROM Usuario u WHERE u.nombre_usuario = :nombre AND u.contrasenia = :pass",
					Long.class);
			query.setParameter("nombre", nombreUsuario);
			query.setParameter("pass", contrasenia);
			return query.getSingleResult() > 0;
		} finally {
			em.close();
		}
	}
//
//	public boolean validateUserAndPass(int id_usuario, String contrasenia) {
//		EntityManager em = emf.createEntityManager();
//		try {
//			List<Usuario> usuarios = em
//					.createQuery("SELECT u.id_usuario  FROM Usuario u WHERE u.id_usuario = :id_usuario "
//							+ "AND u.contrasenia = :contrasenia", Usuario.class)
//					.setParameter("id_usuario", id_usuario).setParameter("contrasenia", contrasenia).getResultList();
//			return usuarios.isEmpty() ? false : true;
//		} finally {
//			em.close();
//		}

}