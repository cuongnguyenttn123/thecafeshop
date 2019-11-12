/**
 * 
 */
$(function() {

	$(".edit-billDetail").click(function() {
		$.get("/admin/billDetail/edit", {
			billid : $(this).attr("data-billId"),
			productid : $(this).attr("data-productId")
		}, function(data, status) {
			$("#billDetail_form").html(data);
		});
	});
	
	$(".remove-billDetail").click(function() {
		billid = $(this).attr("data-billId");
		$.post("/admin/billDetail/remove", {
			billid,
			productid : $(this).attr("data-productId")
		}, function(data, status) {
			$("#billDetail_form").html(data);
			$.get("/admin/bill/edit", {
				billid : billid
			}, function(data, status) {
				$("#tBodyDetail").html(data);
			});
		});
	});

});