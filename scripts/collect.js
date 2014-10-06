
        var urls='http://140.118.155.213/m10215040/public_html/test/csie.php';
         
    do {
      
      Fingerprint.init();
      Fingerprint.events.add(Fingerprint.initFlash,['Fonts.swf'])
      .add(Fingerprint.updateRTT,[10,urls])
      .add(Fingerprint.updatePlugins,[])
      .add(Fingerprint.updateCSSFonts,[cssFontList])
      .run();
      statusTimeout = setTimeout(onFinish,15000);
      setTimeout(function(){Fingerprint.onFinish(onFinish);},500);
      Fingerprint.getUID();
      /*  Fingerprint.onFinish(Fingerprint.submit(urls,'error','success'));*/
      
      var i =0;
}while(i);
console.log(Fingerprint.status.get());
 

 function show(){
   var jsonString = JSON.stringify(Fingerprint.getFingerprint());
 
  /* ouput the JSON string to html */
 // document.write("<h1>Your fingerprint</h1> <pre>"+jsonString+"</pre>"+"<br />");
var newArr = JSON.parse(jsonString);
while (newArr.length > 0) {
  document.write(newArr.pop() + "<br/>");
    };
 };



 function onFinish()
{
 
  clearTimeout(statusTimeout);
  Fingerprint.onFinish = function() {};
  Fingerprint.status.onChange = function() {};

  /*$('#status').text('Sending data...');*/
  Fingerprint.submit(urls,

    function error()
{
    Fingerprint.status.onChange = updateStatus;
    console.log('error');
},
   function success()
{
    console.log('success');
}
  );

}

function updateStatus()
{
    var status = Fingerprint.status.get();
/*$('#status').text(status); */
  
}


