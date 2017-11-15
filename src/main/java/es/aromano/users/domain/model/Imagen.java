package es.aromano.users.domain.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.persistence.*;

@Entity
@Table(name = "imagen")
public class Imagen {

    @Id
    @Column(insertable = false)
    private int id;

    @Column(nullable = false, insertable = false, updatable = false)
    private String enlace;

    protected Imagen() {
    }

    public int getId() {
        return id;
    }

    public String getEnlace() {
        return enlace;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Imagen imagen = (Imagen) o;

        return id == imagen.id;
    }

    @Override
    public int hashCode() {
        return id;
    }


    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .append("id", id)
                .append("enlace", enlace)
                .toString();
    }
}
