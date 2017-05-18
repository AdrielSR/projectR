package es.aromano.espacios.web;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.*;

public class EspacioDTO {

	private int id;

	@NotBlank(message = "Este campo no puede ser vacio")
	@Size(max = 30, message = "El nombre es demasiado largo. (Maximo 30 caractres)")
	private String nombre;


	@Min(value = 1, message = "El aforo no puede ser inferior a 1.")
	private int aforo;

	private int idEdificio;
	
	public EspacioDTO(){}

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

	public int getIdEdificio() {
		return idEdificio;
	}

	public void setIdEdificio(int idEdificio) {
		this.idEdificio = idEdificio;
	}

	
	
}
