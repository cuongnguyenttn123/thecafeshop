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
		$.get("/admin/bill-status/edit", {
			billstatusid : $(this).attr("data-billstatusid")
		}, function(data, status) {
			$("#billStatus_form").html(data);
		});
	});
});