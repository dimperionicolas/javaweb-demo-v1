package persistence;

import java.util.ArrayList;
import java.util.List;

import DTO.UsuarioDTO;
import model.Usuario;

public class PersistenceController {
//	HorarioJPAController horaJPA = new HorarioJPAController();
//	OdontologoJPAController odonJPA = new OdontologoJPAController();
//	PacienteJPAController paciJPA = new PacienteJPAController();
//	PersonaJPAController persJPA = new PersonaJPAController();
//	ResponsableJPAController respJPA = new ResponsableJPAController();
//	SecretarioJPAController secrJPA = new SecretarioJPAController();
//	TurnoJPAController turnJPA = new TurnoJPAController();
	UsuarioJPAController userJPA = new UsuarioJPAController();

	public PersistenceController() {
		super();
	}

	public void createUser(Usuario user) {
		userJPA.create(user);
	}

	public List<UsuarioDTO> getAllUsers() {
		List<UsuarioDTO> userList = new ArrayList<>();
		for (Usuario usuario : userJPA.findAll()) {
			userList.add(new UsuarioDTO(usuario));
		}
		return userList;
	}

	public void deleteUser(int id_eliminar) {
		userJPA.delete(id_eliminar);
	}

	public Usuario findUserById(int id_editar) {
		return userJPA.findById(id_editar); // TODO usuarioDTO?
	}

	public void updateUser(Usuario userToEdit) {
		userJPA.update(userToEdit);
	}

	public Usuario findUserByUsername(String username) {
		return userJPA.findByNombreUsuario(username);
	}

	public boolean validateUser(String nombreUsuario, String contrasenia) {
		return userJPA.validateUserAndPass(nombreUsuario, contrasenia);
	}

}
