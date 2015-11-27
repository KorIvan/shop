<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form:form commandName="category">
		<form:errors path="*" cssClass="errorblock" element="div" />
		<table>
			<tr>
				<td><form:input path="name" placeholder="Enter first name" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="name" id="nameError"
					class="error"></label></td>
				<form:errors path="name" cssClass="error" />

			</tr>
			<tr>
				<td><form:input path="description" placeholder="Description" /></td>
			</tr>


			<c:forEach var="attribute" items="${category.attributesForCategory}">
				<tr>
					<td>${attribute.name}${attribute.description}</td>
				</tr>
			</c:forEach>
			<tr>
			</tr>

			<tr>
				<td><input type="submit" value="Submit" class="input"></td>
			</tr>
		</table>
		<table>
			<thead>
				<tr>
					<th>Attribute name</th>
					<th>Description</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${category.attributesForCategory}" var="attribute"
					varStatus="i" begin="0" >
					<tr>
						<td><form:input path="attributesForCategory[${i.index}].name"
								id="name${i.index}" /></td>
						<td><form:input
								path="attributesForCategory[${i.index}].description"
								id="description${i.index}" /></td>
						<td><a href="#" >Remove attribute</a></td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<input type="button" onclick="addAttribute();" />

	</form:form>
	<script type="text/javascript"
		src="<spring:url value="js/jquery-2.1.4.js"/>"></script>
	<script type="text/javascript">
		function addAttribute() {
			// 			$('#att').append('yourtHTML');
		}
	</script>
	<script type="text/javascript">
		function rowAdded(rowElement) {
			//clear the imput fields for the row
			$(rowElement).find("input").val('');
			//may want to reset <select> options etc

			//in fact you may want to submit the form
			saveNeeded();
		}
		function rowRemoved(rowElement) {
			saveNeeded();
			alert("Removed Row HTML:\n" + $(rowElement).html());
		}

		function saveNeeded() {
			$('#submit').css('color', 'red');
			$('#submit').css('font-weight', 'bold');
			if ($('#submit').val().indexOf('!') != 0) {
				$('#submit').val('!' + $('#submit').val());
			}
		}

		function beforeSubmit() {
			alert('submitting....');
			return true;
		}

		$(document).ready(function() {
			var config = {
				rowClass : 'person',
				addRowId : 'addPerson',
				removeRowClass : 'removePerson',
				formId : 'personListForm',
				rowContainerId : 'personListContainer',
				indexedPropertyName : 'personList',
				indexedPropertyMemberNames : 'name,age',
				rowAddedListener : rowAdded,
				rowRemovedListener : rowRemoved,
				beforeSubmit : beforeSubmit
			};
			new DynamicListHelper(config);
		});
	</script>

</body>
</html>