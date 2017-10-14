<?php
require 'connect.php';
$database = 'hack';
$table = 'basic';

function displayAllColumn($conn,$database,$table){
	$query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = '$database' AND TABLE_NAME='$table'" ;

	return mysqli_query($conn,$query);
}

function displayAllRows($conn){
	$query = "select * from basic";

	return mysqli_query($conn,$query);
}

$resultColumn = displayAllColumn($conn,$database,$table);
$resultRow = displayAllRows($conn);

	
// while ($dataRow = mysqli_fetch_assoc($resultRow)) {
// 	foreach ($dataRow as $keyData => $valueData) {
// 		echo $keyData."".$valueData."<br>";
// 	}

// 	echo $dataRow['id_basic']."<br>";
	
// 	echo "<br>";
// }



?>