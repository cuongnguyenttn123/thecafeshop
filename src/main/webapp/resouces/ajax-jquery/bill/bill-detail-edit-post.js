/**
 * 
 */
$("#btnUpdate").click(function() {
	var billid = $("#billid").val();
	$.post("/admin/billDetail/edit", {
		billid : billid,
		productid : $("#productid").val(),
		quantity : $("#quantity").val()
	}, function(data, status) {
		$("#result-form").html(data);
		$("#result-form").fadeToggle(3000);
		$.get("/admin/bill/edit", {
			billid : billid
		}, function(data, status) {
			$("#tBodyDetail").html(data);
		});
	});
});