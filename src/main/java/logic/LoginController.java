package logic;

import DTO.UsuarioDTO;
import model.Usuario;
import persistence.PersistenceController;

public class LoginController {

	PersistenceController persistenceController = new PersistenceController();

	public UsuarioDTO validarCredenciales(String nombreUsuario, String contrasenia) {
		Usuario usuario = persistenceController.findUserByUsername(nombreUsuario);
		if (usuario == null)
			return null;
//		if (BCrypt.checkpw(contrasenia, usuario.getContrasenia())) {
		return new UsuarioDTO(usuario);
//		}
	}

}
