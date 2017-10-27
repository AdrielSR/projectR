$(document).ready(function() {


    $("#autocomplete-users").autocomplete({
        source: function(request, response){
            //TODO
            var term = request.term;

            buscarUsuariosEmpresa(term)
                .done(function (data) {
                    response($.map(datos,function(item){

                        var obj = {};
                        obj.label = item.id;
                        obj.value = item.tag;
                        return obj;

                    }))


                })
                .fail(function (xhr, status) {
                    var error = JSON.parse(xhr.responseText);
                    console.log(error.msg);
                });
        },
        select: function(event, ui){
            //TODO
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