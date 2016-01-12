<%@ include file="header.jsp"%>

<div align="center">
	<h1>${title}</h1>
	<div>${message}</div>
	<form:form commandName="order" onsubmit="return validateForm(this);">
		<table>
			<c:forEach items="${order.orderItems}" var="item" begin="0"
				varStatus="i">

				<tr>
					<td><form:hidden cssClass="springInput" readonly="true"
							path="orderItems[${i.index}].id" /></td>
					<td><form:hidden cssClass="springInput" readonly="true"
							path="orderItems[${i.index}].product.name" /></td>

					<td><form:hidden cssClass="springAmount" readonly="true"
							path="orderItems[${i.index}].price" /></td>

					<td><form:hidden cssClass="springAmount" readonly="true"
							path="orderItems[${i.index}].amount" /></td>

					<td><form:hidden cssClass="springInput" readonly="true"
							path="orderItems[${i.index}].id" /></td>
				</tr>

			</c:forEach>
			<tr>
				<td>Order status:</td>
				<td><form:input readonly="true" cssClass="springInput"
						path="status" /></td>
			</tr>
			<tr>

				<%-- 					<td><form:hidden cssClass="springAmount" readonly="true" --%>
				<%-- 							path="cost" /></td> --%>
			</tr>
			<tr>
				<td>Payment method:</td>
				<td><form:select cssClass="springSelect" path="payMethod">
						<form:option value="" label="*** Select Option ***" />
						<form:options items="${paymentMethod}" />
					</form:select></td>
			</tr>
			<tr>
				<td></td>
				<td class="errorblock"><label for="payMethod" id="payError"
					class="error"></label></td>
				<form:errors path="payMethod" cssClass="error" />

			</tr>
			<tr>
				<td>Delivery method:</td>
				<td><form:input readonly="true" cssClass="springInput"
						path="deliveryMethod" />
			</tr>

			<tr>
				<td>Delivery date</td>
				<td><form:input cssClass="springInput" path="deliveryDate" /></td>
			</tr>
			<tr>
				<td></td>
				<td class="errorblock"><label for="deliveryDate"
					id="deliveryDateError" class="error"></label></td>
				<form:errors path="deliveryDate" cssClass="error" />

			</tr>

			<tr>
				<td><input type="submit" id="newAddressButton"
					value="Add address" /></td>
				<td>
					<div id="oldAddress">
								
						<form:select id="addressSelect" cssClass="springSelect"
							path="address">
							<option value="0">Previous addresses</option>

							<c:forEach items="${addresses}" var="address" begin="0"
								varStatus="i">
								<form:option value="${address.id}">${address.zip},
									${address.country}, ${address.city}, ${address.street} str, bld
									${address.building}, apt ${address.apartment}
								</form:option>
							</c:forEach>
<!-- 							<div id="oldAddressSelected" style="display: none"> -->
<%-- 								<form:form commandName="address"> --%>
<%-- 									<form:input readonly="true" path="id" value="${address.id}" /> --%>
<%-- 									<form:input path="zip" value="${address.zip}" /> --%>
<%-- 									<form:input path="country" value="${address.country}" /> --%>
<%-- 									<form:input path="city" value="${address.city}" /> --%>
<%-- 									<form:input path="street" value="${address.street}" /> --%>
<%-- 									<form:input path="building" value="${address.building}" /> --%>
<%-- 									<form:input path="apartment" value="${address.apartment}" /> --%>
<%-- 								</form:form> --%>
<!-- 							</div> -->
						</form:select>
						
					</div>
					<div id="newAddress" style="display: none">
						<form:form modelAttribute="address">
							<%-- 		onsubmit="return validateAddressForm(this);"> --%>
							<%-- 		<form:errors path="*" cssClass="errorblock" element="div" /> --%>
							<table>
								<tr>
									<td>${message}</td>
								</tr>
								<tr>
									<td><form:input path="zip" placeholder="Enter zip-code" />
									</td>
								</tr>
								<tr>
									<td class="errorblock"><label for="zip" id="zipError"
										class="error"></label></td>
									<form:errors path="zip" cssClass="error" />

								</tr>
								<tr>
									<td><form:input path="country" placeholder="Enter country" />
									</td>
								</tr>
								<tr>
									<td class="errorblock"><label for="country"
										id="countryError" class="error"></label></td>
									<form:errors path="country" cssClass="error" />

								</tr>

								<tr>
									<td><form:input path="city" placeholder="Enter city" /></td>
								</tr>
								<tr>
									<td class="errorblock"><label for="city" id="cityError"
										class="error"></label></td>
									<form:errors path="city" cssClass="error" />

								</tr>

								<tr>
									<td><form:input path="street" placeholder="Enter street" /></td>
								</tr>
								<tr>
									<td class="errorblock"><label for="street"
										id="streetError" class="error"></label></td>
									<form:errors path="street" cssClass="error" />

								</tr>

								<tr>
									<td><form:input path="building"
											placeholder="Enter building" /></td>
								</tr>
								<tr>
									<td class="errorblock"><label for="building"
										id="buildingError" class="error"></label></td>
									<form:errors path="building" cssClass="error" />

								</tr>
								<tr>
									<td><form:input path="apartment"
											placeholder="Enter apartment" /></td>
								</tr>
								<tr>
									<td class="errorblock"><label for="apartment"
										id="apartmentError" class="error"></label></td>

								</tr>
							</table>
						</form:form>
					</div>
				</td>
			</tr>
			<tr>
				<td></td>
				<td class="errorblock"><label for="address" id="addressError"
					class="error"></label></td>
				<form:errors path="address" cssClass="error" />

			</tr>
			<tr>
				<td><input type="submit" id="cancelOrder" value="Cancel order"
					name="action" onclick="return areYouSure();" /></td>

				<td><input type="submit" value="Next" name="action"
					class="input"></td>
			</tr>

		</table>
	</form:form>
	<div></div>
</div>
<script type="text/javascript">
	function validateForm(form) {
		var date = form.deliveryDate.value;
		var datePattern = /^\d{2}\/\d{2}\/\d{4}$/;
		var dateBool = false;
		document.getElementById("deliveryDateError").innerHTML = "";
		if (date == null || !date.match(datePattern)) {
			document.getElementById("deliveryDateError").innerHTML = "Select delivery date as mm/dd/yyyy!";
		} else {
			dateBool = true;
		}

		var pay = form.payMethod.value;
		var payBool = false;
		document.getElementById("payError").innerHTML = "";
		if (pay == "UNKNOWN") {
			document.getElementById("payError").innerHTML = "You can't choose UNKNOWN now!";
		} else {
			payBool = true;
		}

		// 			var address = Number(form.address.value);
		// 			var addressBool = false;
		// 			console.log(address);
		// 			document.getElementById("addressError").innerHTML = "";
		// 			if (address === 0) {
		// 				document.getElementById("addressError").innerHTML = "Select address or create new one.";
		// 			} else {
		// 				addressBool = true;
		// 			}
		// 			console.log("addressbool=" + addressBool);
		var addressBool = true;
		if (dateBool && addressBool && payBool)
			return true;
		else
			return false;
	}
</script>
<script>
	$(function() {
		$("#deliveryDate").datepicker();
		$("#newAddressButton").click(function(ev) {
			ev.preventDefault();
			ev.stopPropagation();
			$("#newAddress").toggle();
			$("#oldAddress").toggle();
		})
		$("#addressSelect").select(function(ev) {
			$("#oldAddressSelected").toggle();
		})
	})
</script>
<%@ include file="footer.jsp"%>
