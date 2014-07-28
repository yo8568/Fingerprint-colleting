<?php
$con = new MongoClient("mongodb://localhost");
$db = $con->fingerprints;
$collection = $db->createCollection("mydb");
?>


