package es.aromano.espacios.service;

import es.aromano.espacios.domain.model.Espacio;
import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BuscadorEspaciosServiceImpl implements BuscadorEspaciosService {

    @Autowired
    private ReservaService reservaService;

    @Autowired
    private EspacioService espacioService;

    @Override
    public List<Espacio> findEspaciosDisponiblesEnEdificio(RangoDateTime rango, int idEdificio) {

        List<Espacio> espaciosEdificio = espacioService.findEspaciosActivosByIdEdificio(idEdificio);
        List<Espacio> espaciosDisponibles = espaciosEdificio.stream()
                            .filter(e -> reservaService.esPosibleReservarEspacioEnRango(rango, e.getId()))
                            .collect(Collectors.toList());


        return espaciosDisponibles;
    }
}
