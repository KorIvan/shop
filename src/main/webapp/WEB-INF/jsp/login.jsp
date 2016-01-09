<%@ include file="header.jsp"%>
<div align="center">

	<form action="login" method="post">
		<fieldset>
			<legend>Please Login</legend>
			<label for="username">Email</label> 
			<input type="text" id="username" name="username" /> <br/>
				<label for="password">Password</label>
				 <input type="password" id="password" name="password" /> 
				 <input	type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
			<div align="center" class="form-actions">
				<button type="submit" class="btn">Log in</button>
			</div>
		</fieldset>
	</form>
</div>
<%@ include file="footer.jsp"%>