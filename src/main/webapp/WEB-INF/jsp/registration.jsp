<%@ include file="header.jsp" %>
	<div align="center"><h1>${title}</h1>
	<form:form commandName="person"
		onsubmit="return validateRegForm(this);">
<%-- 		<form:errors path="*" cssClass="errorblock" element="div" /> --%>
		<table>
			<tr>
				<td>${message}</td>
			</tr>
			<tr>
				<td><form:input path="firstName" placeholder="Enter first name" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="firstName"
					id="firstNameError" class="error"></label></td>
				<form:errors path="firstName" cssClass="error" />

			</tr>
			<tr>
				<td><form:input path="lastName" placeholder="Enter last name" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="lastName" id="lastNameError"
					class="error"></label></td>
				<form:errors path="lastName" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="birthdate" placeholder="Enter birthdate" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="birthdate"
					id="birthdateError" class="error"></label></td>
				<form:errors path="birthdate" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="email" placeholder="Enter email" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="email" id="emailError"
					class="error"></label></td>
				<form:errors path="email" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="password" placeholder="Enter password" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="password" id="passwordError"
					class="error"></label></td>
				<form:errors path="password" cssClass="error" />

			</tr>
			<tr>
				<td><input id="passwordRepeat" placeholder="Repeat password" /></td>
			</tr>

			<tr>
				<td class="errorblock"><label for="passwordRepeat"
					id="passwordRepeatError" class="error"></label></td>

			</tr>

			<tr>
				<td><input type="submit" value="Submit" class="input"></td>
			</tr>
		</table>
	</form:form>
</div>
<script type="text/javascript">
$(function() {
	$("#birthdate").datepicker();
	});
</script>
	<script src="<spring:url value="js/validateRegForm.js"/>"
		type="text/javascript" /></script>
<%@ include file="footer.jsp" %>
