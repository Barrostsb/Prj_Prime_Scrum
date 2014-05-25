$('document').ready(function(){
//	$('#j_idt11\\:j_idt16').on('click', function(){
		$('#j_idt27\\:j_idt32').on('click', function(){
	    // show loader
	     $('#loader').fadeIn(500);
	  });


//	  $('#j_idt11\\:msgs_container').on("DOMNodeInserted",function(){
		  $('#j_idt27\\:msgs_container').on("DOMNodeInserted",function(){
	      // hide loader
		  setTimeout(function(){	
			  $('#loader').fadeOut(500);
		  } , 500);
	  });
});
