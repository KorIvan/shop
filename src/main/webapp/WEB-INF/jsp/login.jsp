<%@ include file="header.jsp"%>
<form action="login" method="post">
	<fieldset>
		<legend>Please Login</legend>
		<label for="username">Email</label> <input type="text" id="username"
			name="username" /> <label for="password">Password</label> <input
			type="password" id="password" name="password" /> 
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		<div class="form-actions">
			<button type="submit" class="btn">Log in</button>
		</div>
	</fieldset>
</form>
<%@ include file="footer.jsp"%>