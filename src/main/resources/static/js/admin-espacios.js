$(document).ready(function () {
  
    $(".no-activos td i.activar").on("click", function () {
        var idEspacio = $(this).attr("data-id");
        toggleActivarDesactivar(idEspacio);
    });
    
    $(".activos td i.desactivar").on("click", function () {
        var idEspacio = $(this).attr("data-id");
        toggleActivarDesactivar(idEspacio);
    });

    $('#btnEditarEspacio').on('click', function(){

        var espacioDTO = generarEspacioJSON();
        editarEspacio(espacioDTO);
    });


});


function toggleActivarDesactivar(idEspacio) {
    $.ajax({
        url: baseURL + 'activar-desactivar-espacio',
        method: 'POST',
        data: JSON.stringify(idEspacio),
        contentType: 'application/json'
    })
    .done(function () {
    	removeElementById(idEspacio);   
    })
    .fail(function (xhr, status) {
        alert(status);
    });
 }


 function generarEspacioJSON(){
    var result = {};
    result.id = idEspacio;
    result.nombre = $('#nombre-espacio').val();
    result.aforo = $('#aforo-espacio').val();
    result.idEdificio = $('#selec-id-edificio').val();

    var prestaciones = {};

    //TODO: comprobar qué opciones están seleccionadas
     prestaciones.tieneMesa = false;
     prestaciones.tieneSillas = false;
     prestaciones.tieneProyector = false;
     prestaciones.tieneOrdenador = false;
     prestaciones.tienePizarra = false;
     prestaciones.tieneMicrofono = false;
     prestaciones.tieneTelefono = false;
     prestaciones.tieneEnchufes = false;

    result.prestaciones = prestaciones;


    return result;
 }

 function editarEspacio(espacioDTO){
     $.ajax({
         url: baseURL + 'editar-espacio',
         method: 'POST',
         data: JSON.stringify(espacioDTO),
         contentType: 'application/json'
     })
         .done(function () {
             alert("Espacio editado correctamente");
         })
         .fail(function (xhr, status) {
             alert(status);
         });
 }