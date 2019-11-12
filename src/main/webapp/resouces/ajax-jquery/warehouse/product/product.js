/**
 * 
 */

$(function () {
	
	$(".delete").hide();
	
	$(".btnUpdate").click(function() {
		var productid = $(this).attr("data-productId");
		var quantity = $("#"+ productid).val();
			$.post("/admin/warehouse-product/update", {
				productid,
				quantity
			}, function(data, status) {
				var obj = JSON.parse(data);
				if(obj.id == 1){
					$("#result-form").addClass("badge-success");
					$("#result-form").removeClass("badge-danger");
					$("#result-"+ productid).show();
					$("#result-"+ productid).hide(3000);
					$("#result-form").html(obj.mes);
					$("#result-form").fadeIn(3000);
					$("#result-form").fadeOut(3000);
					$("#quantity-"+productid).html(obj.quantity);
					$("#"+ productid).val("");
				}
				else{
					$("#result-form").addClass("badge-danger");
					$("#result-form").removeClass("badge-success");
					$("#result-form").html(obj.mes);
					$("#result-form").fadeIn(2000);
					$("#result-form").fadeOut(2000);
				}
			});
		
	});
	
	$("#btnSearch").click(function() {
		$.post("/admin/warehouse-product", {
			categoryproductid:$("#categoryproductid").val(),
			productid:$("#productid").val(),
			name:$("#name").val()
		}, function(data, status) {
			$("#content-index").html(data);
		});
	});
});
