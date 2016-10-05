<?php
require ("db_config.php");
//create connection
$conn=  mysqli_connect(DB_SERVER,DB_USER,DB_PASSWORD,DB_DATABASE);
//check
if(mysqli_connect_error())
    echo "Fail to connect to mysql:".  mysqli_connect_error();
else
    //echo "Connect ok"."<br>";
            
mysqli_query($conn, "SET NAMES utf8");       
?>