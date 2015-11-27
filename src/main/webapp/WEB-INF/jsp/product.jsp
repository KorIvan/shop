<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form:form commandName="product">
		<form:errors path="*" cssClass="errorblock" element="div" />
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
<%-- 	<form:select id="categories" path="product"></form:select> --%>
<select id="categories"></select>



<!-- <script type="text/javascript" -->
<%-- 		src="<spring:url value='js/jquery-2.1.4.js'/>" --%>
<!-- 		></script> -->
<script type="text/javascript" src="http://code.jquery.com/jquery-2.1.4.min.js"></script> 
	<script type="text/javascript">
	$(document).ready(
		function() {
			$.getJSON('<spring:url value="categories.json"/>', {
				ajax : 'true'
			}, function(data){
				var html = '<option value="">--Please select category--</option>';
				var len = data.length;
				for (var i = 0; i < len; i++) {
					html += '<option value="' + data[i].name + '">'
							+ data[i].name + '</option>';
				}
				html += '</option>';
				$('#categories').html(html);
			});
			
		});
	
</script>
</body>
</html>