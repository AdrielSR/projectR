function removeElementById(id){
	$("#" + id).remove();
}

function refreshCalendar(){
    $("#calendar").fullCalendar('refetchEvents');
}

function closeModal(){
    $('#modal1').modal('close');
}

function scrollToTop(){
    $("html, body").animate({
        scrollTop: 0
    });
}

function showErrorPanel(msgError){
    $(".info-panel").addClass("info-error").text(msgError).show().delay(10000).fadeOut();
}

function showSuccessPanel(msgSuccess){
    $(".info-panel").addClass("info-success").text(msgSuccess).show().delay(10000).fadeOut();
}