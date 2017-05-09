$(document).ready(function () {
  
    $(".no-activos td i.activar").on("click", function () {
        var idEspacio = $(this).attr("data-id");
        toggleActivarDesactivar(idEspacio);
    });
    
    $(".activos td i.desactivar").on("click", function () {
        var idEspacio = $(this).attr("data-id");
        toggleActivarDesactivar(idEspacio);
    });

});


function toggleActivarDesactivar(idEspacio) {
    $.ajax({
        url: baseURL + 'user/activar-desactivar-espacio',
        method: 'POST',
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