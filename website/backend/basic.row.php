<?php
require 'connect.php';
require 'function/table.function.php';

$resultRow = displayAllRows($conn);
$resultColumn = displayAllColumn($conn);

$jsonColumn= [];
$jsonRow= [];
$json=[];

while ($dataColumn = mysqli_fetch_assoc($resultColumn)) {
	array_push($jsonColumn, $dataColumn);
}

while ($dataRow = mysqli_fetch_assoc($resultRow)) {
	array_push($jsonRow, $dataRow);
}

array_push($json,[ 'column' => $jsonColumn, 'row' => $jsonRow ]);

$result = json_encode($json);
echo $result;
?>