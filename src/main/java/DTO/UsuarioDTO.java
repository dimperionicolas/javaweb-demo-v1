package DTO;

import model.Usuario;

public class UsuarioDTO {
	private String id;
	private String nombre;
	private String rol;

	public UsuarioDTO(Usuario usuario) {
		this.id = String.valueOf(usuario.getId_usuario());
		this.nombre = usuario.getNombre_usuario();
		this.rol = usuario.getRol();
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
}