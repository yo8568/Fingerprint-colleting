 var urls='http://140.118.155.213/m10215040/public_html/PhpProject2/dataset.php';
           
      function collection(){
        console.log('yes');
        Fingerprint.init();
       Fingerprint.events.add(Fingerprint.initFlash,['/scripts/Fonts.swf'])
        .add(Fingerprint.updateRTT,[10,urls])
        .add(Fingerprint.updatePlugins,[])
        .add(Fingerprint.updateCSSFonts,[cssFontList])
        .run();
        statusTimeout = setTimeout(onFinish,5500);
        setTimeout(function(){Fingerprint.onFinish(onFinish);},500);

        /*  Fingerprint.onFinish(Fingerprint.submit(urls,'error','success'));*/
        console.log(Fingerprint.getFingerprint());
     };
   
   function onFinish()
{
   
    clearTimeout(statusTimeout);
    Fingerprint.onFinish = function() {};
    Fingerprint.status.onChange = function() {};

    /*$('#status').text('Sending data...');*/
    Fingerprint.submit(
  urls,
  function error()
  {
      Fingerprint.status.onChange = updateStatus;
      console.log('error');
     /* $('#loading').fadeToggle('fast',function(){
    $('#error').fadeToggle('fast');
      });*/
      /* show error msg (highlight) and return to start page here */
      
  },
  function success()
  {
      /*$('#cookie').attr('src','/img/cookie_eaten.gif');
      $('#loading').fadeToggle('fast',function(){
    $('#thanks').fadeToggle('fast');
      });*/
      /* show thank you message here */
      console.log('success');
  }
    );
}

function updateStatus()
{
      var status = Fingerprint.status.get();
  /*$('#status').text(status); */
    
}
