package es.aromano.espacios.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import es.aromano.edificios.model.Edificio;

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
	
	@ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
	
	public Espacio(){ }
	
	public Espacio(String nombre, int aforo){
		this.nombre = nombre;
		this.aforo = aforo;
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
	

}
