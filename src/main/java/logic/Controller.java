package logic;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.mindrot.jbcrypt.BCrypt;

import DTO.OdontoDTO;
import DTO.TurnoDTO;
import DTO.UsuarioDTO;
import model.Horario;
import model.Odontologo;
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
		String hashpw = BCrypt.hashpw(contrasenia, BCrypt.gensalt());
		usuario.setContrasenia(hashpw);
		persistenceController.createUser(usuario);
	}

	public List<UsuarioDTO> getAllUsers() {
		return persistenceController.getAllUsers();
	}

	public void deleteUser(String id_eliminar) {
		int id = Integer.parseInt(id_eliminar);
		persistenceController.deleteUser(id);
	}

	public Usuario findUserById(String id_editar) {
		int id = Integer.parseInt(id_editar);
		return persistenceController.findUserById(id);
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

	//
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
		// TODO quedo persistido el horario?
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

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate fecha = LocalDate.parse(fechanac, formatter);

		odontologo.setFecha_nac(fecha);
		Horario horario = new Horario();
		horario.setHorario_inicio(hora_inicio);
		horario.setHorario_fin(hora_fin);
		odontologo.setHorario(horario);
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

	public List<OdontoDTO> getAllOdonto() {
		List<OdontoDTO> allOdonto = new ArrayList<>();
		for (Odontologo odontologo : persistenceController.getAllOdonto()) {
			allOdonto.add(new OdontoDTO(odontologo));
		}
		return allOdonto;
	}

	public void deleteOdonto(String id_eliminar) {
		int id = Integer.parseInt(id_eliminar);
		persistenceController.deleteOdonto(id);
	}

	public OdontoDTO findOdontoById(String id_editar) {
		int id = Integer.parseInt(id_editar);
		return new OdontoDTO(persistenceController.findOdontoById(id));
	}

	public void updateOdonto(String id_editar, String dni, String nombre, String apellido, String telefono,
			String direccion, String especialidad, String fechanac, String hora_inicio, String hora_fin) {
		Odontologo odontologo = makeOdontologo(dni, nombre, apellido, telefono, direccion, especialidad, fechanac,
				hora_inicio, hora_fin);
		int id = Integer.parseInt(id_editar);
		odontologo.setId(id);
		persistenceController.updateOdonto(odontologo);
	}

	public List<TurnoDTO> obtenerTurnosPorMedico(String odontoId, String fecha) {
		List<TurnoDTO> listaFinalTurnos = new ArrayList<>();
		List<Turno> turnosOdonto = getTurnosOdontologo(odontoId);
		turnosOdonto = new ArrayList<>();
		for (int i = 8; i < 21; i++) {
			listaFinalTurnos.add(new TurnoDTO("fueradehorario", i + " ", fecha));
		}
		for (TurnoDTO turnoDTO : listaFinalTurnos) {
			for (Turno turnoOdonto : turnosOdonto) {
				if (turnoDTO.getHoraTurno() == turnoOdonto.getHoraTurno()) {
					turnoDTO = new TurnoDTO(turnoOdonto);
				}
			}

		}
		return listaFinalTurnos;
	}

	private List<Turno> getTurnosOdontologo(String odontoId) {
		// TODO lista de turnos disponibles y ocupados
		return List.of();
	}

	public List<Turno> obtenerTurnosPorPaciente() {
		// TODO debe devolver una lista de turnos del paciente
		return null;
	}

	//
	//
	//
	//
	//
	// Paciente

}
