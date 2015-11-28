<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>

</head>
<body>
	<form:form commandName="product" onsubmit="return validateProdForm(this);">
		<%-- 		<form:errors path="*" cssClass="errorblock" element="div" /> --%>
		<table>
			<tr>
				<td><form:input path="name" placeholder="Enter product name" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="name" id="nameError"
					class="error"></label></td>
				<form:errors path="name" cssClass="error" />
			</tr>
			<tr>
				<td><form:select path="category" id="categories">
						<form:option value="">Select Category</form:option>
						<c:forEach items="${categories}" var="category">
							<form:option value="${category}">${category.name}</form:option>
						</c:forEach>
					</form:select></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="category"
					id="categoryError" class="error"></label></td>
				<form:errors path="category" cssClass="error" />

			</tr>
			<tr>
				<td><form:input path="currentPrice"
						placeholder="Enter current price" /></td>
			</tr>
				<tr>
				<td class="errorblock"><label for="currentPrice"
					id="currentPriceError" class="error"></label></td>
				<form:errors path="currentPrice" cssClass="error" />

			</tr>
			<tr>
				<td><form:input path="weight"
						placeholder="Enter product's weight" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="weight" id="weightError"
					class="error"></label></td>
				<form:errors path="weight" cssClass="error" />

			</tr>

			<tr>
				<td><form:input path="bulk" placeholder="Enter product's bulk" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="bulk" id="bulkError"
					class="error"></label></td>
				<form:errors path="bulk" cssClass="error" />

			</tr>
			<tr>
				<td><input type="submit" value="Submit" class="input"></td>
			</tr>
			<tr>
				<td><form:textarea path="description"
						placeholder="Enter description" /></td>
			</tr>

		</table>


	</form:form>
<script type="text/javascript"
	src="../js/validateProdForm.js">

	</script>


	<!-- <script type="text/javascript" -->
	<%-- 		src="<spring:url value='js/jquery-2.1.4.js'/>" --%>
	<!-- 		></script> -->
	<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
		<script type="text/javascript">
		$(document).ready(
				function() {
					$.getJSON('<spring:url value="categories.json"/>', 
							{ajax : 'true'	},
					function(data) {
						var html = '<option value="">--Please select one--</option>';
						var len = data.length;
						for (var i = 0; i < len; i++) {
							html += '<option value="' + data[i].name + '">'
									+ data[i].name
									+ '</option>';
						}
						html += '</option>';

						$('#categories').html(html);
					});
					
				});
		</script>
	
</body>
</html>