$(document).ready(function(){
	$('.leftselect').change(function(){
		$('.leftinput').show();
	});
	$('.linput').keyup(function(){
		var check = $(this).val();
		if(check.length>5){
			$('.sendbutton').fadeIn(1000);
		}
		else
		{
			return false;
		}
	});
	
});

