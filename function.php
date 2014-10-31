<?php
function online_standard_measures(){
 
}
function online_count_error($L,$Lc){
/*the error in broswer instance count "e"
e= (abs(Lc)-abs(L))/abs(L)
L is the set of labels in the data set and Lc is the set of labels known to the classifier after training and testing .
Lc will contain those labels generated in the classification process, that are not in L ,  and might be large than L.
*/
$e= (abs($Lc)-abs($L))/abs($L);

return $e;
}

?>