<?php
require 'connect.php';
require 'function/table.function.php';

$resultRow = displayAllRows($conn);

$jsonRow= [];

while ($dataRow = mysqli_fetch_assoc($resultRow)) {
	array_push($jsonRow, $dataRow);
}

$row = json_encode($jsonRow);
echo $row;
?>