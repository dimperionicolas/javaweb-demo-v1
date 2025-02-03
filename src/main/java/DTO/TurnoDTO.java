package DTO;

import java.time.format.DateTimeFormatter;

import model.Odontologo;
import model.Paciente;
import model.Turno;

public class TurnoDTO {

	private int id_turno;
	private String fechaTurno;
	private String horaTurno;
	private String estado = "disponible";
	private Odontologo odonto;
	private Paciente paciente;
	private String motivo;

	public TurnoDTO() {
		super();
	}

	public TurnoDTO(Turno turno) {
		super();
		this.id_turno = turno.getId_turno();

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		this.fechaTurno = turno.getFechaTurno().format(formatter);

		this.horaTurno = turno.getHoraTurno();
		this.odonto = turno.getOdontoRel();
		this.paciente = turno.getPacieRel();
		this.motivo = turno.getMotivoConsulta();
		if (turno.getPacieRel() != null) {
			this.estado = "ocupado";
		}
	}

	public TurnoDTO(String estado, String hora, String fecha) {
		this.estado = estado;
		this.horaTurno = hora;
		this.fechaTurno = fecha;
	}

	public int getId_turno() {
		return id_turno;
	}

	public void setId_turno(int id_turno) {
		this.id_turno = id_turno;
	}

	public String getFechaTurno() {
		return fechaTurno;
	}

	public void setFechaTurno(String fechaTurno) {
		this.fechaTurno = fechaTurno;
	}

	public String getHoraTurno() {
		return horaTurno;
	}

	public void setHoraTurno(String horaTurno) {
		this.horaTurno = horaTurno;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Odontologo getOdonto() {
		return odonto;
	}

	public void setOdonto(Odontologo odonto) {
		this.odonto = odonto;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public String getMotivo() {
		return motivo;
	}

	public void setMotivo(String motivo) {
		this.motivo = motivo;
	}

}
