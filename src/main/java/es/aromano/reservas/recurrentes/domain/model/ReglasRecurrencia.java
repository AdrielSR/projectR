package es.aromano.reservas.recurrentes.domain.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

@Embeddable
public class ReglasRecurrencia {

    @Embedded
    private RRule rrule;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "reserva_id"))
    private List<RDate> rdate;

    @ElementCollection
    @CollectionTable(joinColumns = @JoinColumn(name = "reserva_id"))
    private List<ExDate> exdate;

    protected ReglasRecurrencia(){
    }

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
