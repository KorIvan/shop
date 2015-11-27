function validate(form) {
			var login = form.login.value;
			var password = form.password.value;
			if (login == null || login == ""||password==null||password=="") {
				alert("Fields must be filled out");
				return false;
			}
			Vali
			// 			var pattern="/^[^\\\/&]*$/";
			var pattern="[^A-Za-z0-9_]";
			if (login.match(pattern)||login.length<5)	{
				alert("login does not match pattern!");
			}		
			return false;
		}