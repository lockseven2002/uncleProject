<?php
//sql_Lanuage
$sql="SELECT *FROM products";
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
        $product["pid"] = $row["pid"];
        $product["name"] = $row["name"];
        $product["price"] = $row["price"];
        $product["created_at"] = $row["created_at"];
        $product["updated_at"] = $row["updated_at"];
 
        // push single product into final response array
        array_push($response["products"], $product);
		//array_push($response["ggg"], $product); //error
    }
			// success
		$response["success"] = 1;
 
		// echoing JSON response
			echo json_encode($response);
			
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