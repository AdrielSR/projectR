package es.aromano.reservas.recurrentes.domain.model;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
public class RRule {

    @Enumerated(EnumType.STRING)
    private Frecuency frecuency;

    private int intervalo;

    private int count;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private DateTime until;

    private String daysOfWeek;

    protected  RRule(){}

    public RRule(Frecuency frecuency, int intervalo){
        this.frecuency = frecuency;
        this.intervalo = intervalo;
    }

    public Frecuency getFrecuency() {
        return frecuency;
    }

    public void setFrecuency(Frecuency frecuency) {
        this.frecuency = frecuency;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public DateTime getUntil() {
        return until;
    }

    public void setUntil(DateTime until) {
        this.until = until;
    }

    public int getIntervalo() {
        return intervalo;
    }

    public void setIntervalo(int intervalo) {
        this.intervalo = intervalo;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
