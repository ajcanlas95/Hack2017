<?php
require 'connect.php';
require 'function/user.function.php';

if (isset($_POST['mobile-login'])) {
	$email = $_POST['mobile_email'];
	$pass = $_POST['mobile_password'];

	$result = login($conn,$email,$pass);

	if (mysqli_num_rows($result)>0) {
		$data = mysqli_fetch_assoc($result);
		$_SESSION['hack']['login_id'] = $data['id_login'];
		$_SESSION['hack']['login_email'] = $data['username'];

		echo $_SESSION['hack']['login_id'];
		echo $_SESSION['hack']['login_email'];
	}else{
		echo "login failed!";
	}
}

if (isset($_POST['mobile-user-login'])) {
	$user = $_POST['mobile_username'];
	$org = $_POST['mobile_organization'];
	$pass = $_POST['mobile_userpass'];

	$loginResult = loginEmail($conn,$user,$org,$pass);

	if (mysqli_num_rows($loginResult)>0) {
		$loginData = mysqli_fetch_assoc($loginResult);
		$_SESSION['hack']['login_id'] = $loginData['id_login'];
		$_SESSION['hack']['login_email'] = $loginData['username'];
		$_SESSION['hack']['login_org'] = $loginData['organization_name'];

		echo $_SESSION['hack']['login_id'];
		echo $_SESSION['hack']['login_email'];
		echo $_SESSION['hack']['login_org'];
	}else{
		echo "login failed!";
	}
}

?>