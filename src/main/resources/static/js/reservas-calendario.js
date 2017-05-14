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
		timezone: 'local',
	    header: {
	        left: 'prev,next today',
	        center: 'title',
	        right: 'month,agendaWeek,agendaDay,listWeek'
	    },
	    eventLimit: true,
	    eventClick: function(event, jsEvent, view){
	    	if(view.name == 'month'){
	    		showTooltip(event, $(this));
	    	}
	    	
	    },
		editable: true,
	    selectable : true,
	    eventResize: function(event, delta, revertFunc, jsEvent) {
	    	editarReserva(event, revertFunc);
	    },
        eventDrop: function(event, delta, revertFunc) {
            editarReserva(event, revertFunc);
        },
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

function editarReserva(reserva, revertFunc) {

    $.ajax({
        url: baseURL + 'editar-reserva/' + reserva.id,
        method: 'POST',
        data: JSON.stringify(reserva),
        contentType: 'application/json'
    })
    .done(function () {
        refreshCalendar();
    })
    .fail(function (xhr, status) {
        var error = JSON.parse(xhr.responseText);

        scrollToTop();
        showErrorPanel(error.msg);
        revertFunc();
    });

}

function eliminarReserva(idReserva){
	if(confirm("¿Desea eliminar esta reserva?")){
		eliminarReservaCalendario(idReserva);
	}
}

function eliminarReservaCalendario(idReserva){
	$.ajax({
        url: baseURL + 'eliminar-reserva',
        method: 'POST',
        data: JSON.stringify(idReserva),
        contentType: 'application/json'
    })
	.done(function () {
		refreshCalendar();
	})
	.fail(function (xhr, status) {
		var error = JSON.parse(xhr.responseText);
		console.log(error.msg);
	});
}

function scrollToTop(){
    $("html, body").animate({
        scrollTop: 0
    });
}

function showErrorPanel(msgError){
    $(".info-panel").addClass("info-error").text(msgError).show().delay(10000).fadeOut();
}


function showTooltip(event, $element){
	var asunto = '<p>Asunto: ' + event.title + '</p>';
	var cuando = '<p>Cuando: ' + event.start.format("DD/MM/YYYY HH:mm") + '</p>';
	var donde = '<p>Donde: ' + event.nombreEspacio + '</p>'
	
	var content = asunto + cuando + donde;
	
	//if(event.editable){
		var editar = '<div class="col s6 left"><a href="/reserva/' + event.id +'">Editar</a></div>';
		var eliminar = '<div class="col s6 right"><a href="#" onclick="eliminarReserva(\'' + event.id + '\')">Eliminar</a></div>';
		var acciones = '<div class="col s12">' + editar + eliminar + '</div>';
		
		content += acciones;
	//}
	
	$element.tooltipster({
		contentAsHTML: true,
		contentCloning: true,
		interactive: true,
		theme: 'tooltipster-light',
        trigger: 'click'
	}).tooltipster('content', content);
}