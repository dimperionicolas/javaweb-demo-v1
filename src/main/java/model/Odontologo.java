package model;

import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Odontologo extends Persona {
	private String especialidad;
	@OneToOne
	private Usuario usuario;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private Horario horario;
	@OneToMany(mappedBy = "odontoRel", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Turno> listaTurnos;

	public Odontologo() {
		super();
	}

	public Odontologo(int id_persona, String dni, String nombre, String apellido, String telefono, String direccion,
			LocalDate fecha_nac, String especialidad, List<Turno> turnos, Usuario usuario, Horario horario) {
		super(id_persona, dni, nombre, apellido, telefono, direccion, fecha_nac);
		this.especialidad = especialidad;
		this.listaTurnos = turnos;
		this.usuario = usuario;
		this.horario = horario;
	}

	public String getEspecialidad() {
		return especialidad;
	}

	public void setEspecialidad(String especialidad) {
		this.especialidad = especialidad;
	}

	public List<Turno> getTurnos() {
		return listaTurnos;
	}

	public void setTurnos(List<Turno> turnos) {
		this.listaTurnos = turnos;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Horario getHorario() {
		return horario;
	}

	public void setHorario(Horario horario) {
		this.horario = horario;
	}

}
