var products;
$(document)
		.ready(
				function() {
					$
							.getJSON(
									'<spring:url value="categories.json"/>',
									{
										ajax : 'true'
									},
									function(data) {
										var html = '<option id="selectCategory" value="">--Select category--</option>';
										var len = data.length;
										for (var i = 0; i < len; i++) {
											html += '<option value="'
													+ data[i].id + '">'
													+ data[i].name
													+ '</option>';
										}
										html += '</option>';
										$('#categories').html(html);
									});
				});
$("#categories")
		.change(
				function() {
					var index = $(this)[0].value;
					$
							.getJSON(
									'<spring:url value="category/' + (index)
											+ '.json"/>',
									{
										ajax : 'true'
									},
									function(data) {
										var html = '';
										products = data;
										var len = products.length;
										for (var i = 0; i < len; i++) {
											html += '<tr><td><label id="'
													+ product[i].id
													+ '">'
													+ products[i].name
													+ '</label></td><td><label>'
													+ products[i].currentPrice
													+ '</label></td><td><input id="prod'
													+ products[i].id
													+ '" type="number" min="0" max="100" value=""></td></tr>';
										}
										html += '';
										globalData = data;
										$('#properties').html(html);
									});
				});