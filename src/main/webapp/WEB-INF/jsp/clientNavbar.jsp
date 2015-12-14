<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="security"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<!-- Brand and toggle get grouped for better mobile display -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle collapsed"
				data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
				aria-expanded="false">
				<span class="sr-only">Toggle navigation</span> <span
					class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand" href="<spring:url value="/"/>">Entry
				point </a>
		</div>

		<!-- Collect the nav links, forms, and other content for toggling -->
		<div class="collapse navbar-collapse"
			id="bs-example-navbar-collapse-1">
			<ul class="nav navbar-nav">
				<security:authorize var="loggedIn" access="isAuthenticated()" />
				<c:if test="${!loggedIn}">
					<li><a
						href="<spring:url value="http://localhost:8080/shop/registration.html"/>">Registration</a></li>
				</c:if>

				<li><a
					href="<spring:url value="http://localhost:8080/shop/catalog.html"/>">Catalog</a>
				</li>

				<c:if test="${client.type eq 'CLIENT'}">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Addresses<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="<spring:url value="addAddress.html"/>">Add
									new</a></li>
							<li><a href="<spring:url value="allAddresses.html"/>">All
									addresses</a></li>

							<li><a href="#">Edit</a></li>

						</ul></li>
				</c:if>
			</ul>
			<ul class="nav navbar-nav">
				<!-- 				<li><a href="#">Link</a></li> -->
				<c:if test="${client.type eq 'CLIENT'}">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Orders<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a href="http://localhost:8080/shop/makeOrder.html">Make
									new</a></li>
							<li><a href="http://localhost:8080/shop/orderHistory.html">View
									history</a></li>

						</ul></li>
				</c:if>


				<security:authorize access="hasRole('SALES_MANAGER')">
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Product<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="<spring:url value="http://localhost:8080/shop/manager/category.html"/>">Make
									new</a></li>
							<li role="separator" class="divider"></li>

							<li role="separator" class="divider"></li>
						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Category<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="<spring:url value="http://localhost:8080/shop/manager/addCategory.html"/>">Make
									new</a></li>

						</ul></li>
					<li class="dropdown"><a href="#" class="dropdown-toggle"
						data-toggle="dropdown" role="button" aria-haspopup="true"
						aria-expanded="false">Orders<span class="caret"></span></a>
						<ul class="dropdown-menu">
							<li><a
								href="<spring:url value="http://localhost:8080/shop/manager/orders.html"/>">Order
									list</a></li>

						</ul></li>
				</security:authorize>
			</ul>

			<ul class="nav navbar-nav navbar-right">
				<security:authorize access="hasRole('CLIENT')">
					<li><a href="cart.html">Cart</a></li>
				</security:authorize>

				<!-- 				<li class="dropdown"><a href="#" class="dropdown-toggle" -->
				<!-- 					data-toggle="dropdown" role="button" aria-haspopup="true" -->
				<!-- 					aria-expanded="false">Dropdown <span class="caret"></span></a> -->
				<!-- 					<ul class="dropdown-menu"> -->
				<!-- 						<li><a href="#">Action</a></li> -->
				<!-- 						<li><a href="#">Another action</a></li> -->
				<!-- 						<li><a href="#">Something else here</a></li> -->
				<!-- 						<li role="separator" class="divider"></li> -->
				<!-- 						<li><a href="#">Separated link</a></li> -->
				<!-- 					</ul> -->
				<!-- 					</li> -->
				<li class="active"><c:if test="${client.type eq 'CLIENT'}">
						<a><c:out value="${client.type}" /> <span class="sr-only">(current)</span>
						</a>
					</c:if> <c:if test="${manager.type eq 'SALES_MANAGER'}">
						<a><c:out value="MANAGER" /> <span class="sr-only">(current)</span>
						</a>
					</c:if> 
					<c:if test="${loggedIn}">
						<li class="active"><a href="<spring:url value="logout.html" />">Log out <span
								class="sr-only">(current)</span></a></li>
					</c:if> 
					<c:if test="${manager.type eq 'SALES_MANAGER'}">
						<li class="active"><a
							href="http://localhost:8080/shop/manager/logout.html">Log out
								<span class="sr-only">(current)</span>
						</a></li>
					</c:if> <%-- 					<security:authentication var="role" property="principal.authorities"/> --%>
					<c:if test="${!loggedIn}">
						<li class="active"><a> GUEST <span class="sr-only">(current)</span>
						</a></li>
					</c:if> <security:authorize access="hasRole('SALES_MANAGER')">
						<li class="active"><a>Manager<span class="sr-only">(current)</span>
						</a></li>
					</security:authorize> <security:authorize access="hasRole('CLIENT')">
						<li class="active"><a>Client<span class="sr-only">(current)</span>
						</a></li>
					</security:authorize> <c:if test="${!loggedIn }">
						<li class="active"><a href="<spring:url value="login.html"/>">
								Log in <span class="sr-only">(current)</span>
						</a></li>
					</c:if></li>

				<li><span class="sr-only">(current)</span></li>
			</ul>
		</div>
		<!-- /.navbar-collapse -->
	</div>
	<!-- /.container-fluid --> </nav>
	<script src="<spring:url value="js/bootstrap.min.js"/>"
		integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
		crossorigin="anonymous"></script>

</body>
</html>