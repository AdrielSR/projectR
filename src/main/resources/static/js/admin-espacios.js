$(document).ready(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    var reqHeaders = [];
    reqHeaders[header] = token;
    
    $(".no-activos td i.activar").on("click", function () {
        var idEspacio = $(this).attr("data-id");
        toggleActivarDesactivar(idEspacio, reqHeaders);
    });
    
    $(".activos td i.desactivar").on("click", function () {
        var idEspacio = $(this).attr("data-id");
        toggleActivarDesactivar(idEspacio, reqHeaders);
    });

});


function toggleActivarDesactivar(idEspacio, reqHeaders) {
    $.ajax({
        url: baseURL + 'user/activar-desactivar-espacio',
        method: 'POST',
        headers: reqHeaders,
        data: JSON.stringify(idEspacio),
        contentType: 'application/json'
    })
    .done(function () {
        $("#" + idEspacio).remove(); 
    })
    .fail(function (xhr, status) {
        alert(status);
    });
 }