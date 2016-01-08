
<%@ include file="header.jsp" %>
<div align="center"><h1>${title}</h1>
	<form:form modelAttribute="category" method="post" id="categoryForm">
		<table>
			<tr>
				<td><form:input path="name" placeholder="Enter category name" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="name" id="nameError"
					class="error"></label></td>
				<form:errors path="name" cssClass="error" />
			</tr>
			<tr>
				<td><form:textarea path="description" placeholder="Category description" /></td>
			</tr>
		</table>
		<table>

			<thead>
				<tr>
					<th>Attribute</th>
					<th>Description</th>
					<th></th>
				</tr>
			</thead>

			<tbody class="attributes" id="category">
				<c:forEach items="${category.attributesForCategory}" var="attribute"
					varStatus="i" begin="0">
					<tr>
						<td><form:input path="attributesForCategory[${i.index}].name"
								id="name${i.index}" /></td>
						<td><form:input
								path="attributesForCategory[${i.index}].description"
								id="description${i.index}" /></td>
						<td><input type="checkbox" value="${i.index}" name="toDelete" /></td>

					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="submit" value="Save" name="action" />
		<input type="submit" value="Add Attribute" name="action" />
		<input type="submit" value="Delete" name="action" />
	</form:form>


<%@ include file="footer.jsp" %>