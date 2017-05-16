package es.aromano.espacios.web;

import es.aromano.espacios.domain.model.Espacio;
import es.aromano.espacios.service.BuscadorEspaciosService;
import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.web.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class BuscadorEspaciosRestController {

    @Autowired
    private BuscadorEspaciosService buscadorEspaciosService;

    @RequestMapping(value = "/espacios-disponibles", method = RequestMethod.POST)
    public List<ReservaDTO> reservasDisponibles(@RequestBody BuscadorEspaciosDTO buscador){
        List<ReservaDTO> result;

        RangoDateTime rango = new RangoDateTime(buscador.getInicio(), buscador.getFin());
        int idEdificio = buscador.getIdEdificio();

        List<Espacio> espaciosDisponibles = buscadorEspaciosService.findEspaciosDisponiblesEnEdificio(rango, idEdificio);

        result = espaciosDisponibles.stream()
                .map(espacio -> crearReserva(espacio, rango))
                .collect(Collectors.toList());

        return result;
    }



    private ReservaDTO crearReserva(Espacio espacio, RangoDateTime rango){
        ReservaDTO reserva = new ReservaDTO();
        reserva.setIdEspacio(espacio.getId());
        reserva.setNombreEspacio(espacio.getNombre());
        reserva.setStart(rango.getInicio());
        reserva.setEnd(rango.getFin());

        return  reserva;
    }

}
