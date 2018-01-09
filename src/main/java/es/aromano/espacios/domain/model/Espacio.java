package es.aromano.espacios.domain.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import es.aromano.edificios.domain.model.Edificio;
import es.aromano.reservas.domain.model.Reserva;

@Entity
@Table(name="espacio")
public class Espacio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "espacio_id")
	private int id;
	
	@Column(name = "nombre", nullable=false)
	private String nombre;
	
	@Column(name = "aforo", nullable=false)
	private int aforo;
	
	@NotNull
	@ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
	
	@Column(name="activo", nullable=false)
	private boolean activo;
	
	@OneToMany(mappedBy="espacio")
	private List<Reserva> reservas;

	@Embedded
	private Prestaciones prestaciones;

	public Espacio(){
		this.reservas = new ArrayList<>();
		this.prestaciones = new Prestaciones();
	}
	
	public Espacio(String nombre, int aforo){
		this.nombre = nombre;
		this.aforo = aforo;
		this.activo = true;
		this.reservas = new ArrayList<>();
		this.prestaciones = new Prestaciones();
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

	public int getAforo() {
		return aforo;
	}

	public void setAforo(int aforo) {
		this.aforo = aforo;
	}

	public Edificio getEdificio() {
		return edificio;
	}

	public void setEdificio(Edificio edificio) {
		this.edificio = edificio;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void activar() {
		this.activo = true;	
	}
	
	public void desactivar() {
		this.activo = false;	
	}

	public List<Reserva> getReservas() {
		return reservas;
	}

	public void setReservas(List<Reserva> reservas) {
		this.reservas = reservas;
	}

	public Prestaciones getPrestaciones() {
		return prestaciones;
	}

	public void setPrestaciones(Prestaciones prestaciones) {
		this.prestaciones = prestaciones;
	}
}
