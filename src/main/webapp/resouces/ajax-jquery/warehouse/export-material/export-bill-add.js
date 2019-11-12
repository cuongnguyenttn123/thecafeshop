$(function() {
	
	$(".remove").click(function() {
		$.post("/admin/warehouse-export-material/remove", {
			exportbillid : $(this).attr("data-exportbillid")
		}, function(data, status) {
			$("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link,1);// at loadTable.js
		});
	});

	$(".edit").click(function() {
		$.get("/admin/warehouse-export-material/edit", {
			exportbillid : $(this).attr("data-exportbillid")
		}, function(data, status) {
			$("#export_material_form").html(data);
		});
	});
});