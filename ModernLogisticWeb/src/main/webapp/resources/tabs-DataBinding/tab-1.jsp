<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<html>
<head>
	<title>Заявки от клиентов</title>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	
	<div id="content">
		<div class="left">
			<label>База данных источник:</label>
			<select class="leftselect" name="selectFrom" required="required">
				<option selected>default</option>
				<option>Alter</option>
				<option>Sprinter</option>
			</select>
			<div class="leftinput">
				<input class="linput" name="inputIds" placeholder="Входные параметры" required="required">
			</div>
		</div>
		<div class="central">
			<button class="sendbutton" >Отправить</button>
		</div>
		<div class="right">
			<label>База данных приемник:</label>
			<select class="rightselect" name="selectIn">
				<option selected>default</option>
				<option>Alter</option>
				<option>Sprinter</option>
			</select>
			<div class="rightinput">
			</div>
		</div>
	</div>
	<div id="loader">
	   <img src="resources/images/cloud_loading_256.gif" alt="Loader" width=150px height=150px;/>
	</div>
	<div id="applymessage">
	   	<p class="applMess"></p>
	   	<p class="applAjaxMess"></p>
	   	<!-- <form id="form" action="databind" method="get"> -->
		   	<input class="name" type="text" name="name" placeholder="login" required="required" />
			<input class="password" type="password" name="password" placeholder="password" required="required" />
		<!-- </form> -->
	   	<button class="applMessApply"><!-- type="submit" form="form" -->Подтвердить</button><button class="applMessCancel">Отменить</button>
	</div>
	<div id="overlay"></div>
    <script>
        $(document).ready(function(){
        	        	
        	/* var options = { 
				        beforeSend: function(){
				        	alert($(".rightselect").val());
					        $("#applymessage").css("display","none");
				    	},
						uploadProgress: function(){
							alert($(".rightselect").val());
							$("#loader") 
			        		.css("display", "block") // убирaем у мoдaльнoгo oкнa display: none;
			        		.animate({opacity: 1, top: "50%"}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
				    	},
				    	success: function(){
				    		alert($(".rightselect").val());
				   	    },
				   	    complete: function(response){	
				   	    	$("#overlay").fadeOut(200, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
				        			function(){ // пoсле выпoлнения предъидущей aнимaции
				   	    				$("#loader")
				        						.css("display", "none").animate({opacity: 0, top: "50%"}, 200);
				        	});
				   	    	$('.rightinput').text(response.toString()).show();
				   	    },
				    	error: function(){
				    		alert("Error!");
				    	}
			    	}; 
			    		 
			 $("#form").ajaxForm(options);
        	 */
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
            $('.sendbutton').click(function(){
            	$("#overlay").fadeIn(200, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
	        		function(){ // пoсле выпoлнения предъидущей aнимaции
	        			$("#applymessage")
	        				.css("display", "block") // убирaем у мoдaльнoгo oкнa display: none;
	        				.animate({opacity: 1, top: "50%"}, 200); // плaвнo прибaвляем прoзрaчнoсть oднoвременнo сo съезжaнием вниз
	        			$('.applMess').html("Подтвердить импорт данных!");
	        	});
            });
            $('.applMessApply').click(function(){
       
            	var request = {
            		name: $('.name').val(),
            		password: $('.password').val(),
            		databaseFrom: $('.leftselect').val(),
            		databaseTo: $(".rightselect").val(),
            		ids: $('.linput').val()
            	};
            	alert(JSON.stringify(request));
            	$.ajax({
            		url: 'data/databind',
            		type: 'post',
            		contentType: "application/json; charset=utf-8",
            		dataType: 'json',
            		success: function(data){
            			$("#overlay").fadeOut(200, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
		        			function(){ // пoсле выпoлнения предъидущей aнимaции
		   	    				$("#applymessage")
		        						.css("display", "none").animate({opacity: 0, top: "50%"}, 200);
		        		});
		   	    		$('.rightinput').text(data.ids);
		   	    		$('.rightinput').show();
		   	    	},
		   	    	data: JSON.stringify(request)
            	});
            });
            $('.applMessCancel').click(function(){
            	$("#overlay").fadeOut(200, // снaчaлa плaвнo пoкaзывaем темную пoдлoжку
	        		function(){ // пoсле выпoлнения предъидущей aнимaции
	   	    			$("#applymessage")
	        				.css("display", "none").animate({opacity: 0, top: "50%"}, 200);
	        	});
            });
        });
    </script>
</body>
</html>