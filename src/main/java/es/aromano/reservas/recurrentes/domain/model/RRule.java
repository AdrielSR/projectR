package es.aromano.reservas.recurrentes.domain.model;

import javax.persistence.Embeddable;
import java.util.Date;

@Embeddable
public class RRule {

    private Frecuency frecuency;

    private int interval;

    private int count;

    private Date until;


}
