$(function () {
	$("input[id^='datetimepicker']").datetimepicker({
		format: 'd/m/Y H:i',
		lang: 'es',
		step: 30,
		mask: true
	});
});

$(document).ready(function(){

	/// Controla que la hora de fin no sea menor que la de inicio ///
	$("#datetimepicker1").change(function(){
	    var comienzo = toIso8601($('#datetimepicker1').val());
	    var m = new moment(comienzo);
	    var fin = m.add(1,'hours');
	    $('#datetimepicker2').val(fin.format("DD/MM/YYYY HH:mm"));
	});
	
	$("#datetimepicker2").change(function(){
	    var comienzo = toIso8601($('#datetimepicker1').val());
	    var fin = toIso8601($('#datetimepicker2').val());
	    var start = new moment(comienzo)
	    var end = new moment(fin);
	    if(end.isBefore(start)){
	        $('#datetimepicker2').val(start.add(1,'hours').format("DD/MM/YYYY HH:mm"));
	    }
	    else{
	        $('#datetimepicker2').val(end.format("DD/MM/YYYY HH:mm"));
	    }
	
	});

});