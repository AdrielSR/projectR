package es.aromano.reservas.web;

import es.aromano.reservas.domain.excepciones.ReservaSolapadaException;
import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.service.ReservaService;
import es.aromano.reservas.web.dto.ReservaDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@RestController
public class ReservaRestController {

    @Autowired
    private ReservaService reservaService;

    @RequestMapping(value = "/{id}/reservas", method = RequestMethod.GET)
    public List<ReservaDTO> getReservasFromEspacio(@PathVariable("id") int idEspacio){
    	
    	List<ReservaDTO> result = new ArrayList<>();
    	List<Reserva> reservasUsuario = reservaService.findReservasUsuarioDeUnEspacio(idEspacio);

    	// Reservas del usuario logado (Editables)
    	for(Reserva r : reservasUsuario){
    	    if(r.isRecurrente()){
                result.addAll(r.calcularReservas().stream()
                                .map(reserva -> ReservaDTO.from(reserva).editable())
                                .collect(Collectors.toList()));
            }
            else {
    	        result.add(ReservaDTO.from(r).editable());
            }
        }

    	
    	List<Reserva> reservasNoUsuario = reservaService.findReservasNoUsuarioDeUnEspacio(idEspacio);
    	// Reservas de otros usuarios (No editables)
        for(Reserva r : reservasNoUsuario){
            if(r.isRecurrente()){
                result.addAll(r.calcularReservas().stream()
                        .map(reserva -> ReservaDTO.from(reserva))
                        .collect(Collectors.toList()));
            }
            else {
                result.add(ReservaDTO.from(r));
            }
        }

    	
        return result;
    }

    @RequestMapping(value = "/crear-reserva", method = RequestMethod.POST)
    public ResponseEntity<Void> crearReserva(@RequestBody ReservaDTO reservaDTO) throws ReservaSolapadaException {

        Reserva newReserva = reservaService.crearReserva(reservaDTO);

        if(Objects.isNull(newReserva)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PreAuthorize("@reservaServiceImpl.canAccessUser(#idReserva)")
    @RequestMapping(value = "/editar-reserva/{id}", method = RequestMethod.POST)
    public ResponseEntity<Void> editarReserva(@PathVariable("id") long idReserva, @RequestBody ReservaDTO reservaDTO) throws ReservaSolapadaException {

        Reserva editedReserva = reservaService.editarReserva(reservaDTO);

        if(Objects.isNull(editedReserva)){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }


        return ResponseEntity.status(HttpStatus.OK).build();
    }


    @RequestMapping(value = "/eliminar-reserva", method = RequestMethod.POST)
    public ResponseEntity<Void> eliminarReserva(@RequestBody long idReserva) {

        reservaService.eliminarReserva(idReserva);

        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
