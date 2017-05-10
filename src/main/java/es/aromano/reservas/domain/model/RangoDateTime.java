package es.aromano.reservas.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class RangoDateTime implements Comparable<DateTime>{

	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name="inicio", nullable=false)
	private DateTime inicio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy HH:mm")
	@Column(name="fin", nullable=false)
	private DateTime fin;
	
	
	public RangoDateTime(){}
	
	public RangoDateTime(DateTime inicio, DateTime fin){
		this.inicio = inicio;
		this.fin = fin;
	}

	public DateTime getInicio() {
		return inicio;
	}

	public void setInicio(DateTime inicio) {
		this.inicio = inicio;
	}

	public DateTime getFin() {
		return fin;
	}

	public void setFin(DateTime fin) {
		this.fin = fin;
	}

	@Override
	public int compareTo(DateTime rango) {
		return this.compareTo(rango);
	}
	
	
}
