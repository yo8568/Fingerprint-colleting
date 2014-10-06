<?php 
//Allow to access the assigned domain data
header("Access-Control-Allow-Origin: http://kenchang.comze.com ");
header("Access-Control-Allow-Headers: Origin, X-Requested-With, Content-Type, Accept");

//Connect mongoDB database

$con = new MongoClient("mongodb://localhost");
$db = $con->fingerprints;
$collection = $db->createCollection("test");


//deal with Ajax request content and add some attribute into DB
$request_body = file_get_contents('php://input');

/* php://input 是個可以訪問請求的原始資料的唯讀流
Coentent-Type僅在取值為application/x-www-data-urlencoded和multipart/form-data兩種情況下，PHP才會將http請求資料包中相應的資料填入全域變數$_POST 
php://input資料總是跟$HTTP_RAW_POST_DATA相同，但是php://input比$HTTP_RAW_POST_DATA更湊效，且不需要特殊設置php.ini 
php5.3版本以上的用法
*/

//judge whether is mobile device or not
$iPod = stripos($_SERVER['HTTP_USER_AGENT'],"iPod");
$iPhone = stripos($_SERVER['HTTP_USER_AGENT'],"iPhone");
$iPad = stripos($_SERVER['HTTP_USER_AGENT'],"iPad");
if(stripos($_SERVER['HTTP_USER_AGENT'],"Android") && stripos($_SERVER['HTTP_USER_AGENT'],"mobile")){
    $Android = true;
}else if(stripos($_SERVER['HTTP_USER_AGENT'],"Android")){
    $Android = false;
    $AndroidTablet = true;
}else{
    $Android = false;
    $AndroidTablet = false;
}
$webOS = stripos($_SERVER['HTTP_USER_AGENT'],"webOS");
$BlackBerry = stripos($_SERVER['HTTP_USER_AGENT'],"BlackBerry");
$RimTablet= stripos($_SERVER['HTTP_USER_AGENT'],"RIM Tablet");

$data = json_decode($request_body,true);

$ip=array('ip'=>$_SERVER['REMOTE_ADDR']);
array_push($data,$ip);
date_default_timezone_set('Asia/Taipei');   
$date = date("Y:m:d:h:i:s");
array_push($data,$date);//丟時間進去


//Filter device type
if( $iPod || $iPhone ){
    $ipod_d=array('iPod'=>$iPod);
    $iphone_d=array('iPhone'=>$iPhone);
    array_push($data,$ipod_d);
    array_push($data,$iphone_d);
}else if($iPad){
 $ipad_d=array('iPad'=>$iPad);
 array_push($data,$ipad_d);
}else if($Android){
    $android_d=array('Android'=>$Android);
    array_push($data,$android_d);
}else if($AndroidTablet){
    $androidTablet_d=array('AndroidTablet'=>$AndroidTablet);
    array_push($data,$androidTablet_d);
}else if($webOS){
    $webOS_d=array('webOS'=>$webOS);
    array_push($data,$webOS_d);
}else if($BlackBerry){
    $BlackBerry_d=array('BlackBerry'=>$BlackBerry);
    array_push($data,$BlackBerry_d);
}else if($RimTablet){
    $RimTablet_d=array('RimTablet'=>$RimTablet);
    array_push($data,$RimTablet_d);
}else{

    $no_moblie=false;
    $no_moblie_d=array('unknown'=>$no_moblie);
    array_push($data,$no_moblie_d);
}





//store and filter the elements in variable $_SERVER  
if(!function_exists('getallheaders'))
{
   function getallheaders() 
   {
      foreach($_SERVER as $name => $value)
      {
         if(substr($name, 0, 5) == 'HTTP_')
         {
            $headers[str_replace(' ', '-', ucwords(strtolower(str_replace('_', ' ', substr($name, 5)))))] = $value;
        }
    }
    return $headers;
}
}
$count=0;

foreach (getallheaders() as $name => $value) 
{
    if(preg_match("/^(Accept|Accept-Encoding|Accept-Language|Cookie|Referer)/i", trim($name)))
    {
        $header1[$count]=array($name => $value);
        $count++;
    }
    $header1[$count]=array($name => $value);
   // array_push($data, $header1);
}



array_push($data, $header1);
$collection->insert($data); 



?>

