$(function() {

	$("#btnUpdate").click(function() {
		$.post("/admin/material/edit", {
			materialid : $("#materialid").val(),
			name : $("#name").val(),
			unit : $("#unit").val()
		}, function(data, status) {
			$("#result-form").html(data);
			$("#result-form").fadeToggle(3000);
//			_list(link, 1);// at loadTable.js
		});
	});
});