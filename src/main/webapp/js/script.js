$('document').ready(function(){
		$('#j_idt27\\:j_idt32').on('click', function(){
	     $('#loader').fadeIn(500);
	  });


		  $('#j_idt27\\:msgs_container').on("DOMNodeInserted",function(){
		  setTimeout(function(){	
			  $('#loader').fadeOut(500);
		  } , 500);
	  });
});
