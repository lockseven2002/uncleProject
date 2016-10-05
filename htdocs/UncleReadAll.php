<?php
//header("Content-Type:text/html; charset=utf-8");
//sql_Lanuage
/*
$sql="SELECT `tbl_count`.`_createdate`,`detailsname`,`tbl_count`.`amount` FROM `tbl_species`,`tbl_details`,`tbl_count` 
	  WHERE `tbl_details`.`species_key`=".$_GET["id"]." "; 
$sql.="AND `tbl_details`.`details_key`=`tbl_count`.`details_key` 
       AND `tbl_details`.`species_key`=`tbl_species`.`id`
	   AND `tbl_count`.`_createdate` LIKE '".$_GET["_createdate"]."%'";
*/
$sql = "SELECT `tbl_count`.`_createdate`,`detailsname`,`tbl_count`.`amount` FROM `tbl_species`,`tbl_details`,`tbl_count`  
		WHERE `tbl_details`.`species_key`='".$_GET['id']."' 
        AND `tbl_details`.`details_key`=`tbl_count`.`details_key` 
        AND `tbl_details`.`species_key`=`tbl_species`.`id`
        AND `tbl_count`.`_createdate`='".$_GET['_createdate']."';";

// array for JSON response
$response = array();
 // include db connect class
require ("db_connect.php");

$result = mysqli_query($conn, $sql);
//DB table row count
//echo mysqli_num_rows($result);
// check for empty 
    if (!empty($result)) {
        // check for empty result
        if (mysqli_num_rows($result) > 0) {
			//"ggg" is key,and must be first assign
			 $response ["products"] = array();
			 //$response = array();  //error
    while ($row = mysqli_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["detailsname"]=$row["detailsname"];
        $product["amount"]=$row["amount"];
		$product["_createdate"]=$row["_createdate"];
		//$product["_Key"]=$row["_Key"];
        // push single product into final response array
        array_push($response["products"], $product);
		//array_push($response["ggg"], $product); //error
    }
			// success
		$response["success"] = 1;
 
		// echoing JSON response and encode for Tw Lanuage and response to app
			echo json_encode($response,JSON_UNESCAPED_UNICODE);
        } else {
            // no product found
            $response["success"] = 0;
            $response["message"] = "No product found";
 
            // echo no users JSON
            echo json_encode($response);
        }
    } else {
        // no product found
        $response["success"] = 0;
        $response["message"] = "No product found";
 
        // echo no users JSON
        echo json_encode($response);	
    }
	mysqli_close($conn);
	?>