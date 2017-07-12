package es.aromano.reservas.recurrentes.calculador;

import es.aromano.reservas.domain.model.RangoDateTime;
import es.aromano.reservas.domain.model.Reserva;
import es.aromano.reservas.domain.model.ReservaStepBuilder;
import es.aromano.reservas.recurrentes.domain.model.ExDate;
import es.aromano.reservas.recurrentes.domain.model.RDate;
import es.aromano.reservas.recurrentes.domain.model.RRule;
import es.aromano.reservas.recurrentes.domain.model.ReglasRecurrencia;
import org.joda.time.DateTime;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class CalculadorReservasStrategyDiario implements CalculadorReservasStrategy {

    private Reserva reserva;

    public CalculadorReservasStrategyDiario(Reserva reserva) {
        this.reserva = reserva;
    }

    @Override
    public List<Reserva> calcular() {

        Optional<ReglasRecurrencia> reglas = Optional.ofNullable(reserva.getReglas());

        if(!reglas.isPresent()){
            return new ArrayList<>();
        }

        RRule rrule = reglas.get().getRrule();
        List<RDate> rdates = reglas.get().getRdate();
        List<ExDate> exdates = reglas.get().getExdate();

        List<RangoDateTime> instancias = calcularInstancias(rrule);
        agregarInstanciasExtra(rdates, instancias);
        eliminarExcepcionesDeInstancias(exdates, instancias);


        return instancias.stream()
                .map(rango -> crearReservaFrom(rango))
                .collect(Collectors.toList());
    }


    private Reserva crearReservaFrom(RangoDateTime rango) {
        return ReservaStepBuilder.builder()
                .propietario(reserva.getUser())
                .lugar(reserva.getEspacio())
                .desde(rango.getInicio())
                .hasta(rango.getFin())
                .asunto(reserva.getAsunto())
                .build();
    }

    private void agregarInstanciasExtra(List<RDate> rdates, List<RangoDateTime> instancias) {
        rdates.stream()
                .forEach(rDate -> instancias.add(rDate.getRangoRdate()));
    }

    private void eliminarExcepcionesDeInstancias(List<ExDate> exdates, List<RangoDateTime> instancias) {
        exdates.stream()
                .forEach(exDate -> instancias.remove(exDate.getRangoExdate()));

    }


    private List<RangoDateTime> calcularInstancias(RRule rrule){
        List<RangoDateTime> instancias = new ArrayList<>();
        int interval = rrule.getInterval();
        int count = rrule.getCount();

        RangoDateTime primeraInstancia = new RangoDateTime(reserva.getInicio(), reserva.getFin());

        int i = 0;
        while (count > 0){

            DateTime start = reserva.getInicio();
            DateTime end = reserva.getFin();

            instancias.add(calcularProximaInstancia(primeraInstancia, start, end, interval, i));


            count--;
            i++;
        }

        return instancias;
    }

    private RangoDateTime calcularProximaInstancia(RangoDateTime primeraInstancia, DateTime start, DateTime end, int interval, int i) {
        primeraInstancia.setInicio(start.plusDays(interval * i));
        primeraInstancia.setFin(end.plusDays(interval * i));

        return new RangoDateTime(primeraInstancia.getInicio(), primeraInstancia.getFin());
    }


}
