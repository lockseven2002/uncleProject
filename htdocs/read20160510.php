<!DOCTYPE html><!--宣告文件類型-->
<html lang="zh-tw"><!--指定網頁使用的語言-->
    <head>
        <meta charset="utf-8"><!--指定網頁編碼-->
        <title>學生資料管理系統</title><!--文件標題-->
    </head>
    <body><!--文件主體-->
	<?php
//sql_Lanuage
$sql="SELECT *FROM products pid =1 ";
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
			//A data fetch for array  
            $result = mysqli_fetch_array($result,MYSQLI_ASSOC);
			//echo $result["name"];
            $product = array();
            $product["pid"] = $result["pid"];
            $product["name"] = $result["name"];
            $product["price"] = $result["price"];
            $product["description"] = $result["description"];
            $product["created_at"] = $result["created_at"];
            $product["updated_at"] = $result["updated_at"];
            // success
            $response["success"] = 1;
 
            // user node
            $response["product"] = array();
 
            array_push($response["product"], $product);
 
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
	</body>
</html>
