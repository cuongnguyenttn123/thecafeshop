$(function() {
	
	$(".remove").click(function() {
		$.post("/admin/warehouse-supplier/remove", {
			supplierid : $(this).attr("data-supplierid")
		}, function(data, status) {
			$("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link,1);// at loadTable.js
		});
	});

	$(".edit").click(function() {
		$.get("/admin/warehouse-supplier/edit", {
			supplierid : $(this).attr("data-supplierid")
		}, function(data, status) {
			$("#supplier_form").html(data);
		});
	});
});