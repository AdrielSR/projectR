$(document).ready(function() {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var reqHeaders = [];
    reqHeaders[header] = token;


    $("#editar-reserva").click(function(){
        var reserva = {};
        reserva.id = idReserva;
        reserva.title = $("#asunto").val();
        reserva.start = toIso8601($("#datetimepicker1").val());
        reserva.end = toIso8601($("#datetimepicker2").val());
        reserva.idEspacio = $("#idEspacio").val();

        editarReserva(reserva, reqHeaders);
    });


});


function editarReserva(reserva, reqHeaders){
    $.ajax({
        url: baseURL + 'editar-reserva/' + idReserva,
        method: 'POST',
        headers: reqHeaders,
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