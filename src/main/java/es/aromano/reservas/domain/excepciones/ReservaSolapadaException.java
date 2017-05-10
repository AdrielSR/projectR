package es.aromano.reservas.domain.excepciones;


public class ReservaSolapadaException extends ReservaException{

    public ReservaSolapadaException(String message) {
        super(message);
    }

    public ReservaSolapadaException(String message, Throwable cause) {
        super(message, cause);
    }

}
