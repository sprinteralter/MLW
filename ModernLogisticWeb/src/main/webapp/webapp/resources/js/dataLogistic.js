/**
 * @author Rosteach R.O.
 */

$(document).ready(function(){
    /*global parameters*/
	var formsCollection = [];
	var formsCollectionSize = formsCollection.length;
	/*end*/
	/*$('.testAjax').click(
		function(){
			$('#excelForm').html(formsCollection[0]);
			$("#overlay").fadeIn(200,function(){
		         $("#excelForm")
		         .css("display", "block") 
		         .animate({opacity: 1, top: "50%"}, 200);
		     });
		}	
	);*/
	/* start */
    $('.iconImg').hover(
        function(){
	       $(this).stop().animate({marginRight:"30%"},200);
        }
        ,             
        function(){
            $(this).stop().animate({marginRight:"0"},200);                             
        }
    );    
    /* end */
    /* start for right bar navigation*/
    $('body').on("click",".showExcelForm",
    	function(){
    		
    		var thisvalue = this.parentNode.getElementsByTagName("td")[0].innerHTML;

    		$(this).parent().stop().css({"backgroundColor": "silver","color":"white"});
    		$("#overlay").fadeIn(200,function(){
	    	 
    			$('#excelForm').html(formsCollection[thisvalue-1]);
    			$("#excelForm")
    			.css("display", "block") 
    			.animate({opacity: 1, top: "50%"}, 200);
    		});
    });
    $('body').on("click",'.cancelbut',
        	function(){
    			$("#overlay").fadeOut(200,function(){$("#excelForm").hide();});
    			$(".showExcelForm").parent().stop().css({"backgroundColor": "whitesmoke","color":"black"});
    });
        
    $('.rightBarIcon').click(
            function(){
                var checkName = $(this).attr("name");
                var request = $(this).attr("id");
                if(checkName==undefined){
                	if(request=="allreports"){
                		$('.rightBarIcon').stop().css({"backgroundColor": "whitesmoke"});
                		$('.rightBarIcon').removeAttr("name");
                        $('.Row').detach();
                        $(this).stop().css({"backgroundColor": "silver"});
	                    $(this).attr({name: "switched"});
	                    $('#rightBarReports').fadeIn(1000);
	                    $('.navReports input').focus();
	                    //clear our array
	                    formsCollection = [];
	                    $.getJSON("getReports",request,function(data){
	                		var row = "";
	                		for(var i=0;i<=data.length;i++){
	                			row="<tr class='Row' id='"+data[i].type+"'>"+
	                        	"<td>"+data[i].id+"</td>"+
	                        	"<td class='showExcelForm'>"+data[i].name+"</td>"+
	                        	"<td>"+data[i].type+"</td>"+
	                        	"<td>"+data[i].database+"</td>"+
	                        	"</tr>";
	                			$('.rightBarTable').append(row);
	                			formsCollection.push(data[i].form);
	                		}
	                	});
                	}
                	else
                	{	
                		//$('#allreports').stop().css({"backgroundColor": "whitesmoke"});
                		var checkAll = $('#allreports').attr("name");
                		
                		//$('.Row').detach();
	   		            if(checkAll=="switched"){
	   		            	$('#allreports').stop().css({"backgroundColor": "whitesmoke"});
	                		$('#allreports').removeAttr("name");
	                		$('.Row').detach();
                			$(this).stop().css({"backgroundColor": "silver"});
		                    $(this).attr({name: "switched"});
		                    $('#rightBarReports').fadeIn(1000);
		                    $('.navReports input').focus();
		                    //clear our array
		                    formsCollection = [];
		                    $.getJSON("getReports",request,function(data){
		                		var row = "";
		                		for(var i=0;i<=data.length;i++){
		                			row="<tr class='Row' id='"+data[i].type+"'>"+
		                        	"<td>"+data[i].id+"</td>"+
		                        	"<td class='showExcelForm'>"+data[i].name+"</td>"+
		                        	"<td>"+data[i].type+"</td>"+
		                        	"<td>"+data[i].database+"</td>"+
		                        	"</tr>";
		                			$('.rightBarTable').append(row);
		                			formsCollection.push(data[i].form);
		                		}
		                	});
	   		            }
	   		            else{
	   		            	
	   		            	$(this).stop().css({"backgroundColor": "silver"});
		                    $(this).attr({name: "switched"});
		                    $('#rightBarReports').fadeIn(1000);
		                    $('.navReports input').focus();
		                    //clear our array
		                    formsCollection = [];
		                    $.getJSON("getReports",request,function(data){
		                		var row = "";
		                		for(var i=0;i<=data.length;i++){
		                			row="<tr class='Row' id='"+data[i].type+"'>"+
		                        	"<td>"+data[i].id+"</td>"+
		                        	"<td class='showExcelForm'>"+data[i].name+"</td>"+
		                        	"<td>"+data[i].type+"</td>"+
		                        	"<td>"+data[i].database+"</td>"+
		                        	"</tr>";
		                			$('.rightBarTable').append(row);
		                			formsCollection.push(data[i].form);
		                		}
		                	});
	   		            }
                	}
                }else if(checkName=="switched"){
                	if(request=="excel"){
                		$('.Row').not("#word,#pdf,#txt,#xml,#json").detach();
                	}
                	else if(request=="word"){
                		$('.Row').not("#excel,#pdf,#txt,#xml,#json").detach();
                	}
                	else if(request=="pdf"){
                		$('.Row').not("#excel,#word,#txt,#xml,#json").detach();
                	}
                	else if(request=="txt"){
                		$('.Row').not("#excel,#pdf,#word,#xml,#json").detach();
                	}
                	else if(request=="xml"){
                		$('.Row').not("#excel,#pdf,#txt,#word,#json").detach();
                	}
                	else if(request=="json"){
                		$('.Row').not("#excel,#pdf,#txt,#xml,#word").detach();
                	}
                	else if(request=="allreports"){
                		$('.Row').not().detach();
                	}
                	$(this).stop().css({"backgroundColor": "whitesmoke"});
                    $(this).removeAttr("name");
                }
            }
    ); 
    $('.fadeOutBarReports').click(
        function(){
            $('#rightBarReports').fadeOut(500);
            $('.rightBarIcon').stop().css({"backgroundColor": "whitesmoke"});
            $('.rightBarIcon').removeAttr("name");
            $('.Row').detach();
        }        
    );
    /* end */
    /*form buttons*/
   
    /*end*/
    
});