$(document).ready(function(){
    
	
    
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
    /*-------------AJAX------------*/  
    /*------------------tabs generation------------------*/
    
});

