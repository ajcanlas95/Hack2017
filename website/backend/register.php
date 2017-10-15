<?php
require 'connect.php';
require 'function/user.function.php';
require 'function/validate.input.php';
if (isset($_POST['register'])) {
	$fName = validate($_POST['first_name']);
	$mName = validate($_POST['middle_name']);
	$lName = validate($_POST['last_name']);
	$nName = validate($_POST['nick_name']);
	$pName = validate($_POST['post_name']);
	$eMail = validate($_POST['email']);
	$cNumber = validate($_POST['contact_number']); 
	$orgName = validate($_POST['organization']); 
	$preOrg = "";
	$words = explode(" ", $orgName);

	foreach ($words as $first_letter) {
		$preOrg.=  $first_letter[0];
		
	}

	$fName = ucwords($fName);
	$mName = ucwords($mName);
	$lName = ucwords($lName);
	$pName = ucwords($pName);
	$orgName = ucwords($orgName);
	$preOrg = strtoupper($preOrg);
	
	$orgExists = checkOrganization($conn,$orgName);

	if (mysqli_num_rows($orgExists)<1) {
		$registerInfoAndOrg = registerBasic($conn,$fName,$mName,$lName,$nName,$pName,$eMail,$cNumber);

		if ($registerInfoAndOrg) {
			$registerOrg = registerOrganization($conn,$orgName,$preOrg);

			if ($registerOrg) {
				echo "Successful Registration and Organization";
			}else{
				echo "Org Registration Unsuccessful".mysqli_error($conn);
			}

		}else{
			echo "Info Registration Unsuccessful".mysqli_error($conn);
		}
	}else{
		$registerInfo = registerBasic($conn,$fName,$mName,$lName,$nName,$pName,$eMail,$cNumber);
		if ($registerInfo) {
			echo "Successful Registration of Info";
		}
	}

}

?>
