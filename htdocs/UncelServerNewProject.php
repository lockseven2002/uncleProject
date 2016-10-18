<?php
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
require ("db_connect.php");

$sqlCost = "SELECT `tbl_species`.`field_species` FROM `tbl_species`";

$output = "";
$resultCost = mysqli_query($conn, $sqlCost);

if(!empty($resultCost)){
		while ($row = mysqli_fetch_array($resultCost,MYSQLI_ASSOC)) {
			if ($output != "") {$output .= ",";}
			$output .= '{"field_species":"'.$row["field_species"].'"}';
		}
		$output = '['.$output.']';
		echo ($output);
}else{
	
}
mysqli_close($conn);

?>