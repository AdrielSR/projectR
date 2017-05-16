$(document).ready(function(){
	var reservaDTO = {};
	
	
	$("#btn-buscar").click(function(){
		var buscador = {};
		buscador.idEdificio = 1;
		buscador.inicio = toIso8601($("#datetimepicker1").val());
		buscador.fin = toIso8601($("#datetimepicker2").val());
		
		reservaDTO.start = buscador.inicio;
		reservaDTO.end = buscador.fin;
		reservaDTO.title = "";
		
		buscarEspaciosDisponibles(buscador);
	});
	
	
	$("ul.collection").on('click', 'a', function(){
		var idEspacio = $(this).attr("data-id");
		reservaDTO.idEspacio = idEspacio;
		
		crearReserva(reservaDTO);
	});
	
});


function buscarEspaciosDisponibles(buscador){
	 $.ajax({
	        url: baseURL + 'espacios-disponibles',
	        method: 'POST',
	        data: JSON.stringify(buscador),
	        contentType: 'application/json'
	    })
	    .done(function (datos) {
	    	removeAllElements();
	    	addElements(datos);    
	    })
	    .fail(function (xhr, status) {
	        var error = JSON.parse(xhr.responseText);
	        console.log(error.msg);
	    });
}

function crearReserva(reserva) {

    $.ajax({
        url: baseURL + 'crear-reserva',
        method: 'POST',
        data: JSON.stringify(reserva),
        contentType: 'application/json'
    })
	.done(function () {
		scrollToTop();
		showSuccessPanel("Reserva creada correctamente.");
        removeElementById(reserva.idEspacio);
	})
	.fail(function (xhr, status) {
        var error = JSON.parse(xhr.responseText);
        scrollToTop();
        showErrorPanel(error.msg);
	});

}

function removeAllElements(){
	$("ul.collection").empty();
}

function addElements(datos){
	$("ul.collection").append(
		$.map(datos, function (item, index) {
				 
			return '<li id="' + item.idEspacio + '"class="collection-item avatar" style="padding-left:72px;">' + 
					'<img src="/img/espacio.png" alt="" class="circle" />' +
					'<span class="title">'+ item.nombreEspacio +'</span>' +
					'<a data-id=' + item.idEspacio + ' class="secondary-content" style="cursor: pointer;">Reservar</a>'
					'</li>';
			
	}));
}


