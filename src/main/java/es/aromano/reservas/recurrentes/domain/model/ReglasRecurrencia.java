package es.aromano.reservas.recurrentes.domain.model;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ReglasRecurrencia {

    @Embedded
    private RRule rrule;

    private RDate rdate;

    private ExDate exdate;

    protected ReglasRecurrencia(){}

    public ReglasRecurrencia(RRule rrule){
        this.rrule = rrule;
        this.rdate = new RDate();
        this.exdate = new ExDate();
    }

    public RRule getRrule() {
        return rrule;
    }

    public void setRrule(RRule rrule) {
        this.rrule = rrule;
    }

    public RDate getRdate() {
        return rdate;
    }

    public void setRdate(RDate rdate) {
        this.rdate = rdate;
    }

    public ExDate getExdate() {
        return exdate;
    }

    public void setExdate(ExDate exdate) {
        this.exdate = exdate;
    }
}
