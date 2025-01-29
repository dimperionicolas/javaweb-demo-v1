package model;

import java.util.Date;

import jakarta.persistence.Entity;

@Entity
public class Responsable extends Persona {
	private String tipo_respo;

	public Responsable() {
		super();
	}

	public Responsable(int id_persona, String dni, String nombre, String apellido, String telefono, String direccion,
			Date fecha_nac, String tipo_respo) {
		super(id_persona, dni, nombre, apellido, telefono, direccion, fecha_nac);
		this.tipo_respo = tipo_respo;
	}

	public String getTipo_respo() {
		return tipo_respo;
	}

	public void setTipo_respo(String tipo_respo) {
		this.tipo_respo = tipo_respo;
	}

}
