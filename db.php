<?php
try {
	$con = new MongoClient("mongodb://localhost");
$db = $con->fingerprints;
$collection = $db->createCollection("csie");
   $cursor = $collection->find();
$collection_t = $db->createCollection("csie2");
   $cursor_t = $collection_t->find();
} catch (MongoConnectionException $e) {
  header( 'Location:/errorpage/404.php' ) ;
} catch (MongoException $e) {
  die('Error: ' . $e->getMessage());
}
?>