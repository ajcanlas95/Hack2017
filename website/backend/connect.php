<?php
define('hostname', 'localhost');
define('username', 'root');
define('password', '');
define('database', 'hack');

$conn = mysqli_connect(hostname,username,password,database);

if (!$conn) {
	echo "connection failed";
}

?>