$(function() {

	var link = "/admin/bill/table";
	
	$(".remove").click(function() {
		$.post("/admin/bill/remove", {
			billid : $(this).attr("data-billid")
		}, function(data, status) {
			$("#result-tbody").html(data);
			$("#result-tbody").fadeToggle(3000);
			_list(link,1);// at loadTable.js
		});
	});

	$(".edit").click(function() {
		$.get("/admin/bill/edit", {
			billid : $(this).attr("data-billid")
		}, function(data, status) {
			$("#tBodyDetail").html(data);
		});
	});
});