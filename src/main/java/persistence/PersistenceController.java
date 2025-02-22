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

	// Usuarios
	public void createUser(Usuario user) {
		userJPA.create(user);
	}

	public Usuario findUserById(int id_editar) {
		return userJPA.findById(id_editar);
	}

	public Usuario findUserByUsername(String username) {
		return userJPA.findByNombreUsuario(username);
	}

	public List<UsuarioDTO> findAllUsers() {
		List<UsuarioDTO> userList = new ArrayList<>();
		for (Usuario usuario : userJPA.findAll()) {
			userList.add(new UsuarioDTO(usuario));
		}
		return userList;
	}

	public void updateUser(Usuario userToEdit) {
		userJPA.update(userToEdit);
	}

	public void deleteUser(int id_eliminar) {
		userJPA.delete(id_eliminar);
	}

	public boolean validateUser(String nombreUsuario, String contrasenia) {
		return userJPA.validateUserAndPass(nombreUsuario, contrasenia);
	}

	//
	//
	//
	//
	//
	// Odontologo
	public void createOdontologo(Odontologo odontologo) {
		odonJPA.create(odontologo);
	}

	public Odontologo findOdontoById(int id_editar) {
		return odonJPA.findById(id_editar);
	}

	public List<Odontologo> findOdontoByName(String name) {
		return odonJPA.findByName(name);
	}

	public List<Odontologo> findOdontoByEspecialidad(String especialidad) {
		return odonJPA.findByEspecialidad(especialidad);
	}

	public List<Odontologo> findAllOdonto() {
		return odonJPA.findAll();
	}

	public void updateOdonto(Odontologo odontoToEdit) {
		odonJPA.update(odontoToEdit);
	}

	public void deleteOdonto(int id_eliminar) {
		odonJPA.delete(id_eliminar);
	}

	//
	//
	//
	//
	//
	// Pacientes
	public void createPaciente(Paciente paciente) {
		paciJPA.create(paciente);
	}

	public Paciente findPacienteById(int pacienteId) {
		return paciJPA.findById(pacienteId);
	}

	public List<Paciente> findPacienteByName(String name) {
		return paciJPA.findByName(name);
	}

	public List<Paciente> findAllPacientes() {
		return paciJPA.findAll();
	}

	public void updatePaciente(Paciente paciente) {
		paciJPA.update(paciente);
	}

	public void deletePaciente(int pacienteId) {
		paciJPA.delete(pacienteId);
	}

	//
	//
	//
	//
	//
	// Turnos
	public void createTurno(Turno turno) throws Exception {
		if (!turnJPA.isTurnoAvailable(turno.getFechaTurno(), turno.getHoraTurno(), turno.getOdontoRel())) {
			throw new Exception("El turno ya está ocupado para ese horario y odontólogo");
		}
		turnJPA.create(turno);
	}

	public Turno findTurnoById(int turnoId) {
		return turnJPA.findById(turnoId);
	}

	public List<Turno> findTurnosByPacienteId(Paciente pac) {
		return turnJPA.findByPaciente(pac);
	}

	public List<Turno> findTurnosByOdontoId(Odontologo odonto) {
		return turnJPA.findByOdonto(odonto);
	}

	public List<Turno> getTurnosOdontologo(Odontologo odonto, LocalDate fecha) {
		return turnJPA.getTurnosByOdontologoAndDate(odonto, fecha);
	}

	public void deleteTurno(int turnoId) throws Exception {
		Turno turno = turnJPA.findById(turnoId);
		Odontologo odontologo = turno.getOdontoRel();
		Paciente paciente = turno.getPacieRel();
		turnJPA.delete(turnoId);

		// Eliminar referencias bidireccionales
		// TODO v2 verificar si JPA se encarga de esto o no.
		if (odontologo != null) {
			odontologo.getTurnos().remove(turno);
		}
		if (paciente != null) {
			paciente.getTurnos().remove(turno);
		}
	}

	public boolean verificarDisponibilidadTurno(LocalDate fecha, String hora, Odontologo odonto) {
		return turnJPA.isTurnoAvailable(fecha, hora, odonto);
	}

}
