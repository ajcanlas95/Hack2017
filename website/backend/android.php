<?php

require 'connect.php';
require 'function/user.function.php';

$json = file_get_contents('php://input');
$obj = json_decode($json);

$email = $obj['email'];
$password = $obj['password'];
$data = [];
$result = login($conn,$email,$password);

	if (mysqli_num_rows($result)>0) {
		while ($value = mysqli_fetch_assoc($result)) {
		 	array_push($data,['email'=>$value['username'] ,'password'=>$value['password'] ]);
		 
		 } 
	}else{
		echo "login failed!";
	}

$result = json_encode($data);

echo $result;
	

?>
