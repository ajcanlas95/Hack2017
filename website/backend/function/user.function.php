<?php
function login($conn,$email,$password){
	$query = "select * from login where username = '$email' and password = '$password'";

	return mysqli_query($conn,$query);
}
function loginEmail($conn,$email,$organization,$password){
	$query = "select * from account a, login l, organization o where l.username = '$email' and o.organization_name = '$organization' OR o.organization_table_prefix = '$organization' and l.password = '$password' and l.id_account = a.id_account and a.id_organization = o.id_organization";

	return mysqli_query($conn,$query);
}
?>