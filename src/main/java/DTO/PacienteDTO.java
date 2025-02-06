package DTO;

import java.time.LocalDate;
import java.util.List;

import model.Paciente;
import model.Responsable;
import model.Turno;

public class PacienteDTO {

	private String id;
	private String nombre;
	private String dni;
	private String apellido;
	private String telefono;
	private String direccion;
	private String fechanac;
	private boolean tiene_os;
	private String tipo_sangre;
	private Responsable responsable;
	private List<Turno> turnos;

	public PacienteDTO() {
		super();
	}

	public PacienteDTO(Paciente pac) {
		this.tiene_os = pac.isTiene_os();
		this.tipo_sangre = pac.getTipo_sangre();
		this.responsable = pac.getResponsable();
		this.turnos = pac.getTurnos();
		this.id = String.valueOf(pac.getId());
		this.nombre = pac.getNombre();
		this.dni = pac.getDni();
		this.apellido = pac.getApellido();
		this.telefono = pac.getTelefono();
		this.direccion = pac.getDireccion();
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

	public boolean isTiene_os() {
		return tiene_os;
	}

	public void setTiene_os(boolean tiene_os) {
		this.tiene_os = tiene_os;
	}

	public String getTipo_sangre() {
		return tipo_sangre;
	}

	public void setTipo_sangre(String tipo_sangre) {
		this.tipo_sangre = tipo_sangre;
	}

	public Responsable getResponsable() {
		return responsable;
	}

	public void setResponsable(Responsable responsable) {
		this.responsable = responsable;
	}

	public List<Turno> getTurnos() {
		return turnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.turnos = turnos;
	}

	public String getFechanac() {
		return fechanac;
	}

	public void setFechanac(String fechanac) {
		this.fechanac = fechanac;
	}

	public Paciente getPaciente() {
		Paciente pac = new Paciente();
		pac.setNombre(this.nombre);
		pac.setApellido(this.apellido);
		pac.setDni(this.dni);
		pac.setTelefono(this.telefono);
		pac.setDireccion(this.direccion);
		pac.setFecha_nac(LocalDate.parse(fechanac));
		pac.setTipo_sangre(this.tipo_sangre);
		// pac.setResponsable(this.responsable);
		// pac.setTurnos(this.turnos);
		pac.setTiene_os(this.tiene_os);
		return pac;
	}

}
