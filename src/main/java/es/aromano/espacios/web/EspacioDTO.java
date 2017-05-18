package es.aromano.espacios.web;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EspacioDTO {

	private int id;

	@NotNull
	@Size(max = 30, message = "El nombre es demasiado largo. (Maximo 30 caractres)")
	private String nombre;

	@NotNull
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
