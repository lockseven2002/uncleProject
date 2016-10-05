<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
require ("db_connect.php");

$sqlCost = "SELECT `_date`,SUM(a.`amount`*`price`) AS Cost
			FROM `tbl_count` AS a
			INNER JOIN `tbl_details`AS b
			USING (`details_key`)
			GROUP BY a.`_date` DESC;";

$output = "";
$resultCost = mysqli_query($conn, $sqlCost);

if(!empty($resultCost)){
		while ($row = mysqli_fetch_array($resultCost,MYSQLI_ASSOC)) {
			if ($output != "") {$output .= ",";}
			$output .= '{"date":"'.$row["_date"].'",';
			$output .= '"sum":"'.$row["Cost"].'"}';
		}
		$output = '{"records":['.$output.']}';
		echo ($output);
}else{
	
}
mysqli_close($conn);

?>