package es.aromano.edificios.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import es.aromano.empresas.model.Empresa;
import es.aromano.espacios.model.Espacio;

@Entity
@Table(name="edificio")
public class Edificio {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "edificio_id")
    private int id;
	
	@Column(name = "nombre", nullable=false)
    private String nombre;
    
	@Column(name = "direccion", nullable=false)
    private String direccion;
    
	@NotNull
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    
    @OneToMany(mappedBy="edificio")
	private Set<Espacio> espacios;
    
    @Column(name = "activo", nullable=false)
    private boolean activo;


    public Edificio(){
    	this.espacios = new HashSet<>();
    }

    public Edificio(String nombre, String direccion){
        this.nombre = nombre;
        this.direccion = direccion;
        this.activo = true;
        this.espacios = new HashSet<>();
    }

    public void addEspacio(Espacio espacio){
    	this.espacios.add(espacio);
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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public Set<Espacio> getEspacios() {
		return espacios;
	}

	public void setEspacios(Set<Espacio> espacios) {
		this.espacios = espacios;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void activar(){
		this.activo = true;
	}
	
	public void desactivar(){
		this.activo = false;
	}
	
}
