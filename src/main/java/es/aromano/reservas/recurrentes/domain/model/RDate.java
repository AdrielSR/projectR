package es.aromano.reservas.recurrentes.domain.model;

import es.aromano.reservas.domain.model.RangoDateTime;
import org.joda.time.DateTime;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class RDate {

    @Embedded
    private RangoDateTime rangoRdate;

    public RangoDateTime getRangoRdate() {
        return rangoRdate;
    }

    public void setRangoRdate(RangoDateTime rangoRdate) {
        this.rangoRdate = rangoRdate;
    }
}
