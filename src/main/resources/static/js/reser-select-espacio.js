$(document).ready(function(){


    $(".owl-carousel").owlCarousel({

        margin: 7,
        responsiveClass: true,
        responsive: {
            0:{
                items: 2
            },
            400:{
                items: 4
            },
            600:{
                items: 6
            }
        }
    });


    addItemClickEvent();
    selectFirstItem();

});


function addItemClickEvent(){
    $('.card').on('click', function(event){
        var $this = $(this);
        var idEspacio = $this.find('input').val();
        // Actualizo la url del boton siguiente
        var link =  baseURL + 'espacio/' + idEspacio + '/reservas';
        $("#espacio_link").attr("href",link);

        $(".espacioSeleccionado").removeClass("espacioSeleccionado");

        // Marco el seleccionado
        $this.find('.card-content').addClass('espacioSeleccionado');

        obtenerInfoEspacioSeleccionado(idEspacio);
    });
}

function selectFirstItem(){
    var $firstItem = $(".card:first");
    var idEspacio = $firstItem.find('input').val();
    $firstItem.find('div.card-content').addClass('espacioSeleccionado');

    obtenerInfoEspacioSeleccionado(idEspacio);
}

function obtenerInfoEspacioSeleccionado(idEspacio){
    $.ajax({
        url: baseURL + 'info-espacio',
        method: 'POST',
        data: JSON.stringify(idEspacio),
        contentType: 'application/json'
    })
    .done(function (datos) {
        actualizarInfoEspacioSeleccionado(datos);
    })
    .fail(function (xhr, status) {
        alert(status);
    });


}


function actualizarInfoEspacioSeleccionado(espacioDTO){

    $('#nombre-espacio').text(espacioDTO.nombre);
    $('#capacidad-espacio').text("Aforo: " + espacioDTO.aforo);

    var classMesa = espacioDTO.prestaciones.tieneMesa ? 'p-item-active' : 'p-item-no-active';
    var classSilla = espacioDTO.prestaciones.tieneSillas ? 'p-item-active' : 'p-item-no-active';
    var classProyector = espacioDTO.prestaciones.tieneProyector ? 'p-item-active' : 'p-item-no-active';
    var classOrdenador = espacioDTO.prestaciones.tieneOrdenador ? 'p-item-active' : 'p-item-no-active';
    var classPizarra = espacioDTO.prestaciones.tienePizarra ? 'p-item-active' : 'p-item-no-active';
    var classMicrofono = espacioDTO.prestaciones.tieneMicrofono ? 'p-item-active' : 'p-item-no-active';
    var classTelefono = espacioDTO.prestaciones.tieneTelefono ? 'p-item-active' : 'p-item-no-active';
    var classEnchufe = espacioDTO.prestaciones.tieneEnchufes ? 'p-item-active' : 'p-item-no-active';


    $('#p-mesa').removeClass('p-item-active p-item-no-active').addClass(classMesa);
    $('#p-silla').removeClass('p-item-active p-item-no-active').addClass(classSilla);
    $('#p-proyector').removeClass('p-item-active p-item-no-active').addClass(classProyector);
    $('#p-ordenador').removeClass('p-item-active p-item-no-active').addClass(classOrdenador);
    $('#p-pizarra').removeClass('p-item-active p-item-no-active').addClass(classPizarra);
    $('#p-microfono').removeClass('p-item-active p-item-no-active').addClass(classMicrofono);
    $('#p-telefono').removeClass('p-item-active p-item-no-active').addClass(classTelefono);
    $('#p-enchufe').removeClass('p-item-active p-item-no-active').addClass(classEnchufe);

}