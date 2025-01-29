package model;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Paciente extends Persona {

	private boolean tiene_os;
	private String tipo_sangre;
	@OneToOne
	private Responsable responsable;
	@OneToMany(mappedBy = "pacieRel", cascade = CascadeType.ALL)
	private List<Turno> turnos;

	public Paciente() {
		super();
	}

	public Paciente(int id_persona, String dni, String nombre, String apellido, String telefono, String direccion,
			Date fecha_nac, boolean tiene_os, String tipo_sangre, Responsable responsable, List<Turno> turnos) {
		super(id_persona, dni, nombre, apellido, telefono, direccion, fecha_nac);
		this.tiene_os = tiene_os;
		this.tipo_sangre = tipo_sangre;
		this.responsable = responsable;
		this.turnos = turnos;
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

}
