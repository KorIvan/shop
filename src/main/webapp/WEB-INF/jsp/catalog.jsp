<%@ include file="header.jsp"%>
<div align="center">
	<h1>${title}</h1>
	<div class="message">${message}</div>
</div>
		<td>
			<div class="customList" id="categories" data-bind="foreach:categories">
				<input type="radio" name="category" data-bind="value:id,attr:{id:'cat'+id},event:{change:$root.onCategoryChange}"/>
				<label data-bind="attr:{for:'cat'+id},text:name"></label>
			</div>
		</td>
<div id="products" align="center" style="display: none;">
	<form:form commandName="cart.cart" name="products" style="margin:0px;border-radius: 0px;;">
		<table style="border-collapse: separate;
    border-spacing: 30px 20px;">
			<tr>
				<th>Product name</th>
				<th>Price</th>
				<th>Amount at storage</th>
				<th>Put to cart</th>
			</tr>
			<tbody id="properties" data-bind="foreach:products">
				 <tr>
				 <td><a data-bind="attr:{href:'product/'+id}"><label data-bind="text:name"></label></a></td>
	
		<td><label data-bind="text:currentPrice"></label></td>
			<td><label data-bind="text:storage.amount"> </label>	</td>
		<td >
		<input id="productAmount" data-bind="attr:{id:'prod'+id,max:storage.amount}" type="number" min="0" value=""></td></tr>
			</tbody>

		</table>
		<security:authorize access="!hasRole('SALES_MANAGER')">
			<input type="button" value="Send to cart" id="send" name="action" />
		</security:authorize>
		<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
		
	</form:form>
	</div>
</div>
<script type="text/javascript" src="/shop/js/knockout.js"></script>
<script type="text/javascript">
$(function(){
var viewModel={
		categories:ko.observableArray([]),
		products:ko.observableArray([]),
		onCategoryChange:function(data,event){
			console.log(data);
			console.log(this);
			viewModel.loadProducts(data.id);
			},
		loadProducts:function(categoryId)
		{
			var self=this;
			self.products.removeAll();
			$
			$.getJSON(
					'<spring:url value="/category/'
							+ (categoryId) + '.json"/>',
					{
						ajax : 'true'
					},
					function(data) {
						
						var html = '';
						
						var len = data.length;
						for (var i = 0; i < len; i++) {
							self.products.push(data[i]);
						}
						$("#products").show();
					});


			}
			
		};
	ko.applyBindings(viewModel);
// 	$('#productAmount').click(function(this){
// 		});
	
	$('#send').click(function() {
		var tempAmount;
	
		var product = null;
		var products=viewModel.products();
		var amount=0;
		for (var i = 0; i < products.length; i++) {
			var tempAmount = $('#prod' + products[i].id).val();
			console.log(tempAmount);
			if (tempAmount != 0) {
				amount = tempAmount;
				var product = products[i];
				tempAmount = 0;
				$('#prod' + products[i].id).val(0);
				sendAjax(product,amount);
			}
		}
	});
	
	    var token = $("meta[name='_csrf']").attr("content");
	    var header = $("meta[name='_csrf_header']").attr("content");
	    $(document).ajaxSend(function(e, xhr, options) {
	        xhr.setRequestHeader(header, token);
	    });
	
	function sendAjax(product,amount) {

		$.ajax({
			url : "<spring:url value="/addToCart.html"/>",
			type : 'POST',
			dataType : 'json',
			data : JSON.stringify({
				product : product,
				amount : amount
			}),
			contentType : 'application/json',
			mimeType : 'application/json',
			success : function(data) {
				alert(data.id + " " + data.name);
			},
		});
	}//sendAjax
	var products;
	$.getJSON('<spring:url value="/categories.json"/>',
				{ ajax : 'true'},
										function(data) {
											for(var i=0;i<data.length;++i)
												{
													viewModel.categories.push(data[i]);
												}
										});
	});
</script>

<%@ include file="footer.jsp"%>
