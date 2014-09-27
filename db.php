<?php
$con = new MongoClient("mongodb://140.118.155.213");
$db = $con->fingerprints;
$collection = $db->createCollection("csie");
   $cursor = $collection->find();
$collection_t = $db->createCollection("test");
   $cursor_t = $collection_t->find();




?>