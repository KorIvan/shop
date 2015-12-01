<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>Authorization</title>
<link type="text/css" rel="stylesheet" href="css/mycss.css">
</head>

<!-- <form method="POST" id="authForm" onsubmit="return validate(this);"> -->
<!-- 	<input type="email" name="email" id="email" placeholder="Enter email" -->
<!-- 		title="Please enter your email which contains at least 5 symbols." /> -->

<!-- 	<input type="password" name="password" id="password" -->
<!-- 		placeholder="Enter password" title="Please enter your password 1-9A-z" /> -->
<!-- 	<div class="result"></div> -->
<!-- 	<button name="submit" type="submit" id="submit">Submit</button> -->
<!-- </form> -->
<div>${message}</div>
<form:form method="POST" commandName="user" onsubmit="return validate(this);">
	<form:input path="email" placeholder="Enter email"
		title="Please enter your email which contains at least 5 symbols." />

	<form:input path="password"	placeholder="Enter password" title="Please enter your password 1-9A-z" />
	<div class="result"></div>
	<button name="submit" type="submit" id="submit">Submit</button>
</form:form>

<!-- 	<script type="text/javascript"> 
	// function validate() { // var email =
	document.forms["authForm"]["email"].value; // // var password =
	form.password; // alert(email + " " + password+" 2"); // }
</script> -->

<script type="text/javascript">
	function validate(form) {
		var email = form.email.value;

		var password = form.password.value;
		if (email == null || email == "" || password == null || password == "") {
			alert("Fields must be filled out");
			return false;
		}
		var pattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
		if (!email.match(pattern)) {
			alert("Login does not match pattern!");
		}
		return true;
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