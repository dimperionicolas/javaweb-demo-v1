package DTO;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import model.Horario;
import model.Odontologo;
import model.Turno;

public class OdontoDTO {
	private String id;
	private String nombre;
	private String rol;
	private String dni;
	private String apellido;
	private String telefono;
	private String direccion;
	private String fecha_nac;
	private String especialidad;
	private List<Turno> turnos;
	private UsuarioDTO usuario;
	private Horario horario;

	public OdontoDTO() {
		super();
	}

	public OdontoDTO(Odontologo odontologo) {
		this.id = odontologo.getId() + "";
		this.dni = odontologo.getDni();
		this.nombre = odontologo.getNombre();
		this.apellido = odontologo.getApellido();
		this.telefono = odontologo.getTelefono();
		this.direccion = odontologo.getDireccion();

		LocalDate fechanac = odontologo.getFecha_nac();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.fecha_nac = fechanac.format(formatter);

		this.fecha_nac = this.especialidad = odontologo.getEspecialidad();
		this.turnos = odontologo.getTurnos();
		if (odontologo.getUsuario() != null) {

			this.usuario = new UsuarioDTO(odontologo.getUsuario());
		}
		this.horario = odontologo.getHorario();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getFecha_nac() {
		return fecha_nac;
	}

	public void setFecha_nac(String fecha_nac) {
		this.fecha_nac = fecha_nac;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public UsuarioDTO getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioDTO usuario) {
		this.usuario = usuario;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

}