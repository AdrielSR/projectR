package es.aromano.reservas.web;

import es.aromano.reservas.excepciones.ReservaSolapadaException;
import es.aromano.reservas.model.Reserva;
import es.aromano.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
public class ReservaRestController {

    @Autowired
    private ReservaService reservaService;

    @RequestMapping(value = "/{id}/reservas", method = RequestMethod.GET)
    public List<ReservaDTO> getReservasFromEspacio(@PathVariable("id") int idEspacio){
        return reservaService.findReservasByIdEspacio(idEspacio).stream()
                .map(r -> ReservaDTO.from(r))
                .collect(Collectors.toList());
    }

    @RequestMapping(value = "/crear-reserva", method = RequestMethod.POST)
    public ResponseEntity<Void> crearReserva(@RequestBody ReservaDTO reservaDTO) throws ReservaSolapadaException {

        Reserva newReserva = reservaService.crearReserva(reservaDTO);

        if(Objects.isNull(newReserva)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
