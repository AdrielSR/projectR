package es.aromano.reservas.recurrentes.domain.model;

import es.aromano.reservas.domain.model.RangoDateTime;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class ExDate {

    private String rangoExdate;

    protected ExDate(){}

    public ExDate(String rangoExdate) {
        this.rangoExdate = rangoExdate;
    }

    public String getRangoExdate() {
        return rangoExdate;
    }

    public void setRangoExdate(String rangoExdate) {
        this.rangoExdate = rangoExdate;
    }

    public List<RangoDateTime> convertToRangeFromString(){

        if(StringUtils.isBlank(rangoExdate)){
            return new ArrayList<>();
        }

        List<RangoDateTime> result = new ArrayList<>();
        String[] fechas = rangoExdate.split(",");

        for(String fecha : fechas){
            DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");
            DateTime ini = DateTime.parse(fecha, dtf);
            result.add(new RangoDateTime(ini, ini));
        }


        return result;
    }

}
