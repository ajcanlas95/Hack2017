<?php
function login($conn,$email,$password){
	$query = "select * FROM ((Account INNER JOIN Login ON Account.id_account = Login.id_account) INNER JOIN Basic ON Account.id_basic = Basic.id_basic) where Basic.email='$email' and Login.password='$password'";

	return mysqli_query($conn,$query);
}
function loginEmail($conn,$email,$organization,$password){
	$query  = "select * from ((Account INNER JOIN Login ON Account.id_account = Login.id_account) INNER JOIN Organization ON Account.id_organization = Organization.id_organization) where Login.username='$email' and Organization.organization_name='$organization' and Login.password='$password'";

	return mysqli_query($conn,$query);
}
function registerBasic($conn,$first_name,$middle_name,$last_name,$nick_name,$post_name,$email,$contact_number){
	$query = "insert into Basic (fn_name,mn_name,ln_name,nn_name,pof_name,email,contact_number) values('$first_name','$middle_name','$last_name','$nick_name','$post_name','$email','$contact_number')";

	return mysqli_query($conn,$query);
}
function registerOrganization($conn,$organization,$organization_prefix){
	$query = "insert into Organization (organization_name,organization_table_prefix) values('$organization','$organization_prefix')";

	return mysqli_query($conn,$query);
}
function checkOrganization($conn,$organization){
	$query = "select organization_name from Organization where organization_name = '$organization'";

	return mysqli_query($conn,$query);
}

?>
