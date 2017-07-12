package es.aromano.reservas.recurrentes.domain.model;

import org.joda.time.DateTime;

import javax.persistence.Embeddable;

@Embeddable
public class RRule {

    private Frecuency frecuency;

    private int interval;

    private int count;

    private DateTime until;

    private int[] daysOfWeek;

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

    public int[] getDaysOfWeek() {
        return daysOfWeek;
    }

    public void setDaysOfWeek(int[] daysOfWeek) {
        this.daysOfWeek = daysOfWeek;
    }
}
