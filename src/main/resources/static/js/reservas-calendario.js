$(document).ready(function(){

	$('.modal').modal();
	
    $("#crear-reserva").click(function(){
		var reserva = generarJSON();
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
	    	if(view.name === 'month'){
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


    /// CHECK REPETIR ///
	$("#checkRepetir").change(function () {
		$("#repetirContainer").toggle();
    });

	/// SELECTOR FRECUENCIA ///
    $("#selec_frec").change(function(){
    	var t =  $("#selec_frec option:selected").text();
    	var dow = [];
    	desmarcarChecks();

        if(t === 'Todos los lunes, miercoles y viernes'){
            $("#checksDiasSemana").hide();

            dow = ["L","X","V"];
            marcarChecks(dow);
        }
        else if(t === 'Todos los martes y jueves'){
            $("#checksDiasSemana").hide();

            dow = ["M","J"];
            marcarChecks(dow);
        }
        else if(t === 'Todos los dias laborables (de lunes a viernes)'){
            $("#checksDiasSemana").hide();

            dow = ["L","M","X","J","V"];
            marcarChecks(dow);
        }
        else if(t === 'Cada dia'){

            $("#checksDiasSemana").hide();

        }
        else if(t === 'Cada semana'){

            $("#checksDiasSemana").show();

        }
        else if(t === 'Cada mes'){

            $("#checksDiasSemana").hide();

        }
        else{
            $("#checksDiasSemana").hide();
        }


    });
	
});


function generarJSON(){
    var reserva = {};
    reserva.title = $("#asunto").val();
    reserva.start = toIso8601($("#datetimepicker1").val());
    reserva.end = toIso8601($("#datetimepicker2").val());
    reserva.idEspacio = $("#idEspacio").val();

    if($("#checkRepetir").is(":checked")){
    	reserva.reglas = generarReglasJSON();
	}

    return reserva;
}


function generarReglasJSON(){
	var reglas = {};
	reglas.rrule = {};
    reglas.rdate = [];
    reglas.exdate = [];

    reglas.rrule.frecuency =  $("#selec_frec").val();
    reglas.rrule.intervalo = $("#selec_inter").val();
    reglas.rrule.count = $("#count_repeat").val();
    reglas.rrule.until = null;
    reglas.rrule.daysOfWeek = getChecksMarcados();



    return reglas;
}



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
        scrollToTop();
        showErrorPanel(error.msg);
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

function showTooltip(event, $element){
    var content = '<div style="min-width: 400px; font-size: 12px;">';

    var reservadoPor = '<p class="right" style="margin-top: 0px;"><b><i>Reservado por: </i></b>' + event.reservadoPor + '</p>';
	var asunto = '<p><b><i>Asunto: </i></b>' + event.title + '</p>';
	var cuando = '<p><b><i>Cuándo: </i></b>' + event.start.format("DD/MM/YYYY HH:mm") + '</p>';
	var donde = '<p><b><i>Dónde: </i></b>' + event.nombreEspacio + '</p>';

	if(!event.editable){
	    content += reservadoPor;
    }

	content += asunto + cuando + donde;
	
	if(event.editable){
		var editar = '<div class="col s6 left"><a href="/reserva/' + event.id +'">Editar</a></div>';
		var eliminar = '<div class="col s6 right"><a href="#" onclick="eliminarReserva(\'' + event.id + '\')">Eliminar</a></div>';
        content += '<div class="col s12">' + editar + eliminar + '</div>';
	}

	content += '</div>';
	
	$element.tooltipster({
		contentAsHTML: true,
		contentCloning: true,
		interactive: true,
		theme: 'tooltipster-light',
        trigger: 'click'
	}).tooltipster('content', content);
	
}


function getChecksMarcados(){
	var daysOfWeek = [];

	$("#checksDiasSemana input").each(function (index, elem) {
			if($(this).is(":checked")){
				var valor = $(this).attr("data-value");
				daysOfWeek.push(valor);
			}
    });

	return daysOfWeek.join(",");
}


function desmarcarChecks(){
    $("#checksDiasSemana input").each(function (index, elem) {
        $(this).prop("checked", false);
    });
}

function marcarChecks(array) {
    for(var i in array){
        $("#check_" + array[i]).prop("checked",true);
    }
}