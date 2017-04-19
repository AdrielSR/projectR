package es.aromano.users.model;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRole implements GrantedAuthority, Serializable {

    @Basic
    @Column(length = 20)
    private String role;

    public UserRole(){ }

    public UserRole(String role){
        this.role = role;
    }

    @Override
    public String getAuthority() {
        return this.role;
    }

}
