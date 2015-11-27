function validateRegForm(form) {
	var firstName = form.firstName.value;
	var firstNameBool = false;
	var namePattern = /^[a-zA-Z]+$/;
	document.getElementById("firstNameError").innerHTML = "";
	if (firstName == null || !firstName.match(namePattern)
			|| firstName.length < 2) {
		document.getElementById("firstNameError").innerHTML = "First name must consist of alphabetical symbols!";
		// return false;
	} else {
		firstNameBool = true;
		form.firstName.value = firstName.toUpperCase();
		console.log(form.firstName.value);

	}
	var lastName = form.lastName.value;
	var lastNameBool = false;
	document.getElementById("lastNameError").innerHTML = "";

	if (lastName == null || !lastName.match(namePattern) || lastName.length < 2) {
		document.getElementById("lastNameError").innerHTML = "Last name must consist of alphabetical symbols!";
		// return false;
	} else {
		lastNameBool = true;
		form.lastName.value = lastName.toUpperCase();
		console.log(form.lastName.value);
	}

	var birthdate = form.birthdate.value;
	var birthdatePattern = /^\d{2}\/\d{2}\/\d{4}$/;
	var birthdateBool = false;
	document.getElementById("birthdateError").innerHTML = "";

	if (birthdate == null || !birthdate.match(birthdatePattern)) {
		document.getElementById("birthdateError").innerHTML = "Select your birthdate as mm/dd/yyyy!";
	} else {
		birthdateBool = true;
	}

	var email = form.email.value;
	var emailBool = false;
	var emailPattern = /^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$/;
	document.getElementById("emailError").innerHTML = "";
	if (email == null || !email.match(emailPattern)) {
		document.getElementById("emailError").innerHTML = "Email is not correct!";
		// return false;
	} else {
		emailBool = true;
	}
	var password = form.password.value;
	var passPattern = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])\w{6,}$/;
	var passBool = false;
	document.getElementById("passwordError").innerHTML = "";

	if (password == null || !password.match(passPattern)) {
		document.getElementById("passwordError").innerHTML = "Passoword must contain at least 1 digit, 1 lowercase , 1 uppercase character and contain at least 6 characters!";
		// return false;
	} else {
		passBool = true;

	}
	var passwordRepeat = form.passwordRepeat.value;
	var passwordRepeatBool = false;
	document.getElementById("passwordRepeatError").innerHTML = "";

	if (password == null || password !== passwordRepeat) {
		document.getElementById("passwordRepeatError").innerHTML = "Passwords don't match!";
		// return false;
	} else {
		passwordRepeatBool = true;
	}
	console.log(passwordRepeatBool && passBool && emailBool && firstNameBool
			&& lastNameBool);
	if (passwordRepeatBool && passBool && emailBool && firstNameBool
			&& lastNameBool && birthdateBool)
		return true;
	else
		return false;
}