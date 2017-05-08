'use strict';

var DATE_FORMAT = 'DD/MM/YYYY HH:mm';

function toIso8601(date) {
    return moment(date, DATE_FORMAT).format();
}

function fromIso8601(date) {
    return moment(date).format(DATE_FORMAT);
}

function redondeoMinutos(minutos){
    var m = minutos;
    var aux = m % 10;
    if(aux > 0 && aux < 6)
        m = m - aux;
    else
        m = m + (10-aux);

    return m;
}