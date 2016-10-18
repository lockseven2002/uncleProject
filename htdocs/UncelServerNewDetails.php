<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
require ("db_connect.php");

$sqlCost = "SELECT `detailsName`,`price` FROM `tbl_details`";

$output = "";
$resultCost = mysqli_query($conn, $sqlCost);

if(!empty($resultCost)){
		while ($row = mysqli_fetch_array($resultCost,MYSQLI_ASSOC)) {
			if ($output != "") {$output .= ",";}
			$output .= '{"detailsName":"'.$row["detailsName"].'",';
			$output .= '"price":"'.$row["price"].'"}';
		}
		$output = '['.$output.']';
		echo ($output);
}else{
	
}
mysqli_close($conn);

?>