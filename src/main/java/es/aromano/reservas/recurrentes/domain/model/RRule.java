package es.aromano.reservas.recurrentes.domain.model;

import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Embeddable
public class RRule {

    @Enumerated(EnumType.STRING)
    private Frecuency frecuency;

    @NotNull
    private int interval;

    private int count;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
    private DateTime until;

    private String daysOfWeek;

    protected  RRule(){}

    public RRule(Frecuency frecuency, int interval){
        this.frecuency = frecuency;
        this.interval = interval;
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

    public int getInterval() {
        return interval;
    }

    public void setInterval(int interval) {
        this.interval = interval;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(String daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
