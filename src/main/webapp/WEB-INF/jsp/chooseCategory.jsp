<%@ include file="header.jsp" %>
<div align="center"><h1>${title}</h1>

	<form:form commandName="product">
		<table>
			<tr>
				<td>${message}</td>
			</tr>
			<tr>
				<td><form:select path="category" id="categories" >
						<form:option value="">Select Category</form:option>
						<form:options items="${categories}" itemValue="id"
							itemLabel="name" />
					</form:select></td>
			</tr>

			<tr>
				<td><input type="submit" value="Submit" id="submit"/></td>
			</tr>
		</table>


	</form:form>
	</div>
	
<%@ include file="footer.jsp" %>