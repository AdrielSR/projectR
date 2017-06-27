package es.aromano.reservas.recurrentes.domain.model;

import org.joda.time.DateTime;

import javax.persistence.Embeddable;

@Embeddable
public class RRule {

    private Frecuency frecuency;

    private int interval;

    private int count;

    private DateTime until;


}
