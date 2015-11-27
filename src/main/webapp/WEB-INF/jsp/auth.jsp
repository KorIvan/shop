<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Authorization</title>
<link type="text/css" rel="stylesheet" href="css/mycss.css">
</head>

<body class="activateAppearAnimation">
	<!-- 	<div class="container"> -->
	<!-- 			<form method="POST" id="authForm" role="form"> -->
	<!-- 				<div class="form-group"> -->
	<!-- 					<label for="email">Email</label>  -->
	<!-- 					<input type="email" -->
	<!-- 						name="email" id="email" -->
	<!-- 						placeholder="Enter email" title="Please enter your email like this: xxxxx@xxx.xx" /> -->
	<!-- 						<errors path="minutes" class="error"/> -->
	<!-- 				</div> -->

	<!-- 				<div class="form-group"> -->
	<!-- 					<label for="password">Password</label>  -->
	<!-- 					<input type="password" -->
	<!-- 						name="password" id="password" -->
	<!-- 						placeholder="Enter password" title="Please enter your password 1-9A-z" /> -->
	<!-- 				</div> -->
	<!-- 				<div class="result"></div> -->
	<!-- 				<button name="submit" type="submit" -->
	<!-- 					id="submit">Submit</button> -->
	<!-- 			</form> -->
	<!-- 	</div> -->

	<div class="container">
		<form method="POST" id="authForm" role="form"
			onsubmit="return validate(this);">
			<div class="form-group">
				<input type="login" name="login" id="login"
					placeholder="Enter login"
					title="Please enter your login which contains at least 5 symbols." />
			</div>

			<div class="form-group">
				<input type="password" name="password" id="password"
					placeholder="Enter password"
					title="Please enter your password 1-9A-z" />
			</div>
			<div class="result"></div>
			<button name="submit" type="submit" id="submit">Submit</button>
		</form>
	</div>

	<!-- 	<script type="text/javascript"> 
	// function validate() { // var login =
	document.forms["authForm"]["login"].value; // // var password =
	form.password; // alert(login + " " + password+" 2"); // }
</script> -->

	<script type="text/javascript">
		function validate(form) {
			var login = form.login.value;

			var password = form.password.value;
			if (login == null || login == ""||password==null||password=="") {
				alert("Fields must be filled out");
				return false;
			}
			// 			var pattern="/^[^\\\/&]*$/";
			var pattern="[^A-Za-z0-9_]";
			if (login.match(pattern)||login.length<5)	{
				alert("login does not match pattern!");
			}		
			return false;
		}
	</script>

	<script type="text/javascript"
		src="js-plugin/jquery/1.8.3/jquery.min.js"></script>
	<script type="text/javascript"
		src="js-plugin/jquery-ui/jquery-ui-1.8.23.custom.min.js"></script>
	<!-- third party plugins  -->
	<script type="text/javascript" src="bootstrap/js/bootstrap.js"></script>
	<script type="text/javascript"
		src="js-plugin/easing/jquery.easing.1.3.js"></script>


	<!-- form -->
	<script type="text/javascript"
		src="js-plugin/neko-contact-ajax-plugin/js/jquery.form.js"></script>
	<script type="text/javascript"
		src="js-plugin/neko-contact-ajax-plugin/js/jquery.validate.min.js"></script>

	<!-- Custom  -->
	<script type="text/javascript" src="js/custom.js"></script>

	<!-- 	<script type="text/javascript" src="js/admin.js"></script> -->


</body>
</html>

</html>