/**
 * @ powered by Rosteach
 */

$("document").ready(function(){
	
	$("#testButton").click(function(){
		$.ajax({
			url: 'ajaxTest',
			data: ({name: "Rosteach"}),
			success: function(data){$('#testDiv').html(data);}
		});
	});
});
