package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.List;

import DTO.OdontoDTO;
import DTO.PacienteDTO;
import DTO.TurnoDTO;
import DTO.UsuarioDTO;
import model.Horario;
import model.Odontologo;
import model.Paciente;
import model.Responsable;
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
		return persistenceController.findAllUsers();
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
		for (Odontologo odontologo : persistenceController.findAllOdonto()) {
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
		odontologo.setFecha_nac(LocalDate.parse(fechanac));
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
	public void createPaciente(String dni, String nombre, String apellido, String telefono, String direccion,
			LocalDate fecha_nac, boolean tiene_os, String tipo_sangre, Responsable responsable) {
		Paciente paciente = new Paciente();
		paciente.setNombre(nombre);
		paciente.setApellido(apellido);
		paciente.setDireccion(direccion);
		paciente.setDni(dni);
		paciente.setTelefono(telefono);
		paciente.setFecha_nac(fecha_nac);
		paciente.setResponsable(responsable);
		paciente.setTiene_os(tiene_os);
		paciente.setTipo_sangre(tipo_sangre);
		persistenceController.createPaciente(paciente);
	}

	public Paciente getPacienteByID(int pacienteId) {
		return persistenceController.findPacienteById(pacienteId);
	}

	public List<Paciente> getAllPaciente() {
		return persistenceController.findAllPacientes();
	}

	public void updatePaciente(Paciente paciente) {
		persistenceController.updatePaciente(paciente);
	}

	public void deletePaciente(int pacienteId) {
		persistenceController.deletePaciente(pacienteId);

	}

	// Turno -> Odontologo, Paciente
	// Create

	public void agendarTurno(OdontoDTO odont, TurnoDTO turnoDTO, PacienteDTO paci) throws Exception {
		Odontologo odontologo;
		Paciente paciente;
		try {
			odontologo = persistenceController.findOdontoById(Integer.parseInt(odont.getId()));
			if (paci.getId() != null) {
				paciente = persistenceController.findPacienteById(Integer.parseInt(paci.getId()));
				// El paciente ya existe
			} else {
				paciente = paci.getPaciente();
				persistenceController.createPaciente(paciente);
			}
		} catch (Exception e) {
			System.err.println(e.getMessage());
			return;
		}

		if (odontologo == null) {
			throw new Exception("Odontólogo no encontrado");
		}
		if (paciente == null) {
			throw new Exception("Paciente no encontrado");
		}

		Turno turno = turnoDTO.getTurno();
		turno.setOdontoRel(odontologo);
		turno.setPacieRel(paciente);
		persistenceController.createTurno(turno);
	}

	// getTurnos
	public List<Turno> getTurnoByOdontoId(int odontoId) {
		Odontologo odonto = persistenceController.findOdontoById(odontoId);
		return persistenceController.findTurnosByOdontoId(odonto);
	}

	public List<Turno> getTurnosByPacienteId(int pacienteId) {
		Paciente paci = persistenceController.findPacienteById(pacienteId);
		return persistenceController.findTurnosByPacienteId(paci);
	}

	// getTurnoByIdOdontologo
	public List<TurnoDTO> getTurnoByOdontoIdAndDate(String odontoId, String fecha) {
		List<TurnoDTO> listaFinalTurnos = new ArrayList<>();
		int intId = Integer.parseInt(odontoId);
		Odontologo odonto = persistenceController.findOdontoById(intId);
		int horaIni = Integer.parseInt(odonto.getHorario().getHorario_inicio());
		int horaFin = Integer.parseInt(odonto.getHorario().getHorario_fin());
		List<Turno> turnosOdonto = persistenceController.getTurnosOdontologo(odonto, LocalDate.parse(fecha));

		for (int i = 8; i < 22; i++) {
			String estado = "bloqueado";
			if (i >= horaIni && i < horaFin) {
				estado = "disponible";
				for (Turno turnoOdonto : turnosOdonto) {
					if (turnoOdonto.getHoraTurno().equals(String.valueOf(i))) {
						estado = "ocupado";
					}
				}
			}
			listaFinalTurnos.add(new TurnoDTO(estado, String.valueOf(i), fecha));
		}
		return listaFinalTurnos;
	}

	public boolean validateDisponibilidadTurno(String fecha, String horario, String estado, String turnoId,
			String odontoId) {
		LocalDate localDate = LocalDate.parse(fecha);
		int odontoIdInt = Integer.parseInt(odontoId);
		Odontologo odonto = persistenceController.findOdontoById(odontoIdInt);
		return persistenceController.verificarDisponibilidadTurno(localDate, horario, odonto);
	}

	public SimpleEntry<OdontoDTO, TurnoDTO> prepareOdontologoAndTurno(String fecha, String horario, String estado,
			String turnoId, String odontoId) {
		int odontoIdInt = Integer.parseInt(odontoId);
		OdontoDTO odonto = new OdontoDTO(persistenceController.findOdontoById(odontoIdInt));
		TurnoDTO turno = new TurnoDTO();
		turno.setEstado("disponible");
		turno.setFechaTurno(fecha);
		turno.setHoraTurno(horario);
		turno.setOdonto(odonto);
		return new SimpleEntry<>(odonto, turno);
	}

	// deleteTurno
	public void deleteTurno(int i) throws Exception {
		persistenceController.deleteTurno(i);
		// TODO v2 verificar que los turnos sean eliminados en paciente y odonto
	}

	// Statics TODO Utils V2
	public static String getDateToStringDate(LocalDate localDate) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		return localDate.format(formatter);
	}

}
