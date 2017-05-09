$(document).ready(function(){

	$('.modal').modal();
	
    $("#crear-reserva").click(function(){
		var reserva = {};
		reserva.title = $("#asunto").val();
		reserva.start = toIso8601($("#datetimepicker1").val());
		reserva.end = toIso8601($("#datetimepicker2").val());
		reserva.idEspacio = $("#idEspacio").val();

		crearReserva(reserva);
	});


	$('#calendar').fullCalendar({
		lang: 'es',
		timezone: 'local',
	    header: {
	        left: 'prev,next today',
	        center: 'title',
	        right: 'month,agendaWeek,agendaDay,listWeek'
	    },
	    eventLimit: true,
	    selectable : true,
	    select : function(start, end) {

            var now = moment();
            var eventData = {
                start : start,
                end : end.subtract(1,'days'),
            };
            // redondeo de minutos
            var m = redondeoMinutos(now.minutes());

            // hora actual para start, +1 para end
            eventData.start.hours(now.hours());
            eventData.start.minutes(m);
            eventData.end.hours(now.hours()).add(1,'hours');
            eventData.end.minutes(m);

            $("#asunto").val("");
            $("#datetimepicker1").val(eventData.start.format("DD/MM/YYYY HH:mm"));
            $("#datetimepicker2").val(eventData.end.format("DD/MM/YYYY HH:mm"));

	    	$('#modal1').modal('open');
	    },
        eventSources: [
	             {
	                 url: baseURL + idEspacio + '/reservas',
                     textColor: 'black'
                 }
	         ]
  
	});
	
});

function crearReserva(reserva) {

    $.ajax({
        url: baseURL + 'crear-reserva',
        method: 'POST',
        data: JSON.stringify(reserva),
        contentType: 'application/json'
    })
	.done(function () {
        closeModal();
        refreshCalendar();
	})
	.fail(function (xhr, status) {
        closeModal();
        var error = JSON.parse(xhr.responseText);
		console.log(error.msg);
	});

}

