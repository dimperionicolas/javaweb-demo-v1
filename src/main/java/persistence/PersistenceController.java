package persistence;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import DTO.UsuarioDTO;
import model.Odontologo;
import model.Paciente;
import model.Turno;
import model.Usuario;

public class PersistenceController {
	HorarioJPAController horaJPA = new HorarioJPAController();
	TurnoJPAController turnJPA = new TurnoJPAController();
	OdontologoJPAController odonJPA = new OdontologoJPAController();
	UsuarioJPAController userJPA = new UsuarioJPAController();
	PacienteJPAController paciJPA = new PacienteJPAController();
	// PersonaJPAController persJPA = new PersonaJPAController();
	// ResponsableJPAController respJPA = new ResponsableJPAController();
	// SecretarioJPAController secrJPA = new SecretarioJPAController();

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
		return userJPA.findById(id_editar);
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
		return odonJPA.findAll();
	}

	public void createOdontologo(Odontologo odontologo) {
		odonJPA.create(odontologo);
	}

	public void deleteOdonto(int id_eliminar) {
		odonJPA.delete(id_eliminar);
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

	public List<Turno> getTurnosPaciente(int pacienteId) {
		return turnJPA.findByPaciente(pacienteId);
		// TODO ver
	}

	public void cancelarTurno(int turnoId) throws Exception {
		turnJPA.cancelTurno(turnoId);
	}

	public boolean verificarDisponibilidadTurno(LocalDate fecha, String hora, int odontologoId) {
		return turnJPA.isTurnoAvailable(fecha, hora, odontologoId);
	}

	public Paciente findPacienteById(int pacienteId) {
		return paciJPA.findById(pacienteId);
	}

	public void createTurno(Turno turno) throws Exception {
		if (!turnJPA.isTurnoAvailable(turno.getFechaTurno(), turno.getHoraTurno(), turno.getOdontoRel().getId())) {
			throw new Exception("El turno ya está ocupado para ese horario y odontólogo");
		}
		turnJPA.create(turno);
	}

	public List<Turno> getTurnosByOdontoId(int odontoId) {
		// TODO turnos del odontologo
		return null;

	}

	public List<Turno> getTurnosOdontologo(String id_odonto, String fecha) {
		return odonJPA.getTurnosByOdontologo(id_odonto, LocalDate.parse(fecha));
	}

}
