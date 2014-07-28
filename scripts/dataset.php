<!--<html>
  <head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8" />
    <link href="styles/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      #dataset th { font-weight: normal; color: #ff7e00; border-bottom: 1px solid #ff7e03; }
      #dataset th.label { color: #007cc3; }
      #dataset td, #dataset th { border-left: 1px solid #ff7e00; padding: 0.5em; text-align: center; margin-left: -1px; }
    </style>
    <script src="http://140.118.155.213/mm2/fp/scripts/ext/jquery-1.6.4.min.js" type="text/javascript"></script>
    <script src="http://140.118.155.213/mm2/fp/scripts/ext/jquery.json-2.3.min.js" type="text/javascript"></script>
    <script src="http://140.118.155.213/mm2/fp/scripts/dataset.js" type="text/javascript"></script>
    <script src="http://140.118.155.213/mm2/fp/scripts/Fingerprint.js" type="text/javascript">
    
    </script>
    <title>Browser Fingerprinting</title>
  </head>
<body>
  
  <button onclick="http://140.118.155.213/mm2/fp/dataset.html" class="preview">Reload</button>
  <table id="dataset" style="margin:auto" cellspacing=0>
   <tr class="heading">
    <th>User &nbsp; agent name</th>
    <th>User &nbsp; agent version</th>
    <th>IP</th>
    <th>#Fonts</th>
    <th>#Plugins</th>
    <th>Resolution</th>
    <th>Time&nbsp;zone</th>
    <th>Timestamp</th>
    <th class="label">Label</th>
    </tr>
  </table>
  <p id="demo">
      
      
      
      
  </p>
  <script>
      console.log(Fingerprint.getFingerprint());
       console.log(Fingerprint.status);
Fingerprint.generateDOM();
  </script>
</body>
</html>--!>
<?php 
require_once 'mongodb.php';


$profile=$_POST;

var_dump($profile);   
$json = json_encode($profile);
 file_put_contents('your_data.txt', $json);
?>