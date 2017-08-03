package es.aromano.reservas.recurrentes.domain.model;

import es.aromano.reservas.domain.model.RangoDateTime;
import javax.persistence.*;

@Embeddable
public class RDate {

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="inicio", column = @Column(name="rdate_inicio", nullable = false)),
            @AttributeOverride(name="fin", column = @Column(name="rdate_fin", nullable = false))
    })
    private RangoDateTime rangoRdate;

    protected RDate(){}

    public RangoDateTime getRangoRdate() {
        return rangoRdate;
    }

    public void setRangoRdate(RangoDateTime rangoRdate) {
        this.rangoRdate = rangoRdate;
    }


}
