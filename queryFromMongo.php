<?php
require_once('db.php');
// 迭代显示文档标题
$browser=array();
$browser_unknown_string=array();
$browser_others=array();
$time=array();
$time_web=array();
$time_mobile=array();
//$cursor為所有筆資料



/*累計總共次數
* $document['2']['Android']  為記錄判斷手機與Web的OS
* $document['browser']       為記錄瀏覽器的判斷
* $browser_unknown_string    為記錄未知的遊覽器資訊
* $time_mobile
* $time_mobile
*
*
*
*/

function mobile_time($period){

  
}
foreach ($cursor as $document) {
//var_dump($document['2']);
//day
   $day_temp=substr($document['1'], 5,6);
  $month=substr($day_temp, 0,2);
  $day=substr($day_temp, 3,4);
   $day_temp=array($month,$day);

if($document['2']['Android']){
   $Android_count++;

   array_push($time_mobile, $day_temp);
  
}
elseif ($document['2']['mobile']) {
   $mobile_count++;
   $time_mobile[$day_temp]++;
}
elseif (!$document['2']['mobile']) {
   $web_count++;
  $time_web[$day_temp]++;
}
elseif ($document['2']['iPad']) {
   $iPad_count++;
   $time[$day_temp]++;
}elseif ($document['2']['iPone']) {
   $iPhone_count++;
   $time_mobile[$day_temp]++;
}
if($document['browser']){
   $pos = strpos($document['browser'], ":");
   $br_type=substr($document['browser'],0,$pos);
   array_push($browser,$br_type);


   switch (substr($document['browser'],0,$pos)) {
      case 'chrome':
         $chrome++;
         array_push($time['first-week']['chrome'],$day_temp);
         //print_r($time['first-week']['chrome']);
         break;
      case 'firefox':
         $firefox++;
          array_push($time['first-week']['firefox'],$day_temp);
         break;
      case 'safari':
         $safari++;
         array_push($time['first-week']['safari'],$day_temp);
         break;
       case '/ie/':
         $ie++;
         array_push($time['first-week']['ie'],$day_temp);
         break;
      
      default:
         array_push($browser_others, $document['browser']);
         array_push($time['first-week']['browser_others'],$day_temp);
         break;
   }
}else{
   $browser_unknown++;
   array_push($browser_unknown_string, $document['browser']);
}

} 


$content=  array(
'index' => array('web  device','iPad  device','iPhone  device','Android  device','Unknown device '),
'data'=> array(
 display_content_or_null($web_count),
 display_content_or_null($iPad_count), 
 display_content_or_null($iPhone_count),
 display_content_or_null($Android_count),display_content_or_null($mobile_count)) );

//print_r($time_mobile)."<hr />";
//print_r($time_web);


 ?>

 <html>
 <head>
   <title>資料庫內容</title>
   <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
   <meta name="viewport" content="width=device-width, initial-scale=1">
   <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
   <link href="css/bootstrap-responsive.css" rel="stylesheet">
   <link href="css/bootstrap.css" rel="stylesheet" media="screen">
<script type="text/javascript">
  var day_movlie = JSON.parse(<?php echo json_encode($time_mobile); ?>);
  console.log(day_movlie);
</script>
   <script type="text/javascript">
      $(function () {
        $('#container2').highcharts({
         title: {
          text: 'Mobile device V.S.  Web  ',
             x: -20 //center
          },
          subtitle: {
             text: '一個禮拜的CSIE資料',
             x: -20
          },
          xAxis: {
             categories: ['Day1','Day2','Day3','Day4','Day5','Day6','Day7','Day8','Day9','Day10','Day11','Day12']
          },
          yAxis: {
             title: {
              text: 'count'
           },
           plotLines: [{
              value: 0,
              width: 1,
              color: '#808080'
           }]
        },
        tooltip: {
          valueSuffix: '個'
       },
       legend: {
          layout: 'vertical',
          align: 'right',
          verticalAlign: 'middle',
          borderWidth: 0
       },
       series: [{
          name: 'Mobile',
          data: [10,15,9,17,8,22,2,6,7,3,0,1]
       }, {
          name: 'Web',
          data: [0]
       }]
    });
});


</script>
</head>
<body>
<script src="report/js/highcharts.js"></script>
<script src="report/js/modules/exporting.js"></script>
<nav class="navbar navbar-default" role="navigation">
   <div id="header" class="navbar-inner">
      <div class="container" >


      </div>
      <a href="#"><div class="brand">Fingerprint</div></a>
      <div class="btn-toolbar" ><div class="btn-group" name="sidebar-span"> <i class ="icon-th-list" style="margin-top: -1px" ></i></div></div>
   </div>

</nav>
<div class="container-fluid" >
   <div class="row-fluid">

    <!--  側邊欄  -->
    <div class="span2" id="sidebar">
       <ul class="nav nav-list">
          <li class="active"><i class = "icon-home"></i></li>
          <li class="nav-header">Report</li>

       </ul>
    </div>

    <!--   內容  -->
    <div class="span10">
      <div name ="device-count" id="device-count" >
       <div class="page-header">
          <h1>Device count</h1>
       </div>
       <div class="pager">
          <table class="table">

            <tr>
               <th>裝置</th>
               <th>數量</th>
            </tr>
            <?php  for ($i=0; $i<count($content['index']) ; $i++) { ?>

            <tr>
               <th><?php echo $content['index'][$i]; ?></th>
               <td><?php echo $content['data'][$i];?></td>
            </tr>
            <?php }  ?>
         </div>
      </table>
<table class="table">
<tr>
<th>瀏覽器</th>
<th>chrome</th>   
<th>safari</th>
<th>IE</th>
<th>firefox</th>
<th>Unknown</th>
</tr>
<tr>
<th>CSIE</th>
<td><?php echo $chrome; ?></td>
<td><?php echo $safari; ?></td>
<td><?php echo $ie; ?></td>
<td><?php echo $firefox; ?></td>
<td><?php echo $browser_unknown ;?></td>
</tr>


</table>
      <hr />

     <?php  

    // print_r($browser);
     
    // echo "$browser_unknown";

      function display_content_or_null($data){

         if(is_null($data)){
          $a = 'null';
       }else{$a = $data;}
       return $a;
    }


        
    ?>
<div id="container2"></div>

 </div><!-- tables ending  -->

</div>

</div>

</div>
<footer class="footer">
<div class="container">
 <p>NTUST CSIE RESERCH</p>
 <p>Fingerprint REPORT</p>

 <ul class="footer-links">
  <li></li>
</ul>
</div>
</footer>

</body>

</html>

