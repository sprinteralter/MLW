$(document).ready(function(){
   
	
	
	//var file = event.dataTransfer.files[0];
    /*
	if (file.size > maxFileSize) {
	    dropZone.text('Файл слишком большой!');
	    dropZone.addClass('error');
	    return false;
	};*/
	
   /* $('.delegateUploadButtLeft').on('click','.uplFile',
    	function(event){
    		var request = event.target.files;
    		
    	}
    );*/
	/* start */
    $('body').on('change','.files',
    	function(e){
	    	var filesCollection = [];
	        var files = e.target.files;
	            
	        $.each(files, function(i,file){
	            filesCollection.push("- "+file.name+"\n");    
	        });
	        $(".textL").val(filesCollection);
    });
    /* end */
    
    /* start */
    $(".butt:last").click(
    	function(){
        	$("textarea").val("");
            $(".files").val("");
        }      
    );
    /* end */
    /* start */
    $(".butt").hover(
        function(){
	       $(this).stop().css({"border-color": "#8a2820"});
        }
        ,             
        function(){
            $(this).stop().css({"border-color": "whitesmoke"});
        }
    );
    /* end */
    /*start*/
    $(".infoBarIcon").click(
        function(){
    	    $(".infoBarLogin").toggle();
        }
    );
    /*end*/
    /*login info*/
    
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
    /*end*/
    
    /*-------------AJAX------------*/  
    /*$('.logout').click(function(){
    	$.ajax({
    		url: 'logout',
    		type: 'get',
    		success: function(data){
    			
    	    }
    	});
    })*/
    /*------------------tabs generation------------------*/
   /* $.ajax({
		url: 'getData/databind',
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
	});*/
});

