package persistence;

import java.util.ArrayList;
import java.util.List;

import DTO.UsuarioDTO;
import model.Odontologo;
import model.Turno;
import model.Usuario;

public class PersistenceController {
	HorarioJPAController horaJPA = new HorarioJPAController();
//	PacienteJPAController paciJPA = new PacienteJPAController();
//	PersonaJPAController persJPA = new PersonaJPAController();
//	ResponsableJPAController respJPA = new ResponsableJPAController();
//	SecretarioJPAController secrJPA = new SecretarioJPAController();
//	TurnoJPAController turnJPA = new TurnoJPAController();
	OdontologoJPAController odonJPA = new OdontologoJPAController();
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

	// Odontologo
	public List<Odontologo> getAllOdonto() {
		return odonJPA.findAll(); // TODO devolver una lista refinada?
	}

	public void createOdontologo(Odontologo odontologo) {
//		try {
//			Horario horario = odontologo.getHorario();
//			horaJPA.create(horario);
//		} catch (Exception e) {
//			System.out.println(e.getMessage());
//		}
		odonJPA.create(odontologo);
	}

	public void deleteOdonto(int id_eliminar) {
		// TODO delete horario where id_horario = odontologo.horario.id
		odonJPA.delete(id_eliminar); // TODO se eliminaron sus horas?
	}

	public Odontologo findOdontoById(int id_editar) {
		return odonJPA.findById(id_editar);
	}

	public void updateOdonto(Odontologo odontoToEdit) {
		odonJPA.update(odontoToEdit);
	}

	public List<Odontologo> findOdontoByEspecialidad(String especialidad) {
		return odonJPA.findByEspecialidad(especialidad);
	}

	public List<Odontologo> findOdontoByName(String name) {
		return odonJPA.findByName(name);
	}

	public List<Turno> getTurnosOdontologo(String id_odonto, String fecha) {
		return odonJPA.getTurnosByOdontologo(id_odonto, fecha);

	}

	public List<Turno> getTurnosPaciente(int pacienteId) {
		return null;
	}

}
