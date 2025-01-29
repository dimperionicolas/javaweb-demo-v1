package model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_usuario;
	private String nombre_usuario;
	private String contrasenia;
	private String rol;

	public Usuario() {
		super();
	}

	public Usuario(int id_usuario, String nombre_usuario, String contrasenia, String rol) {
		super();
		this.id_usuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
		this.contrasenia = contrasenia;
		this.rol = rol;
	}

	public Usuario(Integer id_usuario, String nombre_usuario, String rol) {
		super();
		this.id_usuario = id_usuario;
		this.nombre_usuario = nombre_usuario;
		this.rol = rol;
	}

	public Usuario(Integer id_usuario) {
		super();
		this.id_usuario = id_usuario;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public String getNombre_usuario() {
		return nombre_usuario;
	}

	public void setNombre_usuario(String nombre_usuario) {
		this.nombre_usuario = nombre_usuario;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getRol() {
		return rol;
	}

	public void setRol(String rol) {
		this.rol = rol;
	}

}
