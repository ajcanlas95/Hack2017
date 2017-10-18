<?php
require 'connect.php';
require 'function/user.function.php';
require 'function/test.function.php';

if (isset($_POST['action'])) {
	$email = $_POST['name'];
	$password = $_POST['password'];
	$organization = $_POST['organization'];
	$data = [];
	$result = loginEmail($conn,$email,$organization,$password);
	if (mysqli_num_rows($result)>0) {
		array_push($data, ["result" => "success"]);
	}else{
		array_push($data, ["result" => "failed"]);
	}
	$return = json_encode($data);
	echo $return;
}



?>
