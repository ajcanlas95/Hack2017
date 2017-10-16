<?php
function displayAllColumn($conn){
	$query = "SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = 'hack' AND TABLE_NAME='Basic'" ;

	return mysqli_query($conn,$query);
}

function displayAllRows($conn){
	$query = "select * from Basic";

	return mysqli_query($conn,$query);
}
?>