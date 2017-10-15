<?php

$json = file_get_contents('php://input');
$obj = json_decode($json);

$email = $obj->{'email'};
$password = $obj->{'password'};

echo $email." ".$password;

foreach($data as $key => $value ){
	echo "Key= ".$key."<br>"."Value= ".$value."<br>";
}


?>
