package es.aromano.reservas.recurrentes.domain.model;

import es.aromano.reservas.domain.model.RangoDateTime;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class RDate {

    private String rangoRdate;

    protected RDate(){}

    public String getRangoRdate() {
        return rangoRdate;
    }

    public void setRangoRdate(String rangoRdate) {
        this.rangoRdate = rangoRdate;
    }

    public List<RangoDateTime> convertToRangeFromString(){

        if(StringUtils.isBlank(rangoRdate)){
            return new ArrayList<>();
        }

        List<RangoDateTime> result = new ArrayList<>();
        String[] fechas = rangoRdate.split(",");

        for(String fecha : fechas){
            DateTime ini = DateTime.parse(fecha);
            DateTime fin = ini;
            result.add(new RangoDateTime(ini, fin));
        }


        return result;
    }


}
