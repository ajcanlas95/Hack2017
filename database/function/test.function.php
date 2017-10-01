<?php
function parseTest($conn){
	$query = "select * from test";

	return mysqli_query($conn,$query);
}
?>