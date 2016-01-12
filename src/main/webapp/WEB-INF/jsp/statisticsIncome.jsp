<%@ include file="header.jsp"%>
<div align="center">
	<form action="<spring:url value="/statistics/incomePeriod"/>"
		method="POST">
		<table>
			<thead>
				<th>Income period</th>
			</thead>
			<tbody>
				<tr>
					<td>From</td>
					<td><input type="text" name="from" id="from" /></td>
				</tr>
				<tr>
					<td>To</td>
					<td><input type="date" name="to" id="to" /></td>
				</tr>
				<tr>
					<td>Income:</td>
					<td>${income}</td>
				</tr>
			</tbody>
		</table>
		<input type="submit" value="Get income" /> <input type="hidden"
			name="${_csrf.parameterName}" value="${_csrf.token}" />
	</form>

</div>
<script>
	$(function() {
		$("#from").datepicker();
		$("#to").datepicker();
	});
</script>
<%@ include file="footer.jsp"%>