package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import DTO.OdontoDTO;
import DTO.TurnoDTO;
import DTO.UsuarioDTO;
import model.Horario;
import model.Odontologo;
import model.Paciente;
import model.Turno;
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
		// TODO v2 encriptar contraseña.
		usuario.setContrasenia(contrasenia);
		persistenceController.createUser(usuario);
	}

	public UsuarioDTO getUserById(String id_editar) {
		int id = Integer.parseInt(id_editar);
		Usuario userById = persistenceController.findUserById(id);
		return new UsuarioDTO(userById);
	}

	public List<UsuarioDTO> getAllUsers() {
		return persistenceController.getAllUsers();
	}

	public void updateUser(Usuario userToEdit) {
		persistenceController.updateUser(userToEdit);
	}

	public void deleteUser(String id_eliminar) {
		int id = Integer.parseInt(id_eliminar);
		persistenceController.deleteUser(id);
	}

	public UsuarioDTO validateLogin(String username, String password) {
		return loginController.validarCredenciales(username, password);
	}

	private boolean rolValido(String rol) {
		return rol.equals("admin") || rol.equals("user");
		// TODO v2 autenticacion y roles
	}

	//
	//
	//
	// Odontologos
	public void createOdontologo(String dni, String nombre, String apellido, String telefono, String direccion,
			String especialidad, String fechanac, String hora_inicio, String hora_fin) {
		isValidOdontologo(dni, nombre, apellido, telefono, direccion, especialidad, fechanac, hora_inicio, hora_fin);
		// TODO si no es valido deberia salir con el error
		Odontologo odontologo = makeOdontologo(dni, nombre, apellido, telefono, direccion, especialidad, fechanac,
				hora_inicio, hora_fin);
		persistenceController.createOdontologo(odontologo);
	}

	public OdontoDTO getOdontoById(String id_editar) {
		int id = Integer.parseInt(id_editar);
		return new OdontoDTO(persistenceController.findOdontoById(id));
	}

	public List<OdontoDTO> getAllOdonto() {
		List<OdontoDTO> allOdonto = new ArrayList<>();
		for (Odontologo odontologo : persistenceController.getAllOdonto()) {
			allOdonto.add(new OdontoDTO(odontologo));
		}
		return allOdonto;
	}

	public void updateOdonto(String id_editar, String dni, String nombre, String apellido, String telefono,
			String direccion, String especialidad, String fechanac, String hora_inicio, String hora_fin) {
		Odontologo odontologo = makeOdontologo(dni, nombre, apellido, telefono, direccion, especialidad, fechanac,
				hora_inicio, hora_fin);
		int id = Integer.parseInt(id_editar);
		odontologo.setId(id);
		persistenceController.updateOdonto(odontologo);
	}

	public void deleteOdonto(String id_eliminar) {
		int id = Integer.parseInt(id_eliminar);
		persistenceController.deleteOdonto(id);
	}

	private Odontologo makeOdontologo(String dni, String nombre, String apellido, String telefono, String direccion,
			String especialidad, String fechanac, String hora_inicio, String hora_fin) {
		Odontologo odontologo = new Odontologo();
		odontologo.setDni(dni);
		odontologo.setNombre(nombre);
		odontologo.setApellido(apellido);
		odontologo.setDireccion(direccion);
		odontologo.setEspecialidad(especialidad);
		odontologo.setTelefono(telefono);
		odontologo.setFecha_nac(getStringDateToDate(fechanac)); // Convierto fecha con metodo estatico
		odontologo.setHorario(new Horario(hora_inicio, hora_fin));
		return odontologo;
	}

	private void isValidOdontologo(String dni, String nombre, String apellido, String telefono, String direccion,
			String especialidad, String fechanac, String hora_inicio, String hora_fin) {
		if (dni == null || dni.trim().isEmpty() || nombre == null || nombre.trim().isEmpty() || apellido == null
				|| apellido.trim().isEmpty() || telefono == null || telefono.trim().isEmpty() || direccion == null
				|| direccion.trim().isEmpty() || especialidad == null || especialidad.trim().isEmpty()
				|| fechanac == null || fechanac.trim().isEmpty() || hora_inicio == null || hora_inicio.trim().isEmpty()
				|| hora_fin == null || hora_fin.trim().isEmpty()) {
			throw new IllegalArgumentException("Todos los campos son obligatorios");
		}
	}

	// Paciente

	public void createPaciente() {
	}

	public void getPacienteByID() {
	}

	public void getAllPaciente() {
	}

	public void updatePaciente() {
	}

	public void deletePaciente() {
	}

	// Turno -> Odontologo, Paciente
	// Create
	public void agendarTurno(LocalDate fecha, String hora, String motivo, int odontologoId, int pacienteId)
			throws Exception {
		Odontologo odontologo = persistenceController.findOdontoById(odontologoId);
		Paciente paciente = persistenceController.findPacienteById(pacienteId);

		if (odontologo == null) {
			throw new Exception("Odontólogo no encontrado");
		}
		if (paciente == null) {
			throw new Exception("Paciente no encontrado");
		}

		Turno turno = new Turno();
		turno.setFechaTurno(fecha);
		turno.setHoraTurno(hora);
		turno.setMotivoConsulta(motivo);
		turno.setOdontoRel(odontologo);
		turno.setPacieRel(paciente);

		persistenceController.createTurno(turno);
	}

	// getTurnos
	public List<Turno> getTurnoByOdontoId(int odontoId) {
		return persistenceController.getTurnosByOdontoId(odontoId);
	}

	public List<Turno> getTurnosByPacienteId(int pacienteId) {
		return persistenceController.getTurnosPaciente(pacienteId);
	}

	// getTurnoByIdOdontologo
	public List<TurnoDTO> getTurnoByOdontoIdAndDate(String odontoId, String fecha) {
		List<TurnoDTO> listaFinalTurnos = new ArrayList<>();
		List<Turno> turnosOdonto = persistenceController.getTurnosOdontologo(odontoId, fecha);
		for (int i = 8; i < 21; i++) {
			listaFinalTurnos.add(new TurnoDTO("Fuera de horario", String.valueOf(i), fecha));
		}
		for (TurnoDTO turnoDTO : listaFinalTurnos) {
			for (Turno turnoOdonto : turnosOdonto) {
				if (turnoDTO.getHoraTurno().equals(turnoOdonto.getHoraTurno())) {
					turnoDTO = new TurnoDTO(turnoOdonto);
				}
			}
		}
		return listaFinalTurnos;
	}

	public boolean validateDisponibilidadTurno(LocalDate fecha, String horario, int odontoId) {
		return persistenceController.verificarDisponibilidadTurno(fecha, horario, odontoId);
	}

	// deleteTurno
	public void deleteTurno(int i) throws Exception {
		persistenceController.cancelarTurno(i);
		// TODO verificar que los turnos sean eliminados en paciente y odonto
	}

	// Statics TODO Utils V2
	public static String getDateToStringDate(LocalDate localDate) {
		// TODO v2 DateUtils
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDate.format(formatter);
	}

	public static LocalDate getStringDateToDate(String date) {
		// TODO v2 DateUtils
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return LocalDate.parse(date, formatter);
	}

}
