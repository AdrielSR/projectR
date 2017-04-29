$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var reqHeaders = [];
    reqHeaders[header] = token;
    
    $(".no-activos td i.activar").on("click", function () {
        var idUsuario = $(this).attr("data-id");
        toggleActivarDesactivar(idUsuario, reqHeaders);
    });
    
    $(".activos td i.desactivar").on("click", function () {
        var idUsuario = $(this).attr("data-id");
        toggleActivarDesactivar(idUsuario, reqHeaders);
    });

});


function toggleActivarDesactivar(idUsuario, reqHeaders) {
    $.ajax({
        url: baseURL + 'user/activar-desactivar-usuario',
        method: 'POST',
        headers: reqHeaders,
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