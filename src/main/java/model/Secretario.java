package model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
public class Secretario extends Persona {

	private String sector;
	@OneToOne
	private Usuario usuario;

	public Secretario() {
		super();
	}

	public Secretario(int id_persona, String dni, String nombre, String apellido, String telefono, String direccion,
			Date fecha_nac, String sector, Usuario usuario) {
		super(id_persona, dni, nombre, apellido, telefono, direccion, fecha_nac);
		this.sector = sector;
		this.usuario = usuario;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}
