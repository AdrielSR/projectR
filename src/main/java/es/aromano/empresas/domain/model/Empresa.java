package es.aromano.empresas.domain.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import es.aromano.edificios.domain.model.Edificio;
import es.aromano.users.domain.model.User;

@Entity
@Table(name="empresa")
public class Empresa {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "empresa_id")
	private int id;
	
	@Column(name="nombre", nullable=false)
	private String nombre;
	
	@Column(name="cif", nullable=false)
	private String cif;
	
	@OneToMany(mappedBy="empresa")
	private Set<User> usuarios;
	
	@OneToMany(mappedBy="empresa")
	private Set<Edificio> edificios;
	
	public Empresa(){
		this.usuarios = new HashSet<>();
		this.edificios = new HashSet<>();
	}
	
	public Empresa(String nombre, String cif){
		this.nombre = nombre;
		this.cif = cif;
		this.usuarios = new HashSet<>();
		this.edificios = new HashSet<>();
	}

	public void addUser(User user){
		usuarios.add(user);
	}
	
	public void addEdificio(Edificio edificio){
		edificios.add(edificio);
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

	public String getCif() {
		return cif;
	}

	public void setCif(String cif) {
		this.cif = cif;
	}

	public Set<User> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(Set<User> usuarios) {
		this.usuarios = usuarios;
	}
	
	
	
}
