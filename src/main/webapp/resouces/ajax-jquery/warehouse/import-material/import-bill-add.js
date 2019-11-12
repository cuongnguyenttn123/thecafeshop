$(function() {
	
	$(".remove").click(function() {
		$.post("/admin/bill-status/remove", {
			billstatusid : $(this).attr("data-billstatusid")
		}, function(data, status) {
			$("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link,1);// at loadTable.js
		});
	});

	$(".edit").click(function() {
		$.get("/admin/warehouse-import-material/edit", {
			importbillid : $(this).attr("data-importbillid")
		}, function(data, status) {
			$("#import_material_form").html(data);
		});
	});
});