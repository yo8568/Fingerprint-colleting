     1.��� <body> tag ��
   
    <script src="http://140.118.155.213/m10215040/public_html/test/scripts/ext/swfobject.js" type="text/javascript"> </script>
    <script src="http://140.118.155.213/m10215040/public_html/test/scripts/ext/jquery-1.6.4.min.js" type="text/javascript" > </script>> </script>
    <script src="http://140.118.155.213/m10215040/public_html/test/scripts/ext/jquery.json-2.3.min.js" type="text/javascript" > </script>
    <script src="http://140.118.155.213/m10215040/public_html/test/scripts/Fingerprint.js" type="text/javascript" > </script>
    <script src="http://140.118.155.213/m10215040/public_html/test/scripts/collect.js" type="text/javascript"  > </script>

     2. ��database collection
     
     $con = new MongoClient("mongodb://localhost");
     $db = $con->fingerprints;
     $collection = $db->createCollection("test"); 
     
     3.��Fonts.swf��b���������A���W
       ����| collect.js   Fingerprint.events.add(Fingerprint.initFlash,['Fonts.swf���|'])

     
     
    
