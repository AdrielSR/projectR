package es.aromano.espacios.web.dto;

import org.joda.time.DateTime;

public class BuscadorEspaciosDTO {

    private int idEdificio;

    private DateTime inicio;

    private DateTime fin;

    public BuscadorEspaciosDTO(){ }

    public BuscadorEspaciosDTO(int idEdificio, DateTime inicio, DateTime fin) {
        this.idEdificio = idEdificio;
        this.inicio = inicio;
        this.fin = fin;
    }


    public int getIdEdificio() {
        return this.idEdificio;
    }

    public void setIdEdificio(int idEdificio) {
        this.idEdificio = idEdificio;
    }

    public DateTime getInicio() {
        return this.inicio;
    }

    public void setInicio(DateTime inicio) {
        this.inicio = inicio;
    }

    public DateTime getFin() {
        return this.fin;
    }

    public void setFin(DateTime fin) {
        this.fin = fin;
    }
}
