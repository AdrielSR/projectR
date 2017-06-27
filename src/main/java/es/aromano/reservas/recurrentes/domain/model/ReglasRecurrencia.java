package es.aromano.reservas.recurrentes.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.List;

@Embeddable
public class ReglasRecurrencia {

    @Embedded
    private RRule rrule;

    private List<RDate> rdate;

    private List<ExDate> exdate;

}
