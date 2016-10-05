<?php

if(isset($_POST['products'])){
	$json = $_POST['products'];
	$date = date("Y-m-d");
	$jsonArray = json_decode($json,true);	//jsonArray
	$countIDNum = count($jsonArray);
	$countIDXNum = count($jsonArray['countID0']);

/*
$i = 1;
$j = 1;
echo $countIDNum;
echo $countIDXNum."<br>";
echo $jsonArray['countID'.$i][$j];
*/
	
	$response = array();
	$countAndIdArray = array();
	require ("db_connect.php");
	
	for($i=0;$i<$countIDNum;$i++){
		for($j=0;$j<$countIDXNum;$j++){
			$countAndIdArray[$j] = $jsonArray['countID'.$i][$j];
	}
		$sql = "INSERT INTO `tbl_count`(`amount`,`_date`,`details_key`,`_createdate`) 
		        VALUES ('".$countAndIdArray[1]."','".$date."','".$countAndIdArray[0]."','".$date."');";
		//echo $sql."<br>";		
		$result = mysqli_query($conn, $sql) ;
	}
	
	$sqlCost = "SELECT `tbl_count`.`amount`*`price` AS Cost FROM `tbl_count`,`tbl_details`
				WHERE `tbl_count`.`_createdate`='".$date."'
				AND `tbl_count`.`details_key` = `tbl_details`.`details_key`;";
	$sum = 0;
	$resultCost = mysqli_query($conn, $sqlCost);
	if(!empty($resultCost)){
		while ($row = mysqli_fetch_array($resultCost)) {
			$sum = $sum+$row["Cost"];
		}
		$response ["success"] = 1;
		$response ["date"] = $date;
		$response ["sum"] = $sum;
		echo json_encode($response);
	}else{
		$response ["success"] = 0;
		$response ["msg"] = "error sum function"; 
		echo json_encode($response);
	}
//echo count($jsonArray)."<br>";	    //jsonArray陣列數量	
//echo count($jsonArray['countID0']);   //countID底下陣列數量
}else{
	$response ["success"] = 0;
	$response ["msg"] = "error insert function";
	echo json_encode($response);
}
mysqli_close($conn);

/*
if(isset($_POST['products'])){
	$productsData = $_POST['products'];
	$file = fopen("test.txt","a+"); //開啟檔案
	fwrite($file,$productsData);
}else{
	$file = fopen("test.txt","a+"); //開啟檔案
	fwrite($file,"No data!!!");
}
fclose($file);
*/
?>