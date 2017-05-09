$(document).ready(function(){
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var reqHeaders = [];
    reqHeaders[header] = token;

	$('.modal').modal();
	
	$('a.secondary-content').click(function(){
		var id_reserva = $(this).attr("data-id");
		
		var url_editar = "/reserva/" + id_reserva;
		$("#link-editar").attr("href", url_editar);

		$("#link-eliminar").attr("data-id", id_reserva);
	});

    $('#link-eliminar').click(function(){
		if(confirm("Quieres eliminar esta reserva?")){
		    var idReserva = $(this).attr("data-id");
		    eliminarReserva(idReserva, reqHeaders);
		}
    });

	
});

function eliminarReserva(idReserva, reqHeaders){
    $.ajax({
        url: baseURL + 'eliminar-reserva',
        method: 'POST',
        headers: reqHeaders,
        data: JSON.stringify(idReserva),
        contentType: 'application/json'
    })
	.done(function () {

		$("#" + idReserva).fadeOut(1000, function(){
			$(this).remove();
		});
	})
	.fail(function (xhr, status) {
		var error = JSON.parse(xhr.responseText);
		console.log(error.msg);
	});
}