<?php

$email = $_GET['email'];
$password = $_GET['password'];

// if (isset($_POST["json"])) {
	// $obj = json_decode($_POST["json"]);

	// $email = $obj->email;
	// $password = $obj->password 

$data = array('email'=>$email ,'password'=>$password);

$result = json_encode($data);

echo $result;


	
// }

// $json = file_get_contents('php://input');

// foreach($data as $key => $value ){
// 	echo "Key= ".$key."<br>"."Value= ".$value."<br>";
// }


?>
