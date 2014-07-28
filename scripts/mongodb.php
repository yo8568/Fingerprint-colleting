<?php
//$con = new MongoClient("mongodb://admin:admin@140.118.155.213");
//echo(MongoClient::connect);
//$db = $con->fingerprints
$con = new Mongo("mongodb://localhost");

$collection = $con ->selectCollection('fingerprints','fingerprints');

?>


