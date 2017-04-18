package es.aromano.users.model;

import org.springframework.security.core.GrantedAuthority;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserRole implements GrantedAuthority, Serializable {


    private String role;


    @Override
    public String getAuthority() {
        return this.role;
    }
}
