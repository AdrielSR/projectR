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


public class CalculadorReservasStrategyMensual implements CalculadorReservasStrategy {

    private Reserva reserva;

    public CalculadorReservasStrategyMensual(Reserva reserva){
        this.reserva = reserva;
    }


    @Override
    public List<Reserva> calcular() {

        Optional<ReglasRecurrencia> reglas = Optional.ofNullable(reserva.getReglas());

        if(!reglas.isPresent()){
            return new ArrayList<>();
        }

        RRule rrule = reglas.get().getRrule();
        RDate rdate = reglas.get().getRdate();
        ExDate exdate = reglas.get().getExdate();

        List<RangoDateTime> instancias = calcularInstancias(rrule);
        agregarInstanciasExtra(rdate, instancias);
        eliminarExcepcionesDeInstancias(exdate, instancias);


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

    private void agregarInstanciasExtra(RDate rdate, List<RangoDateTime> instancias) {
        rdate.convertToRangeFromString().stream()
                .forEach(rDate -> instancias.add(rDate));
    }

    private void eliminarExcepcionesDeInstancias(ExDate exdate, List<RangoDateTime> instancias) {
        exdate.convertToRangeFromString().stream()
                .forEach(exDate -> instancias.remove(exDate));

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
        primeraInstancia.setInicio(start.plusMonths(interval * i));
        primeraInstancia.setFin(end.plusMonths(interval * i));

        return new RangoDateTime(primeraInstancia.getInicio(), primeraInstancia.getFin());
    }


}
