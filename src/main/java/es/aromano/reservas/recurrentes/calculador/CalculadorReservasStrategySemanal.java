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
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class CalculadorReservasStrategySemanal implements CalculadorReservasStrategy{

    private Reserva reserva;

    public CalculadorReservasStrategySemanal(Reserva reserva) {
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
        List<RangoDateTime> rangosExDate = exdate.convertToRangeFromString();
        List<RangoDateTime> _instancias = new ArrayList<>(instancias);

        for(RangoDateTime rangoExDate : rangosExDate){
            removeRangoIfPresent(_instancias, instancias, rangoExDate);
        }

    }

    private void removeRangoIfPresent(List<RangoDateTime> _instancias, List<RangoDateTime> instancias, RangoDateTime rango){
        for(RangoDateTime r : _instancias){
            if(r.getInicio().toString("dd/MM/yyyy").equals(rango.getInicio().toString("dd/MM/yyyy"))){
                instancias.remove(r);
            }
        }
    }

    private List<RangoDateTime> calcularInstancias(RRule rrule){
        List<RangoDateTime> instancias = new ArrayList<>();
        int interval = rrule.getInterval();
        int count = rrule.getCount();
        String daysOfWeek = rrule.getDaysOfWeek();

        RangoDateTime primeraInstancia = new RangoDateTime(reserva.getInicio(), reserva.getFin());

        int i = 0;
        while (count > 0){

            DateTime start = reserva.getInicio();
            DateTime end = reserva.getFin();

            String[] dias = daysOfWeek.split(",");

            for(int j=0; j < dias.length && count > 0; j++){

                int dayOfWeek = Integer.parseInt(dias[j]);
                int currentDay = start.getDayOfWeek();
                int diasASumar = dayOfWeek - currentDay;

                if(diasASumar >= 0 || i != 0){

                    instancias.add(calcularProximaInstancia(primeraInstancia, start, end, diasASumar, interval, i));
                    count--;
                }

            }



            i++;
        }

        return instancias;
    }

    private RangoDateTime calcularProximaInstancia(RangoDateTime primeraInstancia, DateTime start, DateTime end, int diasASumar, int interval, int i) {
        primeraInstancia.setInicio(start.plusDays(diasASumar + (7 * interval * i)));
        primeraInstancia.setFin(end.plusDays(diasASumar + (7 * interval * i)));

        return new RangoDateTime(primeraInstancia.getInicio(), primeraInstancia.getFin());
    }



}
