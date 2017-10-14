<?php
define('hostname', 'localhost');
define('username', 'hackuser');
define('password', 'H@cKpassw0rd');
define('database', 'hack');

$conn = mysqli_connect(hostname,username,password,database);

if (!$conn) {
	echo "connection failed";
}

?>
