$(document).ready(function() {
  
    $("#editar-reserva").click(function(){
        var reserva = {};
        reserva.id = idReserva;
        reserva.title = $("#asunto").val();
        reserva.start = toIso8601($("#datetimepicker1").val());
        reserva.end = toIso8601($("#datetimepicker2").val());
        reserva.idEspacio = $("#idEspacio").val();

        editarReserva(reserva);
    });


});


function editarReserva(reserva){
    $.ajax({
        url: baseURL + 'editar-reserva/' + idReserva,
        method: 'POST',
        data: JSON.stringify(reserva),
        contentType: 'application/json'
    })
    .done(function () {
        window.location.href = "/";
    })
    .fail(function (xhr, status) {
        var error = JSON.parse(xhr.responseText);
        console.log(error.msg);
    });

}