<!DOCTYPE html>
	<html>
		<head>
			<title>Hack2017</title>
			<!--Import Google Icon Font-->
			<link href="materialize/css/fonts.googleapis.css" rel="stylesheet">
			<!--Import materialize.css-->
			<link type="text/css" rel="stylesheet" href="materialize/css/materialize.min.css"  media="screen,projection"/>
			<!--Let browser know website is optimized for mobile-->
			<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
		</head>

		<body>
			<!-- NavBar-->	
			<nav>
				<div class="nav-wrapper">
					<a href="#" class="brand-logo center">Hack 2017</a>
					<a href="#" data-activates="mobile-sidebar" class="button-collapse"><i class="material-icons">menu</i></a>
					<ul id="nav-mobile" class="left hide-on-med-and-down">
						 <!-- Login Trigger -->
						<li><a class="modal-trigger" href="#login" >Login</a></li>
					</ul>
					<ul id="mobile-sidebar" class="side-nav">
						 <!-- Login Trigger -->
						<li><a class="modal-trigger" href="#login-mobile">Login</a></li>
					</ul>
				</div>
			</nav>
			<!-- Login Structure -->
			<div id="login" class="modal">
				<div class="modal-content">
					<h4>Hack 2017</h4>
					<a href="registration.php" class="btn-floating waves-effect waves-light red right pulse"><i class="material-icons">person_add</i></a>
					<div class="row">
						<!-- Tab Structure-->
						<ul class="tabs">
							<li class="tab col s6"><a href="#withoutorg">Email Login</a></li>
							<li class="tab col s6">
								<a href="#withorg">Username Login</a>
							</li>
						</ul>

						<!-- Login using Email -->
						<form action="../backend/login.php" method="POST">
							<div id="withoutorg" class="row">
								<div class="row">
									<div class="input-field col s12">
										<input name="email" id="emailadd" type="email" class="validate" required="true">
										<label for="emailadd">Email Address</label>
									</div>
								</div>
								<div class="row">
									<div class="input-field col s12">
										<input name="password" id="password" type="password" class="validate" required="true">
										<label for="password">Password</label>
									</div>
								</div>
								<div class="row">
									<button class="right btn waves-effect waves-light" type="submit" name="desktop-login">Submit
										<i class="material-icons right">send</i>
									</button>	
								</div>
							</div>
						</form>

						<!-- Login using Username -->
						<form action="../backend/login.php" method="POST">
							<div id="withorg" class="row">
								<div class="row">
									<div class="input-field col s12">
										<input name="username" id="username" type="text" class="validate" required="true">
										<label for="username">Username</label>
									</div>
								</div>
								<div class="row">
									<div class="input-field col s12">
										<input name="organization" id="organization" type="text" class="validate" required="true">
										<label for="organization">Organization</label>
									</div>
								</div>
								<div class="row">
									<div class="input-field col s12">
										<input name="userpass" id="password" type="password" class="validate" required="true">
										<label for="password">Password</label>
									</div>
								</div>
								<div class="row">
									<button class="right btn waves-effect waves-light" type="submit" name="desktop-user-login">Submit
										<i class="material-icons right">send</i>
									</button>
								</div>
							</div>
						</form>
					</div>
				</div>					
			</div>

			<!-- Login Structure for mobile -->
			<div id="login-mobile" class="modal modal-fixed-footer">
				<div class="modal-content">
					<h4>Hack 2017</h4>
					<ul class="collapsible popout" data-collapsible="accordion">
						<!-- Login using Email -->
							<li>
								<div class="collapsible-header active"><i class="material-icons">email</i>Email Login</div>
								<div class="collapsible-body">
									<div id="withoutorg" class="row">
										<form action="../backend/login-mobile.php" method="POST">
											<div class="row">
												<div class="input-field col s12">
													<input name="mobile_email" id="mobile_email" type="email" class="validate" required="true">
													<label for="mobile_email">Email Address</label>
												</div>
											</div>
											<div class="row">
												<div class="input-field col s12">
													<input name="mobile_password" id="mobile_password" type="password" class="validate" required="true">
													<label for="mobile_password">Password</label>
												</div>
											</div>
											<div class="row">
												<button class="right btn waves-effect waves-light" type="submit" name="mobile-login">Submit
													<i class="material-icons right">send</i>
												</button>	
											</div>
										</form>
									</div>
								</div>
							</li>
						<!-- Login using Username -->
						<li>
							<div class="collapsible-header"><i class="material-icons">account_circle</i>Username Login</div>
							<div class="collapsible-body">
								<div id="withorg" class="row">
									<form action="../backend/login-mobile.php" method="POST">
										<div class="row">
											<div class="input-field col s12">
												<input name="mobile_username" id="mobile_username" type="text" class="validate" required="true">
												<label for="mobile_username">Username</label>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s12">
												<input name="mobile_organization" id="mobile_organization" type="text" class="validate" required="true">
												<label for="mobile_organization">Organization</label>
											</div>
										</div>
										<div class="row">
											<div class="input-field col s12">
												<input name="mobile_userpass" id="mobile_userpass" type="password" class="validate" required="true">
												<label for="mobile_userpass">Password</label>
											</div>
										</div>
										<div class="row">
											<button class="right btn waves-effect waves-light" type="submit" name="mobile-user-login">Submit
												<i class="material-icons right">send</i>
											</button>
										</div>
									</form>
								</div>
							</div>
						</li>
					</ul>
				</div>
				<div class="modal-footer">
					<a href="registration.php" class="modal-action modal-close btn-floating waves-effect waves-light red right"><i class="material-icons">person_add</i></a>
				</div>
			</div>

			<div class="row">
				<div class="col s12 ">
					<div class="card">
						<div class="card-content">
							<span class="card-title">Basic Info</span>
							<table class="responsive-table centered highlight ">
								<thead id="table-column">
									<tr></tr>
								</thead>
								<tbody id="table-row">
								</tbody>
								<tfoot>
									<tr>
										<td>
											<a class='btn-floating waves-effect waves-light green darken-1 btn' href='#' >
												<i class="material-icons">add</i>
											</a>
										</td>
									</tr>
								</tfoot>
							</table>
						</div>
					</div>
				</div>
			</div>
		
			<!--Import jQuery before materialize.js-->
			<script type="text/javascript" src="https://code.jquery.com/jquery-3.2.1.min.js"></script>
			<script type="text/javascript" src="materialize/js/materialize.min.js"></script>
			<!--Customized JS for Hack2017-->
			<script type="text/javascript" src="materialize/js/Hack2017.js"></script>
			<!-- JS for parsing data -->
			<script type="text/javascript" src="materialize/js/parseBasic.js"></script>
		</body>

	</html>
        