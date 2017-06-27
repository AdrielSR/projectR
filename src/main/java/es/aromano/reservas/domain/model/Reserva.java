package es.aromano.reservas.domain.model;

import java.util.Objects;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import es.aromano.espacios.domain.model.Espacio;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;
import es.aromano.users.domain.model.User;
import org.joda.time.DateTime;

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

	@Embedded
	@AttributeOverrides({
		@AttributeOverride(name = "inicio", column = @Column(name = "rr_inicio")),
		@AttributeOverride(name = "fin", column = @Column(name = "rr_fin"))
	})
	private RangoDateTime rangoRecurrencia;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="espacio_id")
	private Espacio espacio;
	
	@NotNull
	@ManyToOne
	@JoinColumn(name="user_id")
	private User user;

	@Embedded
	private ReglasRecurrencia reglas;
	
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


	public ReglasRecurrencia getReglas() {
		return reglas;
	}

	public void setReglas(ReglasRecurrencia reglas) {
		this.reglas = reglas;
	}

	public RangoDateTime getRangoRecurrencia() {
		return rangoRecurrencia;
	}

	public void setRangoRecurrencia(RangoDateTime rangoRecurrencia) {
		this.rangoRecurrencia = rangoRecurrencia;
	}


	public DateTime getInicio(){
		return rango.getInicio();
	}

	public DateTime getFin(){
		return rango.getFin();
	}

}
