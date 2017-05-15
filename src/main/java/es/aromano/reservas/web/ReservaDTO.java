package es.aromano.reservas.web;

import es.aromano.reservas.domain.model.Reserva;
import org.joda.time.DateTime;

import static org.apache.commons.lang3.StringUtils.*;


public class ReservaDTO {

    private Long id;
    private String title;
    private DateTime start;
    private DateTime end;
    private int idEspacio;
    private String nombreEspacio;
    private boolean editable;

    public ReservaDTO(){ }

    public ReservaDTO(Long id, String title, DateTime start, DateTime end, int idEspacio, String nombreEspacio){
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.idEspacio = idEspacio;
        this.nombreEspacio = nombreEspacio;
        this.editable = false;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = isNotBlank(title) ? title : "Sin asunto";
    }

    public DateTime getStart() {
        return start;
    }

    public void setStart(DateTime start) {
        this.start = start;
    }

    public DateTime getEnd() {
        return end;
    }

    public void setEnd(DateTime end) {
        this.end = end;
    }

    public int getIdEspacio() {
        return idEspacio;
    }

    public void setIdEspacio(int idEspacio) {
        this.idEspacio = idEspacio;
    }

    public String getNombreEspacio() {
        return nombreEspacio;
    }

    public void setNombreEspacio(String nombreEspacio) {
        this.nombreEspacio = nombreEspacio;
    }


    public static ReservaDTO from(Reserva reserva){

        Long id = reserva.getId();
        String title = reserva.getAsunto();
        DateTime start = reserva.getRango().getInicio();
        DateTime end = reserva.getRango().getFin();
        int idEspacio = reserva.getEspacio().getId();
        String nombreEspacio =reserva.getEspacio().getNombre();

        return new ReservaDTO(id, title, start, end, idEspacio, nombreEspacio);
    }

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}
	
	public ReservaDTO editable(){
		this.editable = true;
		return this;
	}

}
