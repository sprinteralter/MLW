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
    						.animate({opacity: 1}, 200); // плaвнo прибaвляем прoзрaчнoсть
        });
        $('.buttons').fadeIn(2000);
        $('#generate').show();
        stopSignal(refresh1, $('.step1 p'));
        refresh2=setInterval(signal2,1000);
    });
    /* end */
    /* start */
    $('.step1 p').delegate("button","click", function(){
    	$('.step1').stop().css({"background": "none"});
    	$('.step1 p').html('Шаг 1: выборка данных');
        $(this).fadeOut(500);
        $('.leftTAoverlay').fadeOut(200, function(){
             $('.leftTAloader').css("display", "none") // убирaем у мoдaльнoгo oкнa display: none;
    						.animate({opacity: 0}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
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
    	$('#overlay').fadeIn(200, function(){
            $('#selectoption').css("display", "block") // убирaем у мoдaльнoгo oкнa display: none;
   						.animate({opacity: 1}, 200); 
    	});
    });
    $('.accOption').click(function(){
    	$('#overlay').fadeOut(200, function(){
            $('#selectoption').css("display", "none") // убирaем у мoдaльнoгo oкнa display: none;
   						.animate({opacity: 0}, 200); 
    	});
    	$('.step2').stop().css({"background": "rgb(56, 255, 126)"});
    	$('.step2 p').stop().css({"color":"rgb(8, 48, 22)"});
    	$('.step2 p').text('Шаг 2: документы сгенерированы!');
    	$('.rightTA').show();
    	$('.deleteStep1').fadeOut(200);
    	refresh3 = setInterval(signal3,1000); 
    	stopSignal(refresh2, $('.step2 p'));
    	stopSignal(refresh1, $('.step1 p'));
    });
    /*start sending files to FTP*/
    $('.sendNotification').click(function(){
    	var key = $(this).attr("value");
    	$.ajax({
    		headers: {'key': key},   
    		method:"GET",
    		url:"data/connectToFtpEDI",
    		beforeSend: function(){
    			$('.rightTAoverlay').fadeIn(200,function(){
    	            $('.rightTAloader').css("display", "block") // убирaем у мoдaльнoгo oкнa display: none;
    	    						.animate({opacity: 0.5}, 200); // плaвнo прибaвляем прoзрaчнoсть
    	        });
    			$('#overlay').fadeIn(200, function(){
    	             $('#loader').css("display", "block") // убирaем у мoдaльнoгo oкнa display: none;
    	    						.animate({opacity: 1}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
    	             $('.leftTAloader').animate({opacity: 0.5}, 200);
    	        });
    			
    		},
    		success: function(data){
    			/*$("#overlay").fadeOut(200, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
        			function(){ // пoсле выпoлнения предъидущей aнимaции
    	    				$("#applymessage")
        						.css("display", "none").animate({opacity: 0, top: "50%"}, 200);
        		});
    	    		$('.rightinput').text(data.ids);
    	    		$('.rightinput').show();*/
    			
    	    },
    	    error:function(){},
    	    complete:function(){
    	    	$('#overlay').fadeOut(200, function(){
    	    		$('#loader').css("display", "none") // убирaем у мoдaльнoгo oкнa display: none;
   	    						.animate({opacity: 0}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
    	    		$('.sendConfirmation').show();
    	    		$('.leftTAloader').animate({opacity: 1}, 200);
    	    		$('.rightTAloader').animate({opacity: 1}, 200);
    	    	});
    	    	$("#generate").hide();
    	    	$("#refresh").show();
    	    	$('.step3').stop().css({"background": "rgb(56, 255, 126)"});
    	    	$('.step3 p').stop().css({"color":"rgb(8, 48, 22)"});
    	    	$('.step3 p').text('Шаг 3: Данные отправлены!');
    	    	stopSignal(refresh3, $('.step3 p'));
    	    }
    	})
    });
    
    $('.sendConfirmation').click(function(){
    	var key = $(this).attr("value");
    	$.ajax({
    		headers: {'key': key},   
    		method:"GET",
    		url:"data/connectToFtpEDI",
    		beforeSend: function(){
    			$('.rightTAoverlay').fadeIn(200,function(){
    	            $('.rightTAloader').css("display", "block") // убирaем у мoдaльнoгo oкнa display: none;
    	    						.animate({opacity: 0.5}, 200); // плaвнo прибaвляем прoзрaчнoсть
    	        });
    			$('#overlay').fadeIn(200, function(){
    	             $('#loader').css("display", "block") // убирaем у мoдaльнoгo oкнa display: none;
    	    						.animate({opacity: 1}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
    	             $('.leftTAloader').animate({opacity: 0.5}, 200);
    	        });
    			
    		},
    		success: function(data){
    			/*$("#overlay").fadeOut(200, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
        			function(){ // пoсле выпoлнения предъидущей aнимaции
    	    				$("#applymessage")
        						.css("display", "none").animate({opacity: 0, top: "50%"}, 200);
        		});
    	    		$('.rightinput').text(data.ids);
    	    		$('.rightinput').show();*/
    			
    	    },
    	    error:function(){},
    	    complete:function(){
    	    	$('#overlay').fadeOut(200, function(){
    	    		$('#loader').css("display", "none") // убирaем у мoдaльнoгo oкнa display: none;
   	    						.animate({opacity: 0}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
    	    		$('.sendConfirmation').show();
    	    		$('.leftTAloader').animate({opacity: 1}, 200);
    	    		$('.rightTAloader').animate({opacity: 1}, 200);
    	    	});
    	    	$("#generate").hide();
    	    	$("#refresh").show();
    	    	$('.step3').stop().css({"background": "rgb(56, 255, 126)"});
    	    	$('.step3 p').stop().css({"color":"rgb(8, 48, 22)"});
    	    	$('.step3 p').text('Шаг 3: Данные отправлены!');
    	    	stopSignal(refresh3, $('.step3 p'));
    	    }
    	})
    });

    $('#refresh').click(function(){
    	location.reload();
    });
    
});