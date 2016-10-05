<?php
$sql = "SELECT DISTINCT `tbl_count`.`_createdate` FROM `tbl_count` ORDER BY `tbl_count`.`_createdate` DESC;"; 
$response = array();
require ("db_connect.php");
$result = mysqli_query($conn, $sql);
if (!empty($result)) {
        if (mysqli_num_rows($result) > 0) {
			 $response ["products"] = array();
    while ($row = mysqli_fetch_array($result)) {
		$beforeCompare = $row["_createdate"];
		//$afterCompare = "";
		$product = array();
		//濾重複日期
		//if($beforeCompare != $afterCompare){
		$product["_createdate"] = $beforeCompare;
        array_push($response["products"], $product);
		//}
	}
		// success
		$response["success"] = 1;
		// echoing JSON response and encode for Tw Lanuage
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