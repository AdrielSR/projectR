$(document).ready(function () {
    
    $(".no-activos td i.activar").on("click", function () {
        var idUsuario = $(this).attr("data-id");
        toggleActivarDesactivar(idUsuario);
    });
    
    $(".activos td i.desactivar").on("click", function () {
        var idUsuario = $(this).attr("data-id");
        toggleActivarDesactivar(idUsuario);
    });

});


function toggleActivarDesactivar(idUsuario) {
    $.ajax({
        url: baseURL + 'user/activar-desactivar-usuario',
        method: 'POST',
        data: JSON.stringify(idUsuario),
        contentType: 'application/json'
    })
    .done(function () {
        $("#" + idUsuario).remove(); 
    })
    .fail(function (xhr, status) {
        alert(status);
    });
 }