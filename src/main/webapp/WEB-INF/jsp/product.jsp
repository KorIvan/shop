<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>${title}</title>
<link href="<spring:url value="/css/addNewProd.css"/>" rel="stylesheet">

</head>
	<h1>${title}</h1>

<body>
	<form:form commandName="product"
		onsubmit="return validateProdForm(this);">
		<table>
			<tr>
				<td>${message}</td>
			</tr>
			<tr>
				<td><form:input path="name" placeholder="Enter product name" />
				</td>
			</tr>
			<tr>
				<td class="errorblock"><label for="name" id="nameError"
					class="error"></label> <form:errors path="name" cssClass="error" /></td>
			</tr>
			<tr>
				<td><form:select path="category" id="categories">
						<form:option value="">Select Category</form:option>
						<form:options items="${categories}" itemValue="id"
							itemLabel="name" />
					</form:select></td>

			</tr>
			<tr>
				<td></td>
			</tr>
			<tr id="properties"></tr>
			<tr>
				<td><c:out value="${categoryIndex}" /></td>
			</tr>
<!-- 			<tr> -->
<%-- 				<td><c:forEach items="${categories[categoryIndex].attributesForCategory}" --%>
<%-- 						var="attribute"> --%>
<%-- 						<label>${attribute.name}</label> --%>
<%-- 						${attribute.name} --%>
<%-- 					</c:forEach></td> --%>
<!-- 			</tr> -->

			<tr>
				<td class="errorblock"><label for="category" id="categoryError"
					class="error"></label> <form:errors path="category"
						cssClass="error" /></td>

			</tr>
			<tr>
				<td><form:input path="currentPrice"
						placeholder="Enter current price" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="currentPrice"
					id="currentPriceError" class="error"></label> <form:errors
						path="currentPrice" cssClass="error" /></td>

			</tr>
			<tr>
				<td><form:input path="weight"
						placeholder="Enter product's weight" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="weight" id="weightError"
					class="error"></label> <form:errors path="weight" cssClass="error" /></td>

			</tr>

			<tr>
				<td><form:input path="bulk" placeholder="Enter product's bulk" /></td>
			</tr>
			<tr>
				<td class="errorblock"><label for="bulk" id="bulkError"
					class="error"></label> <form:errors path="bulk" cssClass="error" /></td>

			</tr>

			<tr>
				<td><form:textarea path="description"
						placeholder="Enter description" /></td>
			</tr>
						<tr>
				<td><input type="submit" value="Submit" id="submit"></td>
			</tr>
		</table>


	</form:form>

	<script type="text/javascript" src="../js/validateProdForm.js">
		
	</script>


	<script type="text/javascript"
		src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
		<script type="text/javascript">
		$('#submit').submit(function(){$.getJson
			var properties={property:{id:"",product:"",attribute:"",description:""}};
		});
		function sendAjax(properties) {
			 
			$.ajax({ 
			    url: "<spring:url value="cart.html"/>", 
			    type: 'POST', 
			    dataType: 'json', 
			    data: JSON.stringify({ product: product, amount: parseInt(newAmount)}),
			    contentType: 'application/json',
			    mimeType: 'application/json',
			    success: function(data) { 
			        alert(data.id + " " + data.name);
			    },
//			     error:function(data,status,er) { 
//			         alert("error: "+data.name+" status: "+status+" er:"+er);
//			     }
			});
			}
		</script>
	<script type="text/javascript">
		$("#categories").change(function() {

			// 			var index=$("#myDivNextSelectTag").html($(this)[0].value);
			var index = $(this)[0].value;
			console.log(index);
			var html = '<c:set var="categoryIndex"   value="${'+index+'}"/>';
			// 			<c:set var="categoryIndex" value="index"/>
			// 				var message = '<c:out value="${categoryIndex}"/>';
			$("#myDivNextSelectTag").html(html);

		})
	</script>
	<script type="text/javascript">
	var attributes;
		$("#categories").change(
				function() {
					var index = $(this)[0].value;
					$.getJSON('<spring:url value="categories.json"/>', {
						ajax : 'true'
					}, function(data) {
						var html = '';
						var category = data[index - 1];
						attributes = category.attributesForCategory;
						var len = attributes.length;
						for (var i = 0; i < len; i++) {
							html += '<label>' + attributes[i].name
									+ '</label><input value=""/>';
						}
						html += '';

						$('#properties').html(html);
					});

				});
	</script>

</body>
</html>