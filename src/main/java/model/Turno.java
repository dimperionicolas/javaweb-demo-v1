package model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Turno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_turno;
	private LocalDate fechaTurno;

	private String horaTurno;
	private String motivoConsulta;
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_turno_odonto")
	private Odontologo odontoRel;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "id_turno_pacie")
	private Paciente pacieRel;

	public Turno() {
		super();
	}

	public Turno(int id_turno, LocalDate fechaTurno, String horaTurno, String motivoConsulta) {
		super();
		this.id_turno = id_turno;
		this.fechaTurno = fechaTurno;
		this.horaTurno = horaTurno;
		this.motivoConsulta = motivoConsulta;
	}

	public int getId_turno() {
		return id_turno;
	}

	public void setId_turno(int id_turno) {
		this.id_turno = id_turno;
	}

	public LocalDate getFechaTurno() {
		return fechaTurno;
	}

	public void setFechaTurno(LocalDate fechaTurno) {
		this.fechaTurno = fechaTurno;
	}

	public String getHoraTurno() {
		return horaTurno;
	}

	public void setHoraTurno(String horaTurno) {
		this.horaTurno = horaTurno;
	}

	public String getMotivoConsulta() {
		return motivoConsulta;
	}

	public void setMotivoConsulta(String motivoConsulta) {
		this.motivoConsulta = motivoConsulta;
	}

	public Odontologo getOdontoRel() {
		return odontoRel;
	}

	public void setOdontoRel(Odontologo odontoRel) {
		this.odontoRel = odontoRel;
	}

	public Paciente getPacieRel() {
		return pacieRel;
	}

	public void setPacieRel(Paciente pacieRel) {
		this.pacieRel = pacieRel;
	}

}
