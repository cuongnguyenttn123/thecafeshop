$(function() {
	var link = "/admin/material/table";
	
	$(".remove").click(function() {
		$.post("/admin/material/remove", {
			materialid : $(this).attr("data-materialid")
		}, function(data, status) {
			$("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link,1);// at loadTable.js
		});
	});

	$(".edit").click(function() {
		$.get("/admin/material/edit", {
			materialid : $(this).attr("data-materialid")
		}, function(data, status) {
			$("#material_form").html(data);
		});
	});
});