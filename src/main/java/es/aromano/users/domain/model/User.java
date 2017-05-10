package es.aromano.users.domain.model;


import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import es.aromano.empresas.model.Empresa;

@Entity
@Table(name = "user")
public class User implements UserDetails{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int id;

    @Column(name = "userName", nullable=false)
    private String username;

    @Column(name = "password", nullable=false)
    private String password;

    @Column(name = "email", nullable=false)
    private String email;

    @Column(name = "accountExpired", nullable=false)
    private boolean accountExpired;

    @Column(name = "accountLocked", nullable=false)
    private boolean accountLocked;

    @Column(name = "credentialsExpired", nullable=false)
    private boolean credentialsExpired;

    @Column(name = "enabled", nullable=false)
    private boolean enabled;


    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles;
    
    @NotNull
    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;
    

	public User(){
        roles = new HashSet<>();
    }

    public User(String username, String email){
        this.username = username;
        this.email = email;
        this.enabled = true;
        this.roles = new HashSet<>();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return !accountExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !accountLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public void setAccountExpired(boolean accountExpired) {
        this.accountExpired = accountExpired;
    }

    public void setAccountLocked(boolean accountLocked) {
        this.accountLocked = accountLocked;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public int getId() {
		return id;
	}
    
    public void setId(int id) {
        this.id = id;
    }
    
    public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

    public Set<Role> getRoles() {
        return this.roles;
    }

    public void setRoles(Set<Role> roles){
    	this.roles = roles;
    }

    public void addRole(Role role){
    	this.roles.add(role);
    }

    public void activar(){
        setEnabled(true);
    }

    public void desactivar(){
        setEnabled(false);
    }

}
