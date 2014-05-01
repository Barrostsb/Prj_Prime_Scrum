
(function($){

  $('#j_idt11\\:j_idt16').on('click', function(){
    // show loader
     $('#loader').show();
    console.log('show');
  });


  $('#j_idt11\\:msgs_container').on("DOMNodeInserted",function(){
      // hide loader
      $('#loader').hide();
      console.log('hide');
  });

}(jQuery));