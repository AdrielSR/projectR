$(document).ready(function(){
	var token = $("meta[name='_csrf']").attr("content");
 	var header = $("meta[name='_csrf_header']").attr("content");
 	var reqHeaders = [];
 	reqHeaders[header] = token;


    $('#datetimepicker1').datetimepicker({
		format: 'd/m/Y H:i'
	});

    $('#datetimepicker2').datetimepicker({
        format: 'd/m/Y H:i'
    });

	$("#crear-reserva").click(function(){
		var reserva = {};
		reserva.title = $("#asunto").val();
		reserva.start = toIso8601($("#datetimepicker1").val());
		reserva.end = toIso8601($("#datetimepicker2").val());
		reserva.idEspacio = $("#idEspacio").val();

		crearReserva(reserva, reqHeaders);
	});


	$('.modal').modal();

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

function crearReserva(reserva, reqHeaders) {

    $.ajax({
        url: baseURL + 'crear-reserva',
        method: 'POST',
        headers: reqHeaders,
        data: JSON.stringify(reserva),
        contentType: 'application/json'
    })
	.done(function () {
        $('#modal1').modal('close');
        refreshCalendar();
	})
	.fail(function (xhr, status) {
		alert(status);
	});

}


function refreshCalendar(){
    $("#calendar").fullCalendar('refetchEvents');
}

function closeModal(){
    $('#modal1').modal('close');
}