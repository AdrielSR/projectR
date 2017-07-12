package es.aromano.reservas.recurrentes.domain.model;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ReglasRecurrencia {

    @Embedded
    private RRule rrule;

    private List<RDate> rdate;

    private List<ExDate> exdate;

    protected ReglasRecurrencia(){}

    public ReglasRecurrencia(RRule rrule){
        this.rrule = rrule;
        this.rdate = new ArrayList<>();
        this.exdate = new ArrayList<>();
    }

    public RRule getRrule() {
        return rrule;
    }

    public void setRrule(RRule rrule) {
        this.rrule = rrule;
    }

    public List<RDate> getRdate() {
        return rdate;
    }

    public void setRdate(List<RDate> rdate) {
        this.rdate = rdate;
    }

    public List<ExDate> getExdate() {
        return exdate;
    }

    public void setExdate(List<ExDate> exdate) {
        this.exdate = exdate;
    }
}
