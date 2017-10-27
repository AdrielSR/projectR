$(document).ready(function() {

    $('input.autocomplete').autocomplete({
        data: {"algo" : null,
                    "otra cosa" : null},
        limit: 20,
        onAutocomplete: function(val) {
            // Callback function when value is autcompleted.
        },
        minLength: 3
    });


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
    invitaciones.push(2);
    invitaciones.push(4);

    return invitaciones;
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