<?php
require 'connect.php';
require 'function/user.function.php';
require 'function/test.function.php';

if (isset($_POST['action'])) {
	$email = $_POST['name'];
	$password = $_POST['password'];
	$data = [];
	$result = login($conn,$email,$password);
	if (mysqli_num_rows($result)>0) {
		array_push($data, ["result" => "success"]);
	}else{
		array_push($data, ["result" => "failed"]);
	}
	$return = json_encode($data);
	echo $return;
}



?>