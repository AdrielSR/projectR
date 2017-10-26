package es.aromano.reservas.domain.model;

public enum RespuestaInvitacion {

    ASISTIRE("Voy a ir"),
    QUIZAS_ASISTA("Quiza"),
    NO_ASISTIRE("No voy a ir");

    private String name;

    RespuestaInvitacion(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
