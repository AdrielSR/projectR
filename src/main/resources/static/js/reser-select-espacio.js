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

        //LLamada ajax para actualizar los datos
        //getEspacioById(idEspacio, reqHeaders);

    });
}

function selectFirstItem(){
    $(".card-content:first").addClass('espacioSeleccionado');
}