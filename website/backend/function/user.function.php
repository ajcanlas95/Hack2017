<?php
function login($conn,$email,$password){
	$query = "select * from login where username = '$email' and password = '$password'";

	return mysqli_query($conn,$query);
}
function loginEmail($conn,$email,$organization,$password){
	$query = "select * from account a, login l, organization o where l.username = '$email' and o.organization_name = '$organization' OR o.organization_table_prefix = '$organization' and l.password = '$password' and l.id_account = a.id_account and a.id_organization = o.id_organization";

	return mysqli_query($conn,$query);
}
function registerBasic($conn,$first_name,$middle_name,$last_name,$nick_name,$post_name,$email,$contact_number){
	$query = "insert into basic (fn_name,mn_name,ln_name,nn_name,pof_name,email,contact_number) values('$first_name','$middle_name','$last_name','$nick_name','$post_name','$email','$contact_number')";

	return mysqli_query($conn,$query);
}
function registerOrganization($conn,$organization,$organization_prefix){
	$query = "insert into organization (organization_name,organization_table_prefix) values('$organization','$organization_prefix')";

	return mysqli_query($conn,$query);
}
function checkOrganization($conn,$organization){
	$query = "select organization_name from organization where organization_name = '$organization'";

	return mysqli_query($conn,$query);
}

?>