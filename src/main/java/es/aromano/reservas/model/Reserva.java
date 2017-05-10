package es.aromano.reservas.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import es.aromano.espacios.model.Espacio;
import es.aromano.users.domain.model.User;

@Entity
@Table(name="reserva")
public class Reserva {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="reserva_id")
	private Long id;
	
	@Column(name="asunto", nullable=false)
	private String asunto;
	
	@Embedded
	private RangoDateTime rango;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="espacio_id")
	private Espacio espacio;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;
	
	
	public Reserva(){}

	public Reserva(String asunto, RangoDateTime rango, Espacio espacio, User user){
		this.asunto = Objects.requireNonNull(asunto);
		this.rango = Objects.requireNonNull(rango);
		this.espacio = Objects.requireNonNull(espacio);
		this.user = Objects.requireNonNull(user);
	}

	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getAsunto() {
		return asunto;
	}


	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}


	public RangoDateTime getRango() {
		return rango;
	}


	public void setRango(RangoDateTime rango) {
		this.rango = rango;
	}


	public Espacio getEspacio() {
		return espacio;
	}


	public void setEspacio(Espacio espacio) {
		this.espacio = espacio;
	}


	public User getUser() {
		return user;
	}


	public void setUser(User user) {
		this.user = user;
	}
	
	
	
}
