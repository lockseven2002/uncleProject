<?php
$today = date("Y-m-d");
$sql = "UPDATE `tbl_count`,`tbl_details`,`tbl_species` 
		SET `tbl_count`.`amount`='".$_GET['updateamount']."',`tbl_count`.`_date`='".$today."'
		WHERE `tbl_count`.`_createdate`='".$_GET['_createdate']."'
		AND `tbl_details`.`species_key`='".$_GET['id']."'
		AND `tbl_details`.`details_key`=`tbl_count`.`details_key` 
		AND `tbl_details`.`species_key`=`tbl_species`.`id`
		And `tbl_details`.`detailsName`='".$_GET['item']."'
		AND `tbl_count`.`amount`='".$_GET['count']."';";
//echo $sql;

$response = array();
require ("db_connect.php");
$result = mysqli_query($conn, $sql);
$response["success"] = 1;
$response["message"] = "success";
echo json_encode($response);
mysqli_close($conn);

?>