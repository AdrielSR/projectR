package es.aromano.reservas.web;

import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.*;


public class ReservaDTO {

    private Long id;
    private String title;
    private DateTime start;
    private DateTime end;
    private int idEspacio;
    private String nombreEspacio;
    private boolean editable;
    private ReglasRecurrencia reglas;
    private List<Integer> idsUsuariosInvitados;
    private String reservadoPor;

    public ReservaDTO(){ }

    private ReservaDTO(Long id, String title, DateTime start, DateTime end, int idEspacio, String nombreEspacio, String reservadoPor){
        this.id = id;
        this.title = title;
        this.start = start;
        this.end = end;
        this.idEspacio = idEspacio;
        this.nombreEspacio = nombreEspacio;
        this.editable = false;
        this.idsUsuariosInvitados = new ArrayList<>();
        this.reservadoPor = reservadoPor;
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
        String nombreEspacio = reserva.getEspacio().getNombre();
        String reservadoPor = StringUtils.capitalize(reserva.getUser().getUsername());

        return new ReservaDTO(id, title, start, end, idEspacio, nombreEspacio, reservadoPor);
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

    public ReglasRecurrencia getReglas() {
        return reglas;
    }

    public void setReglas(ReglasRecurrencia reglas) {
        this.reglas = reglas;
    }

    public boolean isRecurrente(){
	    return Objects.nonNull(reglas);
    }

    public List<Integer> getIdsUsuariosInvitados() {
        return idsUsuariosInvitados;
    }

    public void setIdsUsuariosInvitados(List<Integer> idsUsuariosInvitados) {
        this.idsUsuariosInvitados = idsUsuariosInvitados;
    }

    public boolean hayUsuariosInvitados(){
        return !idsUsuariosInvitados.isEmpty();
    }

    public String getReservadoPor() {
        return reservadoPor;
    }
}
