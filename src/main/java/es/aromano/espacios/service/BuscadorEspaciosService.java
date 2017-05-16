package es.aromano.espacios.service;


import es.aromano.espacios.domain.model.Espacio;
import es.aromano.reservas.domain.model.RangoDateTime;

import java.util.List;

public interface BuscadorEspaciosService {

    List<Espacio> findEspaciosDisponiblesEnEdificio(RangoDateTime rango, int idEdificio);

}
