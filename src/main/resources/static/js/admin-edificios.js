$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var reqHeaders = [];
    reqHeaders[header] = token;
    
    $(".no-activos td i.activar").on("click", function () {
        var idEdificio = $(this).attr("data-id");
        toggleActivarDesactivar(idEdificio, reqHeaders);
    });
    
    $(".activos td i.desactivar").on("click", function () {
        var idEdificio = $(this).attr("data-id");
        toggleActivarDesactivar(idEdificio, reqHeaders);
    });

});


function toggleActivarDesactivar(idEdificio, reqHeaders) {
    $.ajax({
        url: baseURL + 'user/activar-desactivar-edificio',
        method: 'POST',
        headers: reqHeaders,
        data: JSON.stringify(idEdificio),
        contentType: 'application/json'
    })
    .done(function () {
        $("#" + idEdificio).remove(); 
    })
    .fail(function (xhr, status) {
        alert(status);
    });
 }