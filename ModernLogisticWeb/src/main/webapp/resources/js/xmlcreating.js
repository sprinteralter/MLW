/**
 * @author Rosteach
 */
$(document).ready(function(){
    var refresh1 = setInterval(signal1,1000);
    var refresh2;
    var refresh3;
    /* start */
    $('.takeData').click(function(){
        $('.step1').stop().css({"background": "rgb(56, 255, 126)","color":"rgb(8, 48, 22)"});
        $('.step1 p').stop().html('Шаг 1: данные в процессе обработки!<button class="deleteStep1">X</button>').css({"color":"rgb(8, 48, 22)"});
        $('.deleteStep1').fadeIn(200);
        $('.leftTAoverlay').fadeIn(200,function(){
            $('.leftTAloader').css("display", "block") // убирaем у мoдaльнoгo oкнa display: none;
    						.animate({opacity: 1, top: "50%"}, 2000); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
        });
        $('.buttons').fadeIn(2000);
        refresh2=setInterval(signal2,1000);
        refresh1 = clearInterval(refresh1);
        stopSignal(refresh1, $('.step1 p'));
    });
    /* end */
    /* start */
    $('.step1 p').delegate("button","click", function(){
    	$('.step1').stop().css({"background": "none"});
    	$('.step1 p').html('Шаг 1: выборка данных');
        $(this).fadeOut(500);
        $('.leftTAoverlay').fadeOut(200, function(){
             $('.leftTAloader').css("display", "none") // убирaем у мoдaльнoгo oкнa display: none;
    						.animate({opacity: 0, top: "0%"}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
        });
        $('.buttons').fadeOut(200);
        setInterval(signal1,1000);
        stopSignal(refresh2,$('.step2 p'));
    });
    /*$('.deleteStep1').hover(function(){
    	$(this).stop().css({"color":"red"});
    });*/
    /* end */
    $('.infoBarIcon').click(function(){
        $('#overlay').fadeIn(200, function(){
            $('#loginInfo').show(200);        
        });    
    });
    $('.cancel').click(function(){
        $('#loginInfo').hide(200,function(){
            $('#overlay').fadeOut(200)
        });        
    });
    
    function signal1(){
    	var color = $('.step1 p').css('color');
    	if(color=="rgb(158, 73, 73)"){
    		$('.step1 p').stop().css({"color":"rgb(128, 128, 128)","borderColor":"rgb(128, 128, 128)"});
    	} else {
    		$('.step1 p').stop().css({"color":"rgb(158, 73, 73)","borderColor":"rgb(158, 73, 73)"});
    	}
    };
    
    function signal2(){
    	var color = $('.step2 p').css('color');
    	if(color=="rgb(158, 73, 73)"){
    		$('.step2 p').stop().css({"color":"rgb(128, 128, 128)","borderColor":"rgb(128, 128, 128)"});
    	} else {
    		$('.step2 p').stop().css({"color":"rgb(158, 73, 73)","borderColor":"rgb(158, 73, 73)"});
    	}
    };
    
    function signal3(){
    	var color = $('.step3 p').css('color');
    	if(color=="rgb(158, 73, 73)"){
    		$('.step3 p').stop().css({"color":"rgb(128, 128, 128)","borderColor":"rgb(128, 128, 128)"});
    	} else {
    		$('.step3 p').stop().css({"color":"rgb(158, 73, 73)","borderColor":"rgb(158, 73, 73)"});
    	}
    };
    
    function stopSignal(value,tag){
    	clearInterval(value);
    	$(tag).stop().css({"color":"rgb(128, 128, 128)","borderColor":"rgb(128, 128, 128)"});
    }
    $('#generate').click(function(){
    	$('.step2').stop().css({"background": "rgb(56, 255, 126)"});
    	$('.step2 p').stop().css({"color":"rgb(8, 48, 22)"});
    	$('.step2 p').text('Шаг 2: документы сгенерированы!');
    	$('.rightTA').show();
    	$('.deleteStep1').fadeOut(200);
    	refresh3 = setInterval(signal3,1000); 
    	stopSignal(refresh2, $('.step2 p'));
    });
});