package es.aromano.espacios.domain.model;

import javax.persistence.Embeddable;

@Embeddable
public class Prestaciones {

    private boolean tieneMesa;
    private boolean tieneSillas;
    private boolean tieneProyector;
    private boolean tieneOrdenador;
    private boolean tienePizarra;
    private boolean tieneMicrofono;
    private boolean tieneTelefono;
    private boolean tieneEnchufes;

    public Prestaciones() {
    }

    public boolean isTieneMesa() {
        return tieneMesa;
    }

    public void setTieneMesa(boolean tieneMesa) {
        this.tieneMesa = tieneMesa;
    }

    public boolean isTieneSillas() {
        return tieneSillas;
    }

    public void setTieneSillas(boolean tieneSillas) {
        this.tieneSillas = tieneSillas;
    }

    public boolean isTieneProyector() {
        return tieneProyector;
    }

    public void setTieneProyector(boolean tieneProyector) {
        this.tieneProyector = tieneProyector;
    }

    public boolean isTieneOrdenador() {
        return tieneOrdenador;
    }

    public void setTieneOrdenador(boolean tieneOrdenador) {
        this.tieneOrdenador = tieneOrdenador;
    }

    public boolean isTienePizarra() {
        return tienePizarra;
    }

    public void setTienePizarra(boolean tienePizarra) {
        this.tienePizarra = tienePizarra;
    }

    public boolean isTieneMicrofono() {
        return tieneMicrofono;
    }

    public void setTieneMicrofono(boolean tieneMicrofono) {
        this.tieneMicrofono = tieneMicrofono;
    }

    public boolean isTieneTelefono() {
        return tieneTelefono;
    }

    public void setTieneTelefono(boolean tieneTelefono) {
        this.tieneTelefono = tieneTelefono;
    }

    public boolean isTieneEnchufes() {
        return tieneEnchufes;
    }

    public void setTieneEnchufes(boolean tieneEnchufes) {
        this.tieneEnchufes = tieneEnchufes;
    }
}
