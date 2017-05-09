function removeElementById(id){
	$("#" + id).remove();
}

function refreshCalendar(){
    $("#calendar").fullCalendar('refetchEvents');
}

function closeModal(){
    $('#modal1').modal('close');
}