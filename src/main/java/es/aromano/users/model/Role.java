package es.aromano.users.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role implements GrantedAuthority, Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="role_id")
    private int id;
	
    @Column(name="role", length=20, nullable=false)
    private String role;

    public Role(){ }

    public Role(String role){
        this.role = role;
    }
    
    @Override
    public String getAuthority() {
        return this.role;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
    
    

}
