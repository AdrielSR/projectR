$(document).ready(function(){
	
	
	$("#btn-buscar").click(function(){
		var buscador = {};
		buscador.idEdificio = 1;
		buscador.inicio = toIso8601($("#datetimepicker1").val());
		buscador.fin = toIso8601($("#datetimepicker2").val());
		
		buscarEspaciosDisponibles(buscador);
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


function removeAllElements(){
	$("ul.collection").empty();
}

function addElements(datos){
	$("ul.collection").append(
		$.map(datos, function (item, index) {
				 
			return '<li class="collection-item avatar" style="padding-left:72px;">' + 
					'<img src="/img/espacio.png" alt="" class="circle" />' +
					'<span class="title">'+ item.nombreEspacio +'</span>' +
					'<a class="secondary-content" >Reservar</a>'
					'</li>';
			
	}));
}


