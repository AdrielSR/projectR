package es.aromano.reservas.excepciones;


public class ReservaException extends Exception {

    public ReservaException(String message) {
        super(message);
    }

    public ReservaException(String message, Throwable cause) {
        super(message, cause);
    }

}
