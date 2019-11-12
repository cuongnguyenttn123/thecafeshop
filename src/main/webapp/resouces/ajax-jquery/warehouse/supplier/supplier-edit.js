$(function() {

	$("#btnUpdate").click(function() {
		$.post("/admin/warehouse-supplier/edit", {
			supplierid : $("#supplierid").val(),
			name : $("#name").val(),
			phone : $("#phone").val(),
			address : $("#address").val()
		}, function(data, status) {
			$("#result-form").html(data);
			$("#result-form").fadeToggle(3000);
//			_list(link, 1);// at loadTable.js
		});
	});
});