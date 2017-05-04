package es.aromano.reservas.web;

import es.aromano.reservas.service.ReservaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;


@RestController
public class ReservaRestController {

    @Autowired
    private ReservaService reservaService;

    @RequestMapping(value = "/espacio/{id}/reservas")
    public List<ReservaDTO> getReservasFromEspacio(@PathVariable("id") int idEspacio){
        return reservaService.findReservasByIdEspacio(idEspacio).stream()
                .map(r -> ReservaDTO.from(r))
                .collect(Collectors.toList());
    }

}
