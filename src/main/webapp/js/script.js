$('document').ready(function(){
//	$('#j_idt11\\:j_idt16').on('click', function(){
		$('#j_idt26\\:j_idt31').on('click', function(){
	    // show loader
	     $('#loader').fadeIn(500);
	  });


//	  $('#j_idt11\\:msgs_container').on("DOMNodeInserted",function(){
		  $('#j_idt26\\:msgs_container').on("DOMNodeInserted",function(){
	      // hide loader
		  setTimeout(function(){	
			  $('#loader').fadeOut(500);
		  } , 500);
	  });
});
