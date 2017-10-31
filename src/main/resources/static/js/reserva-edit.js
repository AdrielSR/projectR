$(document).ready(function() {

    $("#editar-reserva").click(function(){
        var reserva = {};
        reserva.id = idReserva;
        reserva.title = $("#asunto").val();
        reserva.start = toIso8601($("#datetimepicker1").val());
        reserva.end = toIso8601($("#datetimepicker2").val());
        reserva.idEspacio = $("#idEspacio").val();
        reserva.idsUsuariosInvitados = obtenerUsuariosSeleccionados();

        editarReserva(reserva);
    });


});

function obtenerUsuariosSeleccionados(){
    var invitaciones = [];

    $("div.chip").each(function(){
       var idUsuario = $(this).data("id-usuario");
        invitaciones.push(idUsuario);
    });

    return invitaciones;
}

function buscarUsuariosEmpresa(term){
        return $.ajax({
                    url: baseURL + 'buscar-usuarios-empresa/' + term,
                    method: 'GET',
                    contentType: 'application/json'
                });
}

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