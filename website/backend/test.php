<?php
require 'connect.php';
require 'function/test.function.php';

$data = parseTest($conn);

$result = mysqli_fetch_assoc($data);

$test = json_encode($result);

echo $test;
?>