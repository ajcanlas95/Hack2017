<?php
require 'connect.php';
require 'function/table.function.php';

$resultColumn = displayAllColumn($conn);

$jsonColumn= [];

while ($dataColumn = mysqli_fetch_assoc($resultColumn)) {
	array_push($jsonColumn, $dataColumn);
}

$column = json_encode($jsonColumn);
echo $column;
?>
