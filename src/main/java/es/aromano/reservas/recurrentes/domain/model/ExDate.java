package es.aromano.reservas.recurrentes.domain.model;

import es.aromano.reservas.domain.model.RangoDateTime;
import javax.persistence.*;

@Embeddable
public class ExDate {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="inicio", column = @Column(name="exdate_inicio", nullable = false)),
            @AttributeOverride(name="fin", column = @Column(name="exdate_fin", nullable = false))
    })
    private RangoDateTime rangoExdate;

    protected ExDate(){}

    public ExDate(RangoDateTime rangoExdate) {
        this.rangoExdate = rangoExdate;
    }

    public RangoDateTime getRangoExdate() {
        return rangoExdate;
    }

    public void setRangoExdate(RangoDateTime rangoExdate) {
        this.rangoExdate = rangoExdate;
    }


    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        ExDate otro = (ExDate) obj;
        return rangoExdate.equals(otro.getRangoExdate());
    }
}
