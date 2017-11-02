package es.aromano.reservas.service;

import es.aromano.reservas.domain.InvitacionRepository;
import es.aromano.reservas.domain.model.Invitacion;
import es.aromano.reservas.domain.model.Respuesta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class InvitacionServiceImpl implements InvitacionService {

    @Autowired
    private InvitacionRepository invitacionRepository;

    @Override
    public List<Invitacion> findInvitacionesUsuario() {
        return invitacionRepository.findInvitacionesUsuario();
    }

    @Override
    public void responder(long idReserva, int idUsuario, Respuesta respuesta) {

        Invitacion invitacion = invitacionRepository.findInvitacion(idReserva, idUsuario);
        if(Objects.nonNull(invitacion)){
            invitacion.setRespuesta(respuesta);
            invitacionRepository.save(invitacion);
        }

    }
}
