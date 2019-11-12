$(function() {
	
	$(".remove").click(function() {
		$.post("/admin/dinner-table/remove", {
			dinnertableid : $(this).attr("data-dinnertableid")
		}, function(data, status) {
			$("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link,1);// at loadTable.js
		});
	});

	$(".edit").click(function() {
		$.get("/admin/dinner-table/edit", {
			dinnertableid : $(this).attr("data-dinnertableid")
		}, function(data, status) {
			$("#dinnerTable_form").html(data);
		});
	});
});