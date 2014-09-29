<?php
try {
	$con = new MongoClient("mongodb://localhost");
$db = $con->fingerprints;
$collection = $db->createCollection("csie");
   $cursor = $collection->find();
$collection_t = $db->createCollection("test");
   $cursor_t = $collection_t->find();
} catch (MongoConnectionException $e) {
  die('Error connecting to MongoDB server');
} catch (MongoException $e) {
  die('Error: ' . $e->getMessage());
}
?>