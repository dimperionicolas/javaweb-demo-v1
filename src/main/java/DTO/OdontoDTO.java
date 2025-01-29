package DTO;

import model.Usuario;

public class OdontoDTO {
	private int id;
	private String nombre;
	private String rol;

	public OdontoDTO(Usuario usuario) {
		this.id = usuario.getId_usuario();
		this.nombre = usuario.getNombre_usuario();
		this.rol = usuario.getRol();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
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
}