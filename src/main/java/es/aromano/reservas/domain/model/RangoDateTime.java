package es.aromano.reservas.domain.model;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Embeddable
public class RangoDateTime {

	
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
	public boolean equals(Object obj){
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;

		RangoDateTime otro = (RangoDateTime) obj;

		return this.inicio.compareTo(otro.getInicio()) == 0
				&& this.fin.compareTo(otro.getFin()) == 0;

	}

}
