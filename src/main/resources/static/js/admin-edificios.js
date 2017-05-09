$(document).ready(function () {
  
    $(".no-activos td i.activar").on("click", function () {
        var idEdificio = $(this).attr("data-id");
        toggleActivarDesactivar(idEdificio);
    });
    
    $(".activos td i.desactivar").on("click", function () {
        var idEdificio = $(this).attr("data-id");
        toggleActivarDesactivar(idEdificio);
    });

});


function toggleActivarDesactivar(idEdificio) {
    $.ajax({
        url: baseURL + 'user/activar-desactivar-edificio',
        method: 'POST',
        data: JSON.stringify(idEdificio),
        contentType: 'application/json'
    })
    .done(function () {
    	removeElementById(idEdificio); 
    })
    .fail(function (xhr, status) {
        alert(status);
    });
 }