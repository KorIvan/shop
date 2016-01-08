<%@ include file="header.jsp" %>


	<script type="text/javascript" >
		var itemList;
	$(document).ready(
			function() {
				$.getJSON('<spring:url value="cartContent.json"/>', {
					ajax : 'true'
				}, function(data) {
					itemList=data.itemList;
					var html = '';
					var len = itemList.length;
					for (var i = 0; i < len; i++) {
						html += '<tr><td><label>' + itemList[i].product.name+ '</label></td><td><input id="'+i+'" type="number" min="0" max="100" value="'+itemList[i].amount+'" onchange="changeAmount(this);"></td></tr>';
						console.log(itemList[i]);
					}
					html += '';

					$('#cart').html(html);
				});
			});
		
	</script>
<%@ include file="footer.jsp" %>