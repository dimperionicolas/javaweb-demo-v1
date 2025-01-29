package logic;

import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import DTO.UsuarioDTO;
import model.Usuario;
import persistence.PersistenceController;

public class Controller {

	PersistenceController persistenceController = new PersistenceController();
	LoginController loginController = new LoginController();

	public Controller() {
	}

	// Usuarios
	public void createUser(String nombreUsuario, String rol, String contrasenia) {
		if (nombreUsuario == null || nombreUsuario.trim().isEmpty()) {
			throw new IllegalArgumentException("El nombre de usuario no puede estar vacío");
		}
		if (!rolValido(rol)) {
			throw new IllegalArgumentException("Rol no válido");
		}

		Usuario usuario = new Usuario();
		usuario.setNombre_usuario(nombreUsuario);
		usuario.setRol(rol);
		usuario.setContrasenia(BCrypt.hashpw(contrasenia, BCrypt.gensalt()));
		persistenceController.createUser(usuario);
	}

	public List<UsuarioDTO> getAllUsers() {
		return persistenceController.getAllUsers();
	}

	public void deleteUser(int id_eliminar) {
		persistenceController.deleteUser(id_eliminar);
	}

	public Usuario findUserById(int id_editar) {
		return persistenceController.findUserById(id_editar);
		// TODO cambiar return por UsuarioDTO
	}

	public void updateUser(Usuario userToEdit) {
		persistenceController.updateUser(userToEdit);

	}

	public UsuarioDTO validateLogin(String username, String password) {
		return loginController.validarCredenciales(username, password);
	}

	private boolean rolValido(String rol) {
		return rol.equals("admin") || rol.equals("user");
	}

}
