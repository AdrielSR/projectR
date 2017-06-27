package es.aromano.reservas.recurrentes.domain.model;

import es.aromano.reservas.domain.model.RangoDateTime;
import org.joda.time.DateTime;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class ExDate {

    @Embedded
    private RangoDateTime rangoExdate;

    public RangoDateTime getRangoExdate() {
        return rangoExdate;
    }

    public void setRangoExdate(RangoDateTime rangoExdate) {
        this.rangoExdate = rangoExdate;
    }
}
